package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tests");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        MainMenu mainMenu = new MainMenu(new GridPane(), primaryStage);
        MapScene mapScene = new MapScene(new Pane());

        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
