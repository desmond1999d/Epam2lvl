package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PlayersQuantity {
    private Label onePlayer;
    private Label twoPlayers;
    private ImageView choiseSymbol;
    private Stage primaryStage;
    private StackPane playerChoisePane;

    public PlayersQuantity(final StackPane pane, final Stage stage) {
        primaryStage = stage;
        playerChoisePane = pane;
        onePlayer = new Label("1 PLAYER");
        twoPlayers = new Label("2 PLAYERS");
        choiseSymbol = new ImageView(new Image("sample/Original_PacMan.png"));
        choiseSymbolSettings();
        setActions();
        setAlligment(pane);
        pane.getChildren().addAll(onePlayer, twoPlayers, choiseSymbol);
    }

    /** set font
     * @param font - font for labels
     * @return - nothing
     */
    public void setFont(final Font font) {
        onePlayer.setFont(font);
        twoPlayers.setFont(font);
    }

    public void setColor(final Color color) {
        onePlayer.setTextFill(color);
        twoPlayers.setTextFill(color);
    }

    public void onePlayerChosen() {
        onePlayer.setTextFill(Color.RED);
        playerChoisePane.setAlignment(choiseSymbol, Pos.CENTER_LEFT);
    }

    public void twoPlayersChosen() {
        twoPlayers.setTextFill(Color.RED);
        playerChoisePane.setAlignment(choiseSymbol, Pos.BOTTOM_LEFT);
    }

    public void onePlayerNotChosen() {
        onePlayer.setTextFill(Color.WHITE);
    }

    public void twoPlayersNotChosen() {
        twoPlayers.setTextFill(Color.WHITE);
    }

    public void setActions() {
        onePlayer.setOnMouseEntered(event ->
                {
                    onePlayerChosen();
                }
        );
        onePlayer.setOnMouseClicked(
                event ->
                {
                    primaryStage.setScene(new MapScene(new Pane(), primaryStage));
                }
        );
        onePlayer.setOnMouseExited(event->
        {
            onePlayerNotChosen();
        });
        twoPlayers.setOnMouseEntered(event ->
                {
                    twoPlayersChosen();
                }
        );
        twoPlayers.setOnMouseExited(event->
        {
            twoPlayersNotChosen();
        });
        twoPlayers.setOnMouseClicked(
                event ->
                {
                    primaryStage.setScene(new MapScene(new Pane(), primaryStage));
                }
        );
    }

    public void setAlligment(final StackPane playerChoisePane) {
        playerChoisePane.setAlignment(onePlayer, Pos.CENTER);
        playerChoisePane.setAlignment(twoPlayers, Pos.BOTTOM_CENTER);
        playerChoisePane.setAlignment(choiseSymbol, Pos.CENTER_LEFT);
    }

    public void choiseSymbolSettings() {
        choiseSymbol.setFitWidth(30);
        choiseSymbol.setFitHeight(30);
        choiseSymbol.setRotate(choiseSymbol.getRotate() + 180);
        choiseSymbol.setTranslateX(50);
    }
}
