package sample;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.*;

/**
 * sets the style of a record panel
 */

public class RecordPanel {

    private Label oneUp;
    private Label highScore;
    private Label twoUp;
    private Label oneUpValue;
    private Label twoUpValue;
    private Label highScoreValue;
    private int oneUpInt;
    private int twoUpInt;
    private int highScoreInt;

    /**
     * constructor
     */

    public RecordPanel() {
        oneUpInt = 0;
        twoUpInt = 0;
        highScoreInt = 0;
        oneUp = new Label("1UP");
        highScore = new Label("HIGH-SCORE");
        twoUp = new Label("2UP");
        oneUpValue = new Label();
        loadHighScoreFromFile();
        highScoreValue = new Label("00");
        twoUpValue = new Label("00");
        Font pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        setColor();
        setFont(pacManFont);
    }

    public void putInfoToFile(String path) {
        File file;
        PrintWriter printWriter;
        file = new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter = new PrintWriter(file.getAbsoluteFile());
            printWriter.println(oneUpInt);
            printWriter.println(highScoreInt);
            printWriter.println(twoUpInt);
            printWriter.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadHighScoreFromFile() {
        File file = new File("GameResults.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String temp;
                in.readLine();
                temp = in.readLine();
                setHighScore(Integer.parseInt(temp));
            } finally {
                in.close();
                return;
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadInfoFromFile() {
        File file = new File("GameResults.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String temp;
                temp = in.readLine();
                setOneUp(Integer.parseInt(temp));
                temp = in.readLine();
                setHighScore(Integer.parseInt(temp));
                temp = in.readLine();
                setTwoUp(Integer.parseInt(temp));
            } finally {
                in.close();
                return;
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sets the font of the labels
     * @param font obvious
     */

    public void setFont(final Font font) {
        oneUp.setFont(font);
        highScore.setFont(font);
        twoUp.setFont(font);
        oneUpValue.setFont(font);
        twoUpValue.setFont(font);
        highScoreValue.setFont(font);
    }

    /**
     * set the color of labels
     */

    public void setColor() {
        oneUp.setTextFill(Color.RED);
        highScore.setTextFill(Color.RED);
        twoUp.setTextFill(Color.RED);
        oneUpValue.setTextFill(Color.WHITE);
        twoUpValue.setTextFill(Color.WHITE);
        highScoreValue.setTextFill(Color.WHITE);
    }

    /**
     * adds labels to GridPane
     * @param pane where to add
     * @param oneUpX obvious
     * @param oneUpY obvious
     * @param highScoreX obvious
     * @param highScoreY obvious
     * @param twoUpX obvious
     * @param twoUpY obvious
     */

    public void addToGrid(final GridPane pane, final int oneUpX, final int oneUpY,
                          final int highScoreX, final int highScoreY, final int twoUpX, final int twoUpY) {
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

    public int getOneUpInt() {
        return oneUpInt;
    }

    public int getTwoUpInt() {
        return twoUpInt;
    }

    public int getHighScoreInt() {
        return highScoreInt;
    }

    public void setOneUp(int newOneUp) {
        oneUpInt = newOneUp;
        oneUpValue.setText(String.valueOf(oneUpInt));
    }

    public void setHighScore(int newHighScore) {
        highScoreInt = newHighScore;
        highScoreValue.setText(String.valueOf(highScoreInt));
    }

    public void setTwoUp(int newTwoUp) {
        twoUpInt = newTwoUp;
        twoUpValue.setText(String.valueOf(twoUpInt));
    }

    public void setOneUpString(String newOneUp) {
        oneUpValue.setText(newOneUp);
    }

    public void setHighScoreString(String newHighScore) {
        highScoreValue.setText(newHighScore);
    }

    public void setTwoUpString(String newTwoUp) {
        twoUpValue.setText(newTwoUp);
    }
}
