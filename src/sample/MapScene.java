package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * class that constructs scene, where main gameplay will take place.
 */

class MapScene extends Scene {

    private static final String FILENAME= "C:\\Users\\desmond1999d\\Documents\\IntelliJIDEA\\Epam2lvl\\src\\GameFiles\\SavedGame.json";
    private Pane mainPane;
    private RecordPanel recordPanel;
    private Rectangle2D primaryScreenBounds;
    private Stage primaryStage;
    private MainMenuAnimation animation;
    private Player player;
    private PacmanBot bot;
    private RedGhost redGhost;
    private PinkGhost pinkGhost;
    private BrownGhost brownGhost;
    private Map map;
    private Vector<MealClass> mealVector;
    private MainMenu menu;
    private boolean emulate;
    public final AnimationTimer timer;
    public boolean exit = false;
    private JSONArray jsonArray;

    /**
     * Constructor
     * @param pane required for scene constructor
     * @param stage primaryStage itself
     */

    public MapScene(final boolean emu, final Pane pane, final Stage stage, MainMenu oldScene){
        super(pane);
        emulate = emu;
        menu = oldScene;
        mealVector = new Vector<MealClass>();
        recordPanel = new RecordPanel();
        mainPane = pane;
        primaryStage = stage;
        map = new Map(mainPane);
        animation = new MainMenuAnimation(mainPane, 550);
        jsonArray = new JSONArray();
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        if (!emulate)
            player = new Player(mealVector, recordPanel, mainPane);
        else
            bot = new PacmanBot(mealVector, recordPanel, pane);
        if (emu) {
            redGhost = new RedGhost(0, 0, null, bot, primaryStage, this);
            pinkGhost = new PinkGhost(0, 0, null, bot, primaryStage, this);
            brownGhost = new BrownGhost(0, 0, null, bot, primaryStage, this);
        }
        else {
            redGhost = new RedGhost(0, 0, player, null, primaryStage, this);
            pinkGhost = new PinkGhost(0, 0, player, null, primaryStage, this);
            brownGhost = new BrownGhost(0, 0, player, null, primaryStage, this);
        }
        setEvents();
        buildMap();
        buildRecordPanel();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        addMeal();
    }

    private void update() {
        putPosAndDirToJSON();
        if (!emulate) {
            redGhost.setAim(player.posOnMapX, player.posOnMapY);
            pinkGhost.setAim(player.posOnMapX, player.posOnMapY, player.dir);
            brownGhost.setAim(player.posOnMapX, player.posOnMapY);
        }
        else {
            redGhost.setAim(bot.posOnMapX, bot.posOnMapY);
            pinkGhost.setAim(bot.posOnMapX, bot.posOnMapY, bot.dir);
            brownGhost.setAim(bot.posOnMapX, bot.posOnMapY);
        }
        if(mealVector.size() == 0)
            addMeal();
        if (exit) {
            exitScene();
        }
    }

    private void exitScene() {
        exit = false;
        for(int i = 1; i < Map.ySize; i++)
            map.map[i] = map.map[i].replace('2', '0');
        mainPane.getChildren().removeAll(mealVector);
        mealVector.clear();
        recordPanel.putInfoToFile("GameResults.txt");
        menu.readInfoFromFile();
        actionStop();
        putInfoToJson();
        recordPanel.setOneUp(00);
        recordPanel.setTwoUp(00);
        primaryStage.setScene(menu);
    }

    private void putPosAndDirToJSON() {
        JSONObject jsonString = new JSONObject();
        jsonString.put("posOnMapX", player.getTranslateX());
        jsonString.put("posOnMapY", player.getTranslateY());
        jsonString.put("dir", player.dir.ordinal());
        jsonArray.add(jsonString);
    }

    private void putInfoToJson() {
        try (FileWriter writer = new FileWriter(FILENAME)){
            writer.write(jsonArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        jsonArray.clear();
    }

    private void addMeal() {
        MealClass meal;
        Image tempImage = new Image("Sprites/Pacman10-hp-sprite.png");
        mainPane.getChildren().removeAll(mealVector);
        mealVector.clear();
        for(int i = 1; i < Map.ySize; i++) {
            map.map[i] = map.map[i].replace('0', '2');
            for(int j = 1; j < Map.xSize; j++)
                if(map.map[i].charAt(j) == '2') {
                    meal = new MealClass(j, i, tempImage);
                    mealVector.addElement(meal);
                    mainPane.getChildren().add(meal);
                }
        }
    }

    public void actionStop() {
        if (!emulate)
            player.timer.stop();
        else
            bot.timer.stop();
        redGhost.timer.stop();
        pinkGhost.timer.stop();
        brownGhost.timer.stop();
        timer.stop();
    }

    public void actionStart() {
        if (!emulate)
            player.timer.start();
        else
            bot.timer.start();
        redGhost.timer.start();
        pinkGhost.timer.start();
        brownGhost.timer.start();
        timer.start();
    }

    private void setEvents() {
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                exitScene();
            }
            if(!emulate) {
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                    player.changeDir(direction.UP);
                } else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                    player.changeDir(direction.DOWN);
                } else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                    player.changeDir(direction.RIGHT);
                } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                    player.changeDir(direction.LEFT);
                }
            }
        });
    }

    public void refresh() {
        addMeal();
        recordPanel.setOneUp(00);
        if (!emulate)
            player.setStartPos();
        else
            bot.setStartPos();
        redGhost.reset();
        pinkGhost.reset();
        brownGhost.reset();
    }

    /**
     * adds map to the scene
     */

    public void buildMap() {
        mainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        final int x = 322;
        final int y = 0;
        final int height = 138;
        final int width = 500;
        if (!emulate)
            mainPane.getChildren().addAll(player);
        else
            mainPane.getChildren().addAll(bot);
        mainPane.getChildren().addAll(redGhost, pinkGhost, brownGhost);
    }

    /**
     * sets the reocrd menu
     */

    public void buildRecordPanel() {
        GridPane gridPane = new GridPane();
        recordPanel.addToGrid(gridPane, 0, 0, 1, 0, 2, 0);
        gridPane.setTranslateY(420);
        gridPane.setVgap(10);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(primaryScreenBounds.getWidth()/3);
        gridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);
        recordPanel.loadInfoFromFile();
        if (!emulate)
            recordPanel.setOneUp(00);
        else
            recordPanel.setTwoUp(00);
        mainPane.getChildren().addAll(gridPane);
    }
}
