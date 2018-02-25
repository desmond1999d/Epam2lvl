package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.awt.*;

public class MapScene {

    private Pane mainPane;
    private Scene scene;
    private ImageView imageView;
    private RecordPanel recordPanel;
    private Label oneUp;
    private Label twoUp;
    private Label highScore;
    private Label oneUpValue;
    private Label twoUpValue;
    private Label highScoreValue;
    private Rectangle2D primaryScreenBounds;

    public MapScene() {
        recordPanel = new RecordPanel();
        mainPane = new Pane();
        scene = new Scene(mainPane);
        Image image = new Image("sample/Pacman10-hp-sprite.png");
        imageView = new ImageView(image);
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        BuildMap();
        BuildRecordPanel();
    }

    public void BuildMap() {
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

    public void BuildRecordPanel() {
        oneUp = recordPanel.GetOneUp();
        twoUp = recordPanel.GetTwoUp();
        highScore = recordPanel.GetHighscore();
        oneUpValue = recordPanel.GetOneUpValue();
        twoUpValue = recordPanel.GetTwoUpValue();
        highScoreValue = recordPanel.GetHighscoreValue();
//        HBox labelHBox = new HBox();
//        HBox valueHBox = new HBox();
//        labelHBox.getChildren().addAll(oneUp, twoUp, highScore);
//        valueHBox.getChildren().addAll(oneUpValue, twoUpValue, highScoreValue);
//        labelHBox.setAlignment(Pos.CENTER);
//        valueHBox.setAlignment(Pos.CENTER);
//        valueHBox.setPrefWidth(primaryScreenBounds.getWidth());
//        labelHBox.setPrefWidth(primaryScreenBounds.getWidth());
//        labelHBox.setSpacing(150);
//        labelHBox.setTranslateY(450);
//        valueHBox.setSpacing(150);
//        valueHBox.setTranslateY(500);
//        mainPane.getChildren().addAll(labelHBox, valueHBox);
    }

    public Scene GetScene() {
        return scene;
    }
}
