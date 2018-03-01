package sample;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;

public class RecordPanel {

    private Label oneUp;
    private Label highScore;
    private Label twoUp;
    private Label oneUpValue;
    private Label twoUpValue;
    private Label highScoreValue;

    public RecordPanel() {
        oneUp = new Label("1UP");
        highScore = new Label("HIGH-SCORE");
        twoUp = new Label("2UP");
        oneUpValue = new Label("00");
        highScoreValue = new Label("00");
        twoUpValue = new Label("00");
        Font pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        setColor();
        setFont(pacManFont);
    }

    public void setFont(Font font)
    {
        oneUp.setFont(font);
        highScore.setFont(font);
        twoUp.setFont(font);
        oneUpValue.setFont(font);
        twoUpValue.setFont(font);
        highScoreValue.setFont(font);
    }

    public void setColor()
    {
        oneUp.setTextFill(Color.RED);
        highScore.setTextFill(Color.RED);
        twoUp.setTextFill(Color.RED);
        oneUpValue.setTextFill(Color.WHITE);
        twoUpValue.setTextFill(Color.WHITE);
        highScoreValue.setTextFill(Color.WHITE);
    }

    public void addToGrid(GridPane pane, int oneUpX, int oneUpY, int highScoreX, int highScoreY, int twoUpX, int twoUpY)
    {
        GridPane.setHalignment(oneUp, HPos.CENTER);
        GridPane.setHalignment(highScore, HPos.CENTER);
        GridPane.setHalignment(twoUp, HPos.CENTER);
        GridPane.setHalignment(oneUpValue, HPos.CENTER);
        GridPane.setHalignment(highScoreValue, HPos.CENTER);
        GridPane.setHalignment(twoUpValue, HPos.CENTER);
        pane.setConstraints(oneUp, oneUpX, oneUpY);
        pane.setConstraints(highScore, highScoreX, highScoreY);
        pane.setConstraints(twoUp, twoUpX, twoUpY);
        pane.setConstraints(oneUpValue, oneUpX, oneUpY+1);
        pane.setConstraints(highScoreValue, highScoreX, highScoreY+1);
        pane.setConstraints(twoUpValue, twoUpX, twoUpY+1);
        pane.getChildren().addAll(oneUp, highScore, twoUp, oneUpValue, highScoreValue, twoUpValue);
    }
}
