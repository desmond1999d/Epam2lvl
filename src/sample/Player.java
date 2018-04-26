package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import java.util.Vector;

enum direction {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    STABLE
}

public class Player extends IconsAnimated {

    public direction dir;
    private direction prevDir;
    public int posOnMapX = 1;
    public int posOnMapY = 1;
    private int wentX;
    private int wentY;
    private final int dist = Map.blockSize;
    private final int wentBorderPos = 25;
    private final int wentBorderNeg = -25;
    private Vector<MealClass> mealVect;
    private RecordPanel recordPanel;
    private final Pane mainPane;
    public final AnimationTimer timer;

    public Player(Vector<MealClass> mealClassVector, RecordPanel record, Pane pane) {
        mainPane = pane;
        recordPanel = record;
        mealVect = mealClassVector;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        setStartPos();
    }

    private MealClass findMealByCoordinate(int x, int y) {
        for (int i = 0; i < mealVect.size(); i++)
            if(mealVect.get(i).getPosOnMapX() == x && mealVect.get(i).getPosOnMapY() == y)
                return mealVect.get(i);
        return null;
    }

    public void setStartPos() {
        dir = direction.STABLE;
        setTranslateX(41);
        setTranslateY(42);
        wentX = 0;
        wentY = 0;
        prevDir = dir;
        posOnMapY = 1;
        posOnMapX = 1;
    }

    public void changeDir(direction i) {
        switch (i) {
            case LEFT:
                setRotate(0);
                break;
            case RIGHT:
                setRotate(180);
                break;
            case UP:
                setRotate(90);
                break;
            case DOWN:
                setRotate(-90);
                break;
        }
        dir = i;
    }

    public void update() {
        MealClass mealTemp;
        switch (dir) {
            case UP:
                if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX) == '2') {
                    goUp();
                    wentY--;
                    if (Math.abs(wentY) >= dist) {
                        posOnMapY--;
                        wentY = 0;
                    }
                } else {
                    if (wentX <= wentBorderNeg) {
                        if (Map.map[posOnMapY - 1].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX - 1) == '2') {
                            this.setTranslateX(this.getTranslateX() - (dist - Math.abs(wentX)));
                            posOnMapX--;
                            wentX = 0;
                        }
                    }
                    else if (wentX >= wentBorderPos) {
                        if (Map.map[posOnMapY - 1].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX + 1) == '2') {
                            this.setTranslateX(this.getTranslateX() + dist - wentX);
                            posOnMapX++;
                            wentX = 0;
                        }
                    }
                }
                prevDir = dir;
                break;
            case DOWN:
                if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX) == '2') {
                    goDown();
                    wentY++;
                    if (wentY >= dist) {
                        posOnMapY++;
                        wentY = 0;
                    }
                } else {

                    if (wentX <= wentBorderNeg) {
                        if (Map.map[posOnMapY + 1].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX - 1) == '2') {
                            this.setTranslateX(this.getTranslateX() - (dist - Math.abs(wentX)));
                            posOnMapX--;
                            wentX = 0;
                        }
                    }
                    else if (wentX >= wentBorderPos) {
                        if (Map.map[posOnMapY + 1].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX + 1) == '2') {
                            this.setTranslateX(this.getTranslateX() + dist - wentX);
                            posOnMapX++;
                            wentX = 0;
                        }
                    }
                }
                prevDir = dir;
                break;
            case LEFT:
                if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX - 1) == '2') {
                    if (prevDir == direction.LEFT || prevDir == direction.RIGHT) {
                        wentX--;
                        goLeft();
                        if (Math.abs(wentX) >= dist) {
                            posOnMapX--;
                            wentX = 0;
                        }
                    }
                }
                else {
                    if (wentY <= wentBorderNeg) {
                        if (Map.map[posOnMapY - 1].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX - 1) == '2') {
                            this.setTranslateY(this.getTranslateY() - (dist - Math.abs(wentY)));
                            posOnMapY--;
                            wentY = 0;
                        }
                    } else if (wentY >= wentBorderPos) {
                        if (Map.map[posOnMapY + 1].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX - 1) == '2') {
                            this.setTranslateY(this.getTranslateY() + (dist - Math.abs(wentY)));
                            posOnMapY++;
                            wentY = 0;
                        }
                    }
                }
                prevDir = dir;
                break;
            case RIGHT:
                if (Map.map[posOnMapY].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX + 1) == '2') {
                    if (prevDir == direction.LEFT || prevDir == direction.RIGHT) {
                        wentX++;
                        goRight();
                        if (wentX >= dist) {
                            posOnMapX++;
                            wentX = 0;
                        }
                    }
                }
                else {
                    if (wentY <= wentBorderNeg) {
                        if (Map.map[posOnMapY - 1].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX + 1) == '2') {
                            this.setTranslateY(this.getTranslateY() - (dist - Math.abs(wentY)));
                            posOnMapY--;
                            wentY = 0;
                        }
                    } else if (wentY >= wentBorderPos) {
                        if (Map.map[posOnMapY + 1].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX + 1) == '2') {
                            this.setTranslateY(this.getTranslateY() + (dist - Math.abs(wentY)));
                            posOnMapY++;
                            wentY = 0;
                        }
                    }
                }
                prevDir = dir;
                break;
        }
        if (Map.map[posOnMapY].charAt(posOnMapX) == '2') {
            char[] tempString;
            tempString = Map.map[posOnMapY].toCharArray();
            tempString[posOnMapX] = '0';
            Map.map[posOnMapY] = String.valueOf(tempString);
            mealTemp = findMealByCoordinate(posOnMapX, posOnMapY);
            if (mealTemp != null) {
                mainPane.getChildren().remove(mealTemp);
                recordPanel.setOneUp(recordPanel.getOneUpInt() + 1);
                if (recordPanel.getOneUpInt() > recordPanel.getHighScoreInt())
                    recordPanel.setHighScore(recordPanel.getOneUpInt());
                mealVect.remove(mealTemp);
            }
        }
    }
}
