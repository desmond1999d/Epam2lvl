package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.Vector;

public class PacmanBot extends IconsAnimated {
    public direction dir;
    private direction prevDir;
    public int posOnMapX = 1;
    public int posOnMapY = 1;
    private int wentX;
    private int wentY;
    private final int dist = Map.blockSize;
    private Vector<MealClass> mealVect;
    private RecordPanel recordPanel;
    private final Pane mainPane;
    public final AnimationTimer timer;

    public PacmanBot(Vector<MealClass> mealClassVector, RecordPanel record, Pane pane) {
        dir = direction.LEFT;
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
        dir = direction.LEFT;
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

    @Override
    public void goLeft() {
        prevDir = dir;
        dir = direction.LEFT;
        if (dir != prevDir)
            changeDir(direction.LEFT);
        this.setTranslateX(this.getTranslateX() - 1);
        wentX--;
    }

    @Override
    public void goRight() {
        prevDir = dir;
        dir = direction.RIGHT;
        if (dir != prevDir)
            changeDir(direction.RIGHT);
        this.setTranslateX(this.getTranslateX() + 1);
        wentX++;
    }

    @Override
    public void goUp() {
        prevDir = dir;
        dir = direction.UP;
        if (dir != prevDir)
            changeDir(direction.UP);
        this.setTranslateY(this.getTranslateY() - 1);
        wentY--;
    }

    @Override
    public void goDown() {
        prevDir = dir;
        dir = direction.DOWN;
        if (dir != prevDir)
            changeDir(direction.DOWN);
        this.setTranslateY(this.getTranslateY() + 1);
        wentY++;
    }

    private void dirGo(direction dir) {
        switch (dir) {
            case LEFT: goLeft(); break;
            case RIGHT: goRight(); break;
            case UP: goUp(); break;
            case DOWN: goDown(); break;
        }
    }

    public boolean isAbleNotToChangeDir(direction dir) {
        switch (dir) {
            case DOWN: if(Map.map[posOnMapY+1].charAt(posOnMapX) == '0' || Map.map[posOnMapY+1].charAt(posOnMapX) == '2')
                return true;
            else
                return false;
            case UP: if(Map.map[posOnMapY-1].charAt(posOnMapX) == '0' || Map.map[posOnMapY-1].charAt(posOnMapX) == '2')
                return true;
            else
                return false;
            case LEFT: if(Map.map[posOnMapY].charAt(posOnMapX-1) == '0' || Map.map[posOnMapY].charAt(posOnMapX-1) == '2')
                return true;
            else
                return false;
            case RIGHT: if(Map.map[posOnMapY].charAt(posOnMapX+1) == '0' || Map.map[posOnMapY].charAt(posOnMapX+1) == '2')
                return true;
            else
                return false;
        }
        return false;
    }

    private boolean isAbleToGoLeft() {
        if(Map.map[posOnMapY].charAt(posOnMapX-1) == '0' || Map.map[posOnMapY].charAt(posOnMapX-1) == '2')
            return true;
        else
            return false;
    }

    private boolean isAbleToGoRight() {
        if(Map.map[posOnMapY].charAt(posOnMapX+1) == '0' || Map.map[posOnMapY].charAt(posOnMapX+1) == '2')
            return true;
        else
            return false;
    }

    private boolean isAbleToGoUp() {
        if(Map.map[posOnMapY-1].charAt(posOnMapX) == '0' || Map.map[posOnMapY-1].charAt(posOnMapX) == '2')
            return true;
        else
            return false;
    }

    private boolean isAbleToGoDown() {
        if(Map.map[posOnMapY+1].charAt(posOnMapX) == '0' || Map.map[posOnMapY+1].charAt(posOnMapX) == '2')
            return true;
        else
            return false;
    }

    private void movePosOnMap()
    {
        if (wentX <= dist*(-1)) {
            posOnMapX--;
            wentX = 0;
        }
        else if (wentX >= dist) {
            posOnMapX++;
            wentX = 0;
        }
        else if (wentY <= dist * (-1)) {
            posOnMapY--;
            wentY = 0;
        }
        else if (wentY >= dist) {
            posOnMapY++;
            wentY = 0;
        }
    }

    private void move() {
        movePosOnMap();
        if (wentY != 0 || wentX != 0)
            dirGo(dir);
        else if (wentX == 0 && wentY == 0){
            Random random = new Random();
            int i = random.nextInt(2);
            switch (dir) {
                case DOWN:
                    if (isAbleToGoRight() && isAbleToGoLeft()) {
                        if (i == 1)
                            goRight();
                        else
                            goLeft();
                    }
                    else if (isAbleNotToChangeDir(dir))
                        dirGo(dir);
                    else if(isAbleToGoLeft())
                        goLeft();
                    else if(isAbleToGoRight())
                        goRight();
                    else if(isAbleToGoUp())
                        goUp();
                    break;
                case UP:
                    if (isAbleToGoRight() && isAbleToGoLeft()) {
                        if (i == 1)
                            goRight();
                        else
                            goLeft();
                    }
                    else if (isAbleNotToChangeDir(dir))
                        dirGo(dir);
                    else if(isAbleToGoLeft())
                        goLeft();
                    else if(isAbleToGoRight())
                        goRight();
                    else if(isAbleToGoDown())
                        goDown();
                    break;
                case RIGHT:
                    if (isAbleToGoUp() && isAbleToGoDown()) {
                        if (i == 1)
                            goUp();
                        else
                            goDown();
                    }
                    else if (isAbleNotToChangeDir(dir))
                        dirGo(dir);
                    else if(isAbleToGoUp())
                        goUp();
                    else if (isAbleToGoDown())
                        goDown();
                    else if(isAbleToGoLeft())
                        goLeft();
                    break;
                case LEFT:
                    if (isAbleToGoUp() && isAbleToGoDown()) {
                        if (i == 1)
                            goUp();
                        else
                            goDown();
                    }
                    else if (isAbleNotToChangeDir(dir))
                        dirGo(dir);
                    else if(isAbleToGoUp())
                        goUp();
                    else if (isAbleToGoDown())
                        goDown();
                    else if(isAbleToGoRight())
                        goRight();
                    break;
            }
        }
    }

    public void update() {
        MealClass mealTemp;
        move();
        if (Map.map[posOnMapY].charAt(posOnMapX) == '2') {
            char[] tempString;
            tempString = Map.map[posOnMapY].toCharArray();
            tempString[posOnMapX] = '0';
            Map.map[posOnMapY] = String.valueOf(tempString);
            mealTemp = findMealByCoordinate(posOnMapX, posOnMapY);
            if (mealTemp != null) {
                mainPane.getChildren().remove(mealTemp);
                recordPanel.setTwoUp(recordPanel.getTwoUpInt() + 1);
                if (recordPanel.getTwoUpInt() > recordPanel.getHighScoreInt())
                    recordPanel.setHighScore(recordPanel.getTwoUpInt());
                mealVect.remove(mealTemp);
            }
        }
    }
}
