package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * class that constructs scene, where main gameplay will take place.
 */

class MapScene extends Scene {

    private Pane mainPane;
    private RecordPanel recordPanel;
    private Rectangle2D primaryScreenBounds;
    private Stage primaryStage;
    private MainMenuAnimation animation;
    private Player player;
    private RedGhost redGhost;
    private PinkGhost pinkGhost;
    private BrownGhost brownGhost;
    private Map map;
    private Vector<MealClass> mealVector;
    private MainMenu menu;

    /**
     * Constructor
     * @param pane required for scene constructor
     * @param stage primaryStage itself
     */

    public MapScene(final Pane pane, final Stage stage, MainMenu oldScene){
        super(pane);
        menu = oldScene;
        mealVector = new Vector<MealClass>();
        recordPanel = new RecordPanel();
        mainPane = pane;
        primaryStage = stage;
        map = new Map(mainPane);
        animation = new MainMenuAnimation(mainPane, 550);
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        player = new Player(mealVector, recordPanel, mainPane);
        //redGhost = new RedGhost(0, 0, 28, 1, player, recordPanel, primaryStage);
        //pinkGhost = new PinkGhost(0, 0, 28, 1, player, recordPanel, primaryStage);
        //brownGhost = new BrownGhost(0, 0, 28, 1, player, recordPanel, primaryStage);
        setEvents();
        buildMap();
        buildRecordPanel();
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        addMeal();
    }

    private void putInfoToFile(String path) {
        File file;
        PrintWriter printWriter;
        file = new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter = new PrintWriter(file.getAbsoluteFile());
            printWriter.println(recordPanel.getOneUpInt());
            printWriter.println(recordPanel.getHighScoreInt());
            printWriter.println(recordPanel.getTwoUpInt());
            printWriter.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update() {
        //redGhost.setAim(player.posOnMapX, player.posOnMapY);
        //pinkGhost.setAim(player.posOnMapX, player.posOnMapY, player.dir);
        //brownGhost.setAim(player.posOnMapX, player.posOnMapY);
        if(mealVector.size() == 0)
            addMeal();
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

    private void setEvents() {
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                for(int i = 1; i < Map.ySize; i++)
                    map.map[i] = map.map[i].replace('2', '0');
                putInfoToFile("GameResults.txt");
                menu.readInfoFromFile();
                primaryStage.setScene(menu);
            }
            else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                player.changeDir(direction.UP);
            }
            else if (event.getCode() == KeyCode.S|| event.getCode() == KeyCode.DOWN) {
                player.changeDir(direction.DOWN);
            }
            else if (event.getCode() == KeyCode.D|| event.getCode() == KeyCode.RIGHT) {
                player.changeDir(direction.RIGHT);
            }
            else if (event.getCode() == KeyCode.A|| event.getCode() == KeyCode.LEFT) {
                player.changeDir(direction.LEFT);
            }
        });
    }

    public void refresh() {
        addMeal();
        recordPanel.setOneUp(00);
        player.setStartPos();
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
        mainPane.getChildren().addAll(player/*, redGhost, pinkGhost, redGhost, brownGhost*/);
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
        mainPane.getChildren().addAll(gridPane);
    }
}
