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

import java.io.*;

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
    private boolean twoPlayersChosen;
    private MapScene map = null;

    /**
     * Constructor
     * @param constructorPane pane for the scene constructor
     * @param stage primaryStage itself
     */

    public MainMenu(final GridPane constructorPane, final Stage stage) {
        super(constructorPane);
        pane = constructorPane;
        primaryStage = stage;
        mainLogo = new MainLogo();
        playerChoisePane = new StackPane();
        recordPanel = new RecordPanel();
        readInfoFromFile();
        playersQuantity = new PlayersQuantity(playerChoisePane, primaryStage, map, this);
        anim = new MainMenuAnimation(pane, ConstantClass.SCENEANIMPOSY);
        getItTogether();
        setActions();
    }

    public void readInfoFromFile() {
        File file = new File("GameResults.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                recordPanel.setOneUpString(in.readLine());
                recordPanel.setHighScoreString(in.readLine());
                recordPanel.setTwoUpString(in.readLine());
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the events for players quantity choise
     */

    private void setActions() {
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP)
            {
                playersQuantity.onePlayerChosen();
                playersQuantity.twoPlayersNotChosen();
                onePlayerChosen = true;
                twoPlayersChosen = false;
            }
            else if (event.getCode() == KeyCode.DOWN)
            {
                playersQuantity.twoPlayersChosen();
                playersQuantity.onePlayerNotChosen();
                twoPlayersChosen = true;
                onePlayerChosen = false;
            }
            else if (event.getCode() == KeyCode.ENTER)
            {
                if (onePlayerChosen) {
                    if (map == null)
                        map = new MapScene(new Pane(), primaryStage, this);
                    else
                        map.refresh();
                    primaryStage.setScene(map);
                }
                else if (twoPlayersChosen) {
                    if (map == null)
                        map = new MapScene(new Pane(), primaryStage, this);
                    else
                        map.refresh();
                    primaryStage.setScene(map);
                }
            }
            else if (event.getCode() == KeyCode.ESCAPE) {
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
        columnConstraints.setPrefWidth(primaryScreenBounds.getWidth()/ 3);
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
