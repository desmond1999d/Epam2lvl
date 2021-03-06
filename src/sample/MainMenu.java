package sample;

import Constants.ConstantClass;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Class, that represents Main menu scene and all the actions on that scene
 */

public class MainMenu extends Scene {

    private GridPane pane;
    private Font pacManFont;
    private StackPane playerChoisePane;
    private RecordPanel recordPanel;
    private MainLogo mainLogo;
    private PlayersQuantity playersQuantity;
    private MainMenuAnimation anim;
    private Stage primaryStage;
    private boolean onePlayerChosen;
    private boolean gameEmulatorChosen;
    private boolean loadGameChosen;
    private MapScene map = null;
    private MapScene mapEmulated;

    /**
     * Constructor
     *
     * @param constructorPane pane for the scene constructor
     * @param stage           primaryStage itself
     */

    public MainMenu(final GridPane constructorPane, final Stage stage) {
        super(constructorPane);
        pane = constructorPane;
        primaryStage = stage;
        mainLogo = new MainLogo();
        playerChoisePane = new StackPane();
        recordPanel = new RecordPanel();
        recordPanel.loadInfoFromFile();
        playersQuantity = new PlayersQuantity(playerChoisePane, primaryStage, map, mapEmulated, this);
        anim = new MainMenuAnimation(pane, ConstantClass.SCENEANIMPOSY);
        getItTogether();
        setActions();
    }

    /**
     * Load RecordPanel info from specified file
     */

    public void readInfoFromFile() {
        recordPanel.loadInfoFromFile();
    }

    /**
     * Sets the events for players quantity choise
     */

    private void setActions() {
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (playersQuantity.chosenOption == chosenLabel.GAME_EMULATOR) {
                    playersQuantity.onePlayerChosen();
                    playersQuantity.chosenOption = chosenLabel.ONE_PLAYER;
                    playersQuantity.EmulationNotChosen();
                    onePlayerChosen = true;
                    gameEmulatorChosen = false;
                } else if (playersQuantity.chosenOption == chosenLabel.LOAD_GAME) {
                    playersQuantity.EmulationChosen();
                    playersQuantity.chosenOption = chosenLabel.GAME_EMULATOR;
                    playersQuantity.loadGameNotChosen();
                    gameEmulatorChosen = true;
                    loadGameChosen = false;
                } else if (playersQuantity.chosenOption == chosenLabel.NO_CHOISE) {
                    playersQuantity.onePlayerChosen();
                    playersQuantity.chosenOption = chosenLabel.ONE_PLAYER;
                    onePlayerChosen = true;
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                if (playersQuantity.chosenOption == chosenLabel.GAME_EMULATOR) {
                    playersQuantity.loadGameChosen();
                    playersQuantity.EmulationNotChosen();
                    playersQuantity.chosenOption = chosenLabel.LOAD_GAME;
                    gameEmulatorChosen = false;
                    loadGameChosen = true;
                } else if (playersQuantity.chosenOption == chosenLabel.ONE_PLAYER) {
                    playersQuantity.EmulationChosen();
                    playersQuantity.onePlayerNotChosen();
                    playersQuantity.chosenOption = chosenLabel.GAME_EMULATOR;
                    onePlayerChosen = false;
                    gameEmulatorChosen = true;
                } else if (playersQuantity.chosenOption == chosenLabel.NO_CHOISE) {
                    playersQuantity.chosenOption = chosenLabel.GAME_EMULATOR;
                    playersQuantity.EmulationChosen();
                    gameEmulatorChosen = true;
                }
            } else if (event.getCode() == KeyCode.ENTER) {
                if (onePlayerChosen) {
                    onePlayerChosen = false;
                    playersQuantity.onePlayerNotChosen();
                    if (map == null)
                        map = new MapScene(false, new Pane(), primaryStage, this);
                    else
                        map.refresh();
                    map.actionStart();
                    primaryStage.setScene(map);
                } else if (gameEmulatorChosen) {
                    gameEmulatorChosen = false;
                    playersQuantity.EmulationNotChosen();
                    if (mapEmulated == null)
                        mapEmulated = new MapScene(true, new Pane(), primaryStage, this);
                    else
                        mapEmulated.refresh();
                    mapEmulated.actionStart();
                    primaryStage.setScene(mapEmulated);
                } else if (loadGameChosen) {
                    loadGameChosen = false;
                    playersQuantity.loadGameNotChosen();
                    if (map == null)
                        map = new MapScene(false, new Pane(), primaryStage, this);
                    else
                        map.refresh();
                    map.actionStart();
                    map.startLoadActions();
                    primaryStage.setScene(map);
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                pane.getChildren().removeAll();
                primaryStage.close();
            }
        });
    }

    /**
     * sets the style to the record panel and adds it to the
     * GridPane
     */

    private void setRecordPanel() {
        recordPanel.setFont(pacManFont);
        recordPanel.setColor();
        recordPanel.addToGrid(pane, 0, 0, 1, 0, 2, 0);
    }

    /**
     * adds all the elements to the main menu
     */

    private void getItTogether() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        columnConstraints.setPrefWidth(primaryScreenBounds.getWidth() / 3);
        pane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);
        pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        playerChoisePane.setMinHeight(120);
        GridPane.setHalignment(playerChoisePane, HPos.CENTER);
        pane.setConstraints(playerChoisePane, 1, 3);
        setRecordPanel();
        setPlayersQuantity();
        pane.setHgap(50);
        mainLogo.addToPane(pane);
        pane.getChildren().addAll(playerChoisePane);
    }

    /**
     * Sets the style of PlayersQuantity info
     */

    private void setPlayersQuantity() {
        playersQuantity.setFont(pacManFont);
        playersQuantity.setColor(Color.WHITE);
    }
}
