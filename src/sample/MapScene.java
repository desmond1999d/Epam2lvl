package sample;

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

class MapScene extends Scene {

    private Pane mainPane;
    private ImageView imageView;
    private RecordPanel recordPanel;
    private Rectangle2D primaryScreenBounds;
    private Stage primaryStage;
    private MainMenuAnimation animation;

    public MapScene(final Pane pane, final Stage stage){
        super(pane);
        recordPanel = new RecordPanel();
        mainPane = pane;
        primaryStage = stage;
        Image image = new Image("sample/Pacman10-hp-sprite.png");
        imageView = new ImageView(image);
        animation = new MainMenuAnimation(mainPane, 550);
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        buildMap();
        buildRecordPanel();
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE)
                primaryStage.setScene(new MainMenu(new GridPane(), primaryStage));
        });
    }

    public void buildMap() {
        mainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        final int x = 322;
        final int y = 0;
        final int height = 138;
        final int width = 500;
        imageView.setFitHeight(400);
        imageView.setFitWidth(primaryScreenBounds.getWidth()+85);
        imageView.setViewport(new Rectangle2D(x, y, width, height));
        mainPane.getChildren().addAll(imageView);
    }



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
