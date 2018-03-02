package sample;

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

    public MainMenu(final GridPane constructorPane, final Stage stage) {
        super(constructorPane);
        pane = constructorPane;
        primaryStage = stage;
        mainLogo = new MainLogo();
        playerChoisePane = new StackPane();
        recordPanel = new RecordPanel();
        playersQuantity = new PlayersQuantity(playerChoisePane, primaryStage);
        anim = new MainMenuAnimation(pane, 500);
        getItTogether();
        setActions();
    }

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
                if (onePlayerChosen)
                    primaryStage.setScene(new MapScene(new Pane(), primaryStage));
                else if (twoPlayersChosen)
                    primaryStage.setScene(new MapScene(new Pane(), primaryStage));
            }
        });
    }

    private void setRecordPanel() {
        recordPanel.setFont(pacManFont);
        recordPanel.setColor();
        recordPanel.addToGrid(pane, 0, 0, 1, 0, 2, 0);
    }

    private void getItTogether() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        columnConstraints.setPrefWidth(primaryScreenBounds.getWidth()/3);
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

    private void setPlayersQuantity() {
        playersQuantity.setFont(pacManFont);
        playersQuantity.setColor(Color.WHITE);
    }
}
