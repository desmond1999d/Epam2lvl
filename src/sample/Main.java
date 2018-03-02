package sample;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This is the main class that preloads first scene
 */

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tests");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        MainMenu mainMenu = new MainMenu(new GridPane(), primaryStage);
        MapScene mapScene = new MapScene(new Pane(), primaryStage);

        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    /**
     * auto-generted method
     * @param args
     */

    public static void main(final String[] args) {
        launch(args);
    }
}
