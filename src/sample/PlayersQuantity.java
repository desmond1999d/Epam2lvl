package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;

public class PlayersQuantity {

    private Label OnePlayer;
    private Label TwoPlayers;
    private ImageView ChoiseSymbol;

    public PlayersQuantity(StackPane pane)
    {
        OnePlayer = new Label("1 PLAYER");
        TwoPlayers = new Label("2 PLAYERS");
        Image ChoiseImage = new Image("sample/Original_PacMan.png");
        ChoiseSymbol = new ImageView(ChoiseImage);
        ChoiseSymbolSettings();
        SetActions(pane);
        SetAlligment(pane);
        pane.getChildren().addAll(OnePlayer, TwoPlayers, ChoiseSymbol);
    }

    public void SetFont(Font font)
    {
        OnePlayer.setFont(font);
        TwoPlayers.setFont(font);
    }

    public void SetColor(Color color)
    {
        OnePlayer.setTextFill(color);
        TwoPlayers.setTextFill(color);
    }

    public void SetActions(StackPane PlayerChoisePane)
    {
        OnePlayer.setOnMouseEntered(event ->
                {
                    OnePlayer.setTextFill(Color.RED);
                    PlayerChoisePane.setAlignment(ChoiseSymbol, Pos.CENTER_LEFT);
                }
        );
        OnePlayer.setOnMouseExited(event->
        {
            OnePlayer.setTextFill(Color.WHITE);
        });
        TwoPlayers.setOnMouseEntered(event ->
                {
                    TwoPlayers.setTextFill(Color.RED);
                    PlayerChoisePane.setAlignment(ChoiseSymbol, Pos.BOTTOM_LEFT);
                }
        );
        TwoPlayers.setOnMouseExited(event->
        {
            TwoPlayers.setTextFill(Color.WHITE);
        });
    }

    public void SetAlligment(StackPane PlayerChoisePane)
    {
        PlayerChoisePane.setAlignment(OnePlayer, Pos.CENTER);
        PlayerChoisePane.setAlignment(TwoPlayers, Pos.BOTTOM_CENTER);
        PlayerChoisePane.setAlignment(ChoiseSymbol, Pos.CENTER_LEFT);
    }

    public void ChoiseSymbolSettings()
    {
        ChoiseSymbol.setFitWidth(30);
        ChoiseSymbol.setFitHeight(30);
        ChoiseSymbol.setRotate(ChoiseSymbol.getRotate() + 180);
        ChoiseSymbol.setTranslateX(50);
    }
}
