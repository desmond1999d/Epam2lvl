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

/**
 * the interface of choise of palyers quantity.
 */

enum chosenLabel {
    ONE_PLAYER,
    GAME_EMULATOR,
    LOAD_GAME,
    NO_CHOISE
}

/**
 * Block that provides an opportunity to choose a game mode
 */

public class PlayersQuantity {
    private Label onePlayer;
    private Label gameEmulator;
    private Label loadGame;
    private ImageView choiseSymbol;
    private Stage primaryStage;
    private StackPane playerChoisePane;
    private MapScene map;
    private MainMenu menu;
    private MapScene mapEmulated;
    public chosenLabel chosenOption;

    public PlayersQuantity(final StackPane pane, final Stage stage, final MapScene mapScene, final MapScene mapEmul, final MainMenu mainMenu) {
        menu = mainMenu;
        map = mapScene;
        mapEmulated = mapEmul;
        primaryStage = stage;
        playerChoisePane = pane;
        chosenOption = chosenLabel.NO_CHOISE;
        onePlayer = new Label("1 PLAYER");
        gameEmulator = new Label("EMULATE");
        loadGame = new Label("LOAD GAME");
        choiseSymbol = new ImageView(new Image("Sprites/Original_PacMan.png"));
        choiseSymbolSettings();
        setActions();
        setAlligment(pane);
        pane.getChildren().addAll(onePlayer, gameEmulator, loadGame, choiseSymbol);
    }

    /** set font
     * @param font - font for labels
     */
    public void setFont(final Font font) {
        onePlayer.setFont(font);
        gameEmulator.setFont(font);
        loadGame.setFont(font);
    }

    /** set color
     * @param color - color for labels
     */

    public void setColor(final Color color) {
        onePlayer.setTextFill(color);
        gameEmulator.setTextFill(color);
        loadGame.setTextFill(color);
    }

    /**
     * highlightion for one player label
     */

    public void onePlayerChosen() {
        onePlayer.setTextFill(Color.RED);
        playerChoisePane.setAlignment(choiseSymbol, Pos.TOP_LEFT);
    }

    /**
     * highlightion for two players label
     */

    public void EmulationChosen() {
        gameEmulator.setTextFill(Color.RED);
        playerChoisePane.setAlignment(choiseSymbol, Pos.CENTER_LEFT);
    }

    /**
     * highlightion for load game label
     */

    public void loadGameChosen() {
        loadGame.setTextFill(Color.RED);
        playerChoisePane.setAlignment(choiseSymbol, Pos.BOTTOM_LEFT);
    }

    /**
     * return back from the highlightion state for one player label
     */

    public void onePlayerNotChosen() {
        onePlayer.setTextFill(Color.WHITE);
    }

    /**
     * return back from the highlightion state for two players label
     */

    public void EmulationNotChosen() {
        gameEmulator.setTextFill(Color.WHITE);
    }

    /**
     * return back from the highlightion state for load game label
     */

    public void loadGameNotChosen() {
        loadGame.setTextFill(Color.WHITE);
    }

    /**
     * set envents for the labels
     */

    public void setActions() {
        onePlayer.setOnMouseEntered(event ->
                {
                    if (chosenOption == chosenLabel.GAME_EMULATOR)
                        EmulationNotChosen();
                    else if (chosenOption == chosenLabel.LOAD_GAME)
                        loadGameNotChosen();
                    onePlayerChosen();
                    chosenOption = chosenLabel.ONE_PLAYER;
                }
        );
        onePlayer.setOnMouseClicked(
                event ->
                {
                    if (map == null)
                        map = new MapScene(false, new Pane(), primaryStage, menu);
                    else
                        map.refresh();
                    map.actionStart();
                    primaryStage.setScene(map);
                }
        );
        onePlayer.setOnMouseExited(event->
        {
            onePlayerNotChosen();
        });
        loadGame.setOnMouseEntered(event ->
                {
                    if (chosenOption == chosenLabel.ONE_PLAYER)
                        onePlayerNotChosen();
                    else if (chosenOption == chosenLabel.GAME_EMULATOR)
                        EmulationNotChosen();
                    loadGameChosen();
                    chosenOption = chosenLabel.LOAD_GAME;
                }
        );
        loadGame.setOnMouseClicked(
                event ->
                {
                    if (map == null)
                        map = new MapScene(false, new Pane(), primaryStage, menu);
                    else
                        map.refresh();
                    map.actionStart();
                    map.startLoadActions();
                    primaryStage.setScene(map);
                }
        );
        loadGame.setOnMouseExited(event->
        {
            loadGameNotChosen();
        });
        gameEmulator.setOnMouseEntered(event ->
                {
                    EmulationChosen();
                    if (chosenOption == chosenLabel.ONE_PLAYER)
                        onePlayerNotChosen();
                    else if (chosenOption == chosenLabel.LOAD_GAME)
                        loadGameNotChosen();
                    chosenOption = chosenLabel.GAME_EMULATOR;
                }
        );
        gameEmulator.setOnMouseExited(event->
        {
            EmulationNotChosen();
        });
        gameEmulator.setOnMouseClicked(
                event ->
                {
                    if (mapEmulated == null)
                        mapEmulated = new MapScene(true, new Pane(), primaryStage, menu);
                    else
                        mapEmulated.refresh();
                    mapEmulated.actionStart();
                    primaryStage.setScene(mapEmulated);
                }
        );
    }

    /**
     * sets the position of the labels
     * @param playerChoisePane the place, where labels are put
     */

    public void setAlligment(final StackPane playerChoisePane) {
        playerChoisePane.setAlignment(onePlayer, Pos.TOP_CENTER);
        playerChoisePane.setAlignment(gameEmulator, Pos.CENTER);
        playerChoisePane.setAlignment(loadGame, Pos.BOTTOM_CENTER);
        playerChoisePane.setAlignment(choiseSymbol, Pos.TOP_LEFT);
    }

    /**
     * settings of a little icon on the left of the labels
     */

    public void choiseSymbolSettings() {
        choiseSymbol.setFitWidth(30);
        choiseSymbol.setFitHeight(30);
        choiseSymbol.setRotate(choiseSymbol.getRotate() + 180);
        choiseSymbol.setTranslateX(50);
    }
}
