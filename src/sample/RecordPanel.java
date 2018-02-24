package sample;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;

public class RecordPanel {

    private Label OneUp;
    private Label HighScore;
    private Label TwoUp;
    private Label OneUpValue;
    private Label TwoUpValue;
    private Label HighScoreValue;

    public RecordPanel() {
        OneUp = new Label("1UP");
        HighScore = new Label("HIGH-SCORE");
        TwoUp = new Label("2UP");
        OneUpValue = new Label("00");
        HighScoreValue = new Label("00");
        TwoUpValue = new Label("00");
    }

    public void SetFont(Font font)
    {
        OneUp.setFont(font);
        HighScore.setFont(font);
        TwoUp.setFont(font);
        OneUpValue.setFont(font);
        TwoUpValue.setFont(font);
        HighScoreValue.setFont(font);
    }

    public void SetColor()
    {
        OneUp.setTextFill(Color.RED);
        HighScore.setTextFill(Color.RED);
        TwoUp.setTextFill(Color.RED);
        OneUpValue.setTextFill(Color.WHITE);
        TwoUpValue.setTextFill(Color.WHITE);
        HighScoreValue.setTextFill(Color.WHITE);
    }

    public void AddToGrid(GridPane pane)
    {
        GridPane.setHalignment(OneUp, HPos.CENTER);
        GridPane.setHalignment(HighScore, HPos.CENTER);
        GridPane.setHalignment(TwoUp, HPos.CENTER);
        GridPane.setHalignment(OneUpValue, HPos.CENTER);
        GridPane.setHalignment(HighScoreValue, HPos.CENTER);
        GridPane.setHalignment(TwoUpValue, HPos.CENTER);
        pane.setConstraints(OneUp, 0, 0);
        pane.setConstraints(HighScore, 1, 0);
        pane.setConstraints(TwoUp, 2, 0);
        pane.setConstraints(OneUpValue, 0, 1);
        pane.setConstraints(HighScoreValue, 1, 1);
        pane.setConstraints(TwoUpValue, 2, 1);
        pane.getChildren().addAll(OneUp, HighScore, TwoUp, OneUpValue, HighScoreValue, TwoUpValue);
    }
}
