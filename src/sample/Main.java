package sample;

import com.sun.javafx.geom.Rectangle;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.time.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tests");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        Label OneUp = new Label("1UP");
        Label HighScore = new Label("HIGH-SCORE");
        Label TwoUp = new Label("2UP");
        Label OneUpValue = new Label("00");
        Label HighScoreValue = new Label("00");
        Label TwoUpValue = new Label("00");
        Label OnePalyer = new Label("1 PLAYER");
        Label TwoPalyers = new Label("2 PLAYERS");
        Font pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        HBox PlayerInfo = new HBox();
        Image logoImage = new Image("sample/Pacman_logo.png");
        ImageView pacManLogo = new ImageView(logoImage);
        Image ChoiseImage = new Image("sample/Original_PacMan.png");
        ImageView ChoiseSymbol = new ImageView(ChoiseImage);

        VBox LogoBox = new VBox();
        StackPane  PlayerChoisePane = new StackPane ();

        PlayerChoisePane.getChildren().addAll(OnePalyer, TwoPalyers, ChoiseSymbol);
        PlayerChoisePane.setMinHeight(120);
        PlayerChoisePane.setAlignment(OnePalyer, Pos.CENTER);
        PlayerChoisePane.setAlignment(TwoPalyers, Pos.BOTTOM_CENTER);
        PlayerChoisePane.setAlignment(ChoiseSymbol, Pos.CENTER_LEFT);
        ChoiseSymbol.setFitWidth(30);
        ChoiseSymbol.setFitHeight(30);
        ChoiseSymbol.setRotate(ChoiseSymbol.getRotate() + 180);
        ChoiseSymbol.setTranslateX(50);


        LogoBox.setMinHeight(250);
        pacManLogo.setScaleX(1.2);
        pacManLogo.setScaleY(1.2);
        PlayerInfo.setAlignment(Pos.CENTER);
        PlayerInfo.getChildren().addAll(OneUp, HighScore, TwoUp);
        PlayerInfo.setSpacing(200);


        OneUp.setFont(pacManFont);
        HighScore.setFont(pacManFont);
        TwoUp.setFont(pacManFont);
        OneUp.setTextFill(Color.RED);
        HighScore.setTextFill(Color.RED);
        TwoUp.setTextFill(Color.RED);
        OneUpValue.setFont(pacManFont);
        HighScoreValue.setFont(pacManFont);
        TwoUpValue.setFont(pacManFont);
        OneUpValue.setTextFill(Color.WHITE);
        HighScoreValue.setTextFill(Color.WHITE);
        TwoUpValue.setTextFill(Color.WHITE);
        OnePalyer.setFont(pacManFont);
        OnePalyer.setTextFill(Color.WHITE);
        TwoPalyers.setFont(pacManFont);
        TwoPalyers.setTextFill(Color.WHITE);
        OnePalyer.setOnMouseEntered(event ->
                {
                        OnePalyer.setTextFill(Color.RED);
                        PlayerChoisePane.setAlignment(ChoiseSymbol, Pos.CENTER_LEFT);

                }
        );
        OnePalyer.setOnMouseExited(event->
        {
            OnePalyer.setTextFill(Color.WHITE);
        });
        TwoPalyers.setOnMouseEntered(event ->
                {
                    TwoPalyers.setTextFill(Color.RED);
                    PlayerChoisePane.setAlignment(ChoiseSymbol, Pos.BOTTOM_LEFT);
                }
        );
        TwoPalyers.setOnMouseExited(event->
        {
            TwoPalyers.setTextFill(Color.WHITE);
        });


        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPrefWidth(primaryScreenBounds.getWidth()/3);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPrefWidth(primaryScreenBounds.getWidth()/3);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPrefWidth(primaryScreenBounds.getWidth()/3);

        pane.getColumnConstraints().addAll(column1, column2, column3);
        GridPane.setHalignment(OneUp, HPos.CENTER);
        GridPane.setHalignment(HighScore, HPos.CENTER);
        GridPane.setHalignment(TwoUp, HPos.CENTER);
        GridPane.setHalignment(OneUpValue, HPos.CENTER);
        GridPane.setHalignment(HighScoreValue, HPos.CENTER);
        GridPane.setHalignment(TwoUpValue, HPos.CENTER);
        GridPane.setHalignment(PlayerChoisePane, HPos.CENTER);

        pane.setHgap(50);
        pane.setGridLinesVisible(true);
        pane.setConstraints(OneUp, 0, 0);
        pane.setConstraints(HighScore, 1, 0);
        pane.setConstraints(TwoUp, 2, 0);
        pane.setConstraints(OneUpValue, 0, 1);
        pane.setConstraints(HighScoreValue, 1, 1);
        pane.setConstraints(TwoUpValue, 2, 1);
        pane.setConstraints(PlayerChoisePane, 1, 3);


        pane.setConstraints(LogoBox, 1, 2);
        LogoBox.getChildren().addAll(pacManLogo);
        LogoBox.setAlignment(Pos.BOTTOM_CENTER);

        AnimationControl animationControl = new AnimationControl(2, 2, 80, 100, 20, 20);
        animationControl.PacmanSetTranslateX(500);
        animationControl.PacmanSetTranslateY(500);
        animationControl.Launch(pane);

        pane.getChildren().addAll(OneUp, HighScore, TwoUp, OneUpValue, HighScoreValue, TwoUpValue, LogoBox, PlayerChoisePane);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
