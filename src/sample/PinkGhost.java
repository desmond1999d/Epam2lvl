package sample;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class PinkGhost extends RedGhost {

    public PinkGhost(int plPosX, int plPosY, Player pl, PacmanBot pacbot, Stage primary, MapScene prevScene) {
        currentScene = prevScene;
        primaryStage = primary;
        player = pl;
        bot = pacbot;
        setSprite(2, 2, 80, 100);
        aimX = plPosX;
        aimY = plPosY;
        startPosOnMapX = 28;
        startPosOnMapY = 1;
        posOnMapY = 1;
        posOnMapX = 28;
        startPosX = 40*25 - 10;
        startPosY = 42;
        setTranslateX(startPosX);
        setTranslateY(startPosY);
    }

    @Override
    public void goLeft() {
        if (dir != prevDir)
            setSprite(2, 2, 80, 100);
        this.setTranslateX(this.getTranslateX() - 1);
    }

    @Override
    public void goRight() {
        if (dir != prevDir)
            setSprite(2, 2, 120, 100);
        this.setTranslateX(this.getTranslateX() + 1);
    }

    @Override
    public void goUp() {
        if (dir != prevDir)
            setSprite(2, 2, 0, 100);
        this.setTranslateY(this.getTranslateY() - 1);
    }

    @Override
    public void goDown() {
        if (dir != prevDir)
            setSprite(2, 2, 40, 100);
        this.setTranslateY(this.getTranslateY() + 1);
    }

    public void setAim(int newAimX, int newAimY, direction dir) {
        switch (dir) {
            case DOWN:
                aimX = newAimX;
                if((newAimY + 4) > (Map.ySize - 1))
                    aimY = newAimY;
                else
                    aimY = newAimY + 4;
                break;
            case UP:
                aimX = newAimX;
                if((newAimY - 4) < 0)
                    aimY = newAimY;
                else
                    aimY = newAimY - 4;
                break;
            case RIGHT:
                aimY = newAimY;
                if((newAimX+4) > (Map.xSize - 1))
                    aimX = newAimX;
                else
                    aimX = newAimX + 4;
                break;
            case LEFT:
                aimY = newAimY;
                if((newAimX-4) < 0)
                    aimX = newAimX;
                else
                    aimX = newAimX - 4;
                break;
        }
        //System.out.println("aimX " + aimX + " aimY " + aimY + " dir " + dir + "\n");
    }
}
