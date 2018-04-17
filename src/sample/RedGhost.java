package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class RedGhost extends IconsAnimated {
    protected int aimX;
    protected int aimY;
    protected int posOnMapX;
    protected int posOnMapY;
    protected direction dir = direction.STABLE;
    protected direction prevDir = direction.STABLE;
    protected final int dist = Map.blockSize;
    protected boolean going = false;
    protected int went = 0;
    protected direction whereToGo = direction.STABLE;
    protected Player player = null;
    protected PacmanBot bot = null;
    protected Stage primaryStage;
    protected int startPosX;
    protected int startPosY;
    protected int startPosOnMapX;
    protected int startPosOnMapY;
    public final AnimationTimer timer;
    protected MapScene currentScene;

    public RedGhost() {
        setSprite(2, 2, 80, 80);
        aimX = 0;
        aimY = 0;
        posOnMapY = 0;
        posOnMapX = 0;
        startPosX = 40;
        startPosY = 42;
        startPosOnMapX = 1;
        startPosOnMapY = 1;
        setTranslateX(startPosX);
        setTranslateY(startPosY);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
    }

    public RedGhost(int plPosX, int plPosY, Player pl, PacmanBot pacbot, Stage primary, MapScene mapScene) {
        currentScene = mapScene;
        primaryStage = primary;
        player = pl;
        bot = pacbot;
        setSprite(2, 2, 80, 80);
        aimX = plPosX;
        aimY = plPosY;
        posOnMapY = 1;
        posOnMapX = 28;
        startPosOnMapX = 28;
        startPosOnMapY = 1;
        startPosX = 40*25 - 10;
        startPosY = 42;
        setTranslateX(startPosX);
        setTranslateY(startPosY);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
    }

    public void reset() {
        setTranslateX(startPosX);
        setTranslateY(startPosY);
        posOnMapX = startPosOnMapX;
        posOnMapY = startPosOnMapY;
        went = 0;
        going = false;
        dir = direction.STABLE;
        prevDir = direction.STABLE;
        whereToGo = direction.STABLE;
    }

    @Override
    public void goLeft() {
        if (dir != prevDir)
            setSprite(2, 2, 80, 80);
        this.setTranslateX(this.getTranslateX() - 1);
    }

    @Override
    public void goRight() {
        if (dir != prevDir)
            setSprite(2, 2, 120, 80);
        this.setTranslateX(this.getTranslateX() + 1);
    }

    @Override
    public void goUp() {
        if (dir != prevDir)
            setSprite(2, 2, 0, 80);
        this.setTranslateY(this.getTranslateY() - 1);
    }

    @Override
    public void goDown() {
        if (dir != prevDir)
            setSprite(2, 2, 40, 80);
        this.setTranslateY(this.getTranslateY() + 1);
    }

    public void setAim(int newAimX, int newAimY)
    {
        aimX = newAimX;
        aimY = newAimY;
    }

    protected direction decideWichWay() {
        if ((Math.abs(aimX - posOnMapX)) >= Math.abs(aimY - posOnMapY)) {
            if ((aimX - posOnMapX) > 0)
                return direction.RIGHT;
            else
                return direction.LEFT;
        }
        else {
            if ((aimY - posOnMapY) > 0)
                return direction.DOWN;
            else
                return direction.UP;
        }
    }

    protected direction findWayDown()
    {
        int offset = 1;
        if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX) == '2')
            return direction.DOWN;
        else if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX - 1) == '2' ||
                Map.map[posOnMapY].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX + 1) == '2') {
            while (true) {
                if (Map.map[posOnMapY + 1].charAt(posOnMapX - offset) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX - offset) == '2') {
                    return direction.LEFT;
                }
                if (Map.map[posOnMapY + 1].charAt(posOnMapX + offset) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX + offset) == '2') {
                    return direction.RIGHT;
                }
                offset++;
                if((posOnMapX - offset) < 0)
                    return direction.RIGHT;
                else if((posOnMapX + offset) > (Map.xSize - 1))
                    return direction.LEFT;
            }
        }
        else if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX) == '2'){
            return direction.UP;
        }
        return direction.STABLE;
    }

    protected direction findWayUp()
    {
        int offset = 1;
        if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX) == '2')
            return direction.UP;
        else if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX - 1) == '2' ||
                Map.map[posOnMapY].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX + 1) == '2') {
            while (true) {
                if (Map.map[posOnMapY - 1].charAt(posOnMapX - offset) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX - offset) == '2') {
                    return direction.LEFT;
                }
                if (Map.map[posOnMapY - 1].charAt(posOnMapX + offset) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX + offset) == '2') {
                    return direction.RIGHT;
                }
                offset++;
                if((posOnMapX - offset) < 0)
                    return direction.RIGHT;
                else if((posOnMapX + offset) > (Map.xSize - 1))
                    return direction.LEFT;
            }
        }
        else if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX) == '2'){
            return direction.DOWN;
        }
        return direction.STABLE;
    }

    protected direction findWayRight()
    {
        int offset = 1;
        if (Map.map[posOnMapY].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX + 1) == '2')
            return direction.RIGHT;
        else if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX) == '2' ||
                Map.map[posOnMapY - 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX) == '2') {
            while (true) {
                if (Map.map[posOnMapY - offset].charAt(posOnMapX) == '0' || Map.map[posOnMapY - offset].charAt(posOnMapX) == '2') {
                    return direction.UP;
                }
                if (Map.map[posOnMapY + offset].charAt(posOnMapX) == '0' || Map.map[posOnMapY + offset].charAt(posOnMapX) == '2') {
                    return direction.DOWN;
                }
                offset++;
                if((posOnMapY - offset) < 0)
                    return direction.DOWN;
                else if((posOnMapY + offset) > (Map.ySize - 1))
                    return direction.UP;
            }
        }
        else if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX - 1) == '2'){
            return direction.LEFT;
        }
        return direction.STABLE;
    }

    protected direction findWayLeft()
    {
        int offset = 1;
        if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX - 1) == '2')
            return direction.LEFT;
        else if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY + 1].charAt(posOnMapX) == '2' ||
                Map.map[posOnMapY - 1].charAt(posOnMapX) == '0' || Map.map[posOnMapY - 1].charAt(posOnMapX) == '2') {
            while (true) {
                if (Map.map[posOnMapY - offset].charAt(posOnMapX) == '0' || Map.map[posOnMapY - offset].charAt(posOnMapX) == '2') {
                    return direction.UP;
                }
                if (Map.map[posOnMapY + offset].charAt(posOnMapX) == '0' || Map.map[posOnMapY + offset].charAt(posOnMapX) == '2') {
                    return direction.DOWN;
                }
                offset++;
                if((posOnMapY - offset) < 0)
                    return direction.DOWN;
                else if((posOnMapY + offset) > (Map.ySize - 1))
                    return direction.UP;
            }
        }
        else if (Map.map[posOnMapY].charAt(posOnMapX + 1) == '0' || Map.map[posOnMapY].charAt(posOnMapX + 1) == '2'){
            return direction.RIGHT;
        }
        return direction.STABLE;
    }

    protected void isDead() {
        if (player != null && bot == null) {
            if (posOnMapX == player.posOnMapX && posOnMapY == player.posOnMapY) {
                currentScene.exit = true;
                return;
            }
        }
        else if (bot != null && player == null) {
            if (posOnMapX == bot.posOnMapX && posOnMapY == bot.posOnMapY) {
                currentScene.exit = true;
                return;
            }
        }
    }

    protected void update() {
        if (went == 0)
            isDead();
        if(!going) {
            prevDir = dir;
            dir = decideWichWay();
            switch (dir) {
                case DOWN:
                    whereToGo = findWayDown();
                    break;
                case UP:
                    whereToGo = findWayUp();
                    break;
                case LEFT:
                    whereToGo = findWayLeft();
                    break;
                case RIGHT:
                    whereToGo = findWayRight();
                    break;
            }
            going = true;
        }
        else {
            switch (whereToGo) {
                case DOWN:
                    goDown();
                    break;
                case UP:
                    goUp();
                    break;
                case LEFT:
                    goLeft();
                    break;
                case RIGHT:
                    goRight();
                    break;
            }
            went++;
            if(went == dist) {
                going = false;
                switch (whereToGo) {
                    case DOWN:
                        posOnMapY++;
                        break;
                    case UP:
                        posOnMapY--;
                        break;
                    case LEFT:
                        posOnMapX--;
                        break;
                    case RIGHT:
                        posOnMapX++;
                        break;
                }
                //System.out.println("X: " + posOnMapX + " Y: " + posOnMapY + "\n");
                went = 0;
            }
        }
    }
}
