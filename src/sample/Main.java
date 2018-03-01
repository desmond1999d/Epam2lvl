package sample;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tests");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        MainMenu mainMenu = new MainMenu(new GridPane(), primaryStage);
        MapScene mapScene = new MapScene(new Pane(), primaryStage);

        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
