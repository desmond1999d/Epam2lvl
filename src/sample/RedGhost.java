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
    protected RecordPanel recordPanel;
    protected Stage primaryStage;

    public RedGhost() {
        setSprite(2, 2, 80, 80);
        aimX = 0;
        aimY = 0;
        posOnMapY = 0;
        posOnMapX = 0;
        setTranslateX(/*dist*posOnMapX*/40);
        setTranslateY(/*dist*posOnMapY*/42);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    public RedGhost(int plPosX, int plPosY, int mapPosX, int mapPosY, Player pl, RecordPanel record, Stage primary) {
        primaryStage = primary;
        recordPanel = record;
        player = pl;
        setSprite(2, 2, 80, 80);
        aimX = plPosX;
        aimY = plPosY;
        posOnMapY = mapPosY;
        posOnMapX = mapPosX;
        setTranslateX(/*dist*posOnMapX*/40*25 - 10);
        setTranslateY(/*dist*posOnMapY*/42);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    private void putInfoToFile(String path) {
        File file;
        PrintWriter printWriter;
        file = new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter = new PrintWriter(file.getAbsoluteFile());
            printWriter.println(recordPanel.getOneUpInt());
            printWriter.println(recordPanel.getHighScoreInt());
            printWriter.println(recordPanel.getTwoUpInt());
            printWriter.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
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

    protected void update() {
        System.out.println("Working");
        if(posOnMapX == player.posOnMapX && posOnMapY == player.posOnMapY) {
            putInfoToFile("GameResults.txt");
            for(int i = 1; i < Map.ySize; i++)
                Map.map[i] = Map.map[i].replace('2', '0');
            primaryStage.setScene(new MainMenu(new GridPane(), primaryStage));
            return;
        }
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
