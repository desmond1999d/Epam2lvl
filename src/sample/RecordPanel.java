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
        Font pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        SetColor();
        SetFont(pacManFont);
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

    public void AddToGrid(GridPane pane, int oneUpX, int oneUpY, int highScoreX, int highScoreY, int twoUpX, int twoUpY)
    {
        GridPane.setHalignment(OneUp, HPos.CENTER);
        GridPane.setHalignment(HighScore, HPos.CENTER);
        GridPane.setHalignment(TwoUp, HPos.CENTER);
        GridPane.setHalignment(OneUpValue, HPos.CENTER);
        GridPane.setHalignment(HighScoreValue, HPos.CENTER);
        GridPane.setHalignment(TwoUpValue, HPos.CENTER);
        pane.setConstraints(OneUp, oneUpX, oneUpY);
        pane.setConstraints(HighScore, highScoreX, highScoreY);
        pane.setConstraints(TwoUp, twoUpX, twoUpY);
        pane.setConstraints(OneUpValue, oneUpX, oneUpY+1);
        pane.setConstraints(HighScoreValue, highScoreX, highScoreY+1);
        pane.setConstraints(TwoUpValue, twoUpX, twoUpY+1);
        pane.getChildren().addAll(OneUp, HighScore, TwoUp, OneUpValue, HighScoreValue, TwoUpValue);
    }

    public Label GetTwoUp(){
        return TwoUp;
    }

    public Label GetOneUp(){
        return OneUp;
    }

    public Label GetHighscore(){
        return HighScore;
    }

    public Label GetOneUpValue(){
        return OneUpValue;
    }

    public Label GetTwoUpValue(){
        return TwoUpValue;
    }

    public Label GetHighscoreValue(){
        return HighScoreValue;
    }
}
