package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class MainMenu {

    private Scene mainMenuScene;
    private GridPane pane;
    private Font pacManFont;
    private StackPane PlayerChoisePane;
    private RecordPanel recordPanel;
    private MainLogo mainLogo;
    private PlayersQuantity playersQuantity;
    private MainMenuAnimation anim;

    public MainMenu()
    {
        pane = new GridPane();
        mainMenuScene = new Scene(pane);
        mainLogo = new MainLogo();
        PlayerChoisePane = new StackPane();
        recordPanel = new RecordPanel();
        playersQuantity = new PlayersQuantity(PlayerChoisePane);
        anim = new MainMenuAnimation(pane);
        GetItTogether();
    }

    private void SetRecordPanel()
    {
        recordPanel.SetFont(pacManFont);
        recordPanel.SetColor();
        recordPanel.AddToGrid(pane);
    }

    private void GetItTogether()
    {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        columnConstraints.setPrefWidth(primaryScreenBounds.getWidth()/3);
        pane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);
        pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        PlayerChoisePane.setMinHeight(120);
        GridPane.setHalignment(PlayerChoisePane, HPos.CENTER);
        pane.setConstraints(PlayerChoisePane, 1, 3);
        SetRecordPanel();
        SetPlayersQuantity();
        pane.setHgap(50);
        mainLogo.AddToPane(pane);
        pane.getChildren().addAll(PlayerChoisePane);
    }

    private void SetPlayersQuantity()
    {
        playersQuantity.SetFont(pacManFont);
        playersQuantity.SetColor(Color.WHITE);
    }

    public Scene GetScene()
    {
        return mainMenuScene;
    }
}
