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

import java.io.Console;

/**
 * class that constructs scene, where main gameplay will take place.
 */

class MapScene extends Scene {

    private Pane mainPane;
    //private ImageView imageView;
    private RecordPanel recordPanel;
    private Rectangle2D primaryScreenBounds;
    private Stage primaryStage;
    private MainMenuAnimation animation;
    private Player player;
    private RedGhost redGhost;
    private Map map;

    /**
     * Constructor
     * @param pane required for scene constructor
     * @param stage primaryStage itself
     */

    public MapScene(final Pane pane, final Stage stage){
        super(pane);
        recordPanel = new RecordPanel();
        mainPane = pane;
        primaryStage = stage;
        Image image = new Image("Sprites/Pacman10-hp-sprite.png");
        map = new Map(mainPane);
        //imageView = new ImageView(image);
        animation = new MainMenuAnimation(mainPane, 550);
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        player = new Player();
        redGhost = new RedGhost(0, 0, 28, 1);
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
    }

    private void update()
    {
        redGhost.setAim(player.posOnMapX, player.posOnMapY);
    }

    private void setEvents() {
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.setScene(new MainMenu(new GridPane(), primaryStage));
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

    /**
     * adds map to the scene
     */

    public void buildMap() {
        mainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        final int x = 322;
        final int y = 0;
        final int height = 138;
        final int width = 500;
        //imageView.setFitHeight(400);
        //imageView.setFitWidth(primaryScreenBounds.getWidth()+85);
        //imageView.setViewport(new Rectangle2D(x, y, width, height));
        mainPane.getChildren().addAll(/*imageView,*/ player, redGhost);
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
