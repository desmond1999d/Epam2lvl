package sample;

import Constants.ConstantClass;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
        highScoreValue = new Label("00");
        twoUpValue = new Label("00");
        Font pacManFont = Font.loadFont(getClass()
                .getResourceAsStream("Joystix.TTF"), 30);
        setColor();
        setFont(pacManFont);
    }

    public void putInfoToFile() {
        File file;
        PrintWriter printWriter;
        file = new File(ConstantClass.RECORDFILENAME);
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

    /**
     * Loads Record panel info from specified file
     */

    public void loadInfoFromFile() {
        File file = new File(ConstantClass.RECORDFILENAME);
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

    /**
     * @param newOneUp new OneUp value
     */

    public void setOneUp(int newOneUp) {
        oneUpInt = newOneUp;
        oneUpValue.setText(String.valueOf(oneUpInt));
    }

    /**
     * @param newHighScore new HighScore value
     */

    public void setHighScore(int newHighScore) {
        highScoreInt = newHighScore;
        highScoreValue.setText(String.valueOf(highScoreInt));
    }

    /**
     * @param newTwoUp new TwoUp value
     */

    public void setTwoUp(int newTwoUp) {
        twoUpInt = newTwoUp;
        twoUpValue.setText(String.valueOf(twoUpInt));
    }

    /**
     * @param newOneUp new OneUp string
     */

    public void setOneUpString(String newOneUp) {
        oneUpValue.setText(newOneUp);
    }

    /**
     * @param newHighScore new HighScore string
     */

    public void setHighScoreString(String newHighScore) {
        highScoreValue.setText(newHighScore);
    }

    /**
     * @param newTwoUp new TwoUp string
     */

    public void setTwoUpString(String newTwoUp) {
        twoUpValue.setText(newTwoUp);
    }
}
