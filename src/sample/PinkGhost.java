package sample;

import javafx.stage.Stage;

/**
 * Pink ghost class whith a unique logics
 */

public class PinkGhost extends RedGhost {

    /**
     * @param plPosX X position of a player
     * @param plPosY Y position of a player
     * @param pl player object
     * @param pacbot the aim of a bot if emulation is chosen
     * @param primary primary stage from Main class
     * @param prevScene main menu scene
     */

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

    /**
     * Translates bot positionX by (-1)
     */

    @Override
    public void goLeft() {
        if (dir != prevDir)
            setSprite(2, 2, 80, 100);
        this.setTranslateX(this.getTranslateX() - 1);
    }

    /**
     * Translates bot positionX by (+1)
     */

    @Override
    public void goRight() {
        if (dir != prevDir)
            setSprite(2, 2, 120, 100);
        this.setTranslateX(this.getTranslateX() + 1);
    }

    /**
     * Translates bot positionY by (-1)
     */

    @Override
    public void goUp() {
        if (dir != prevDir)
            setSprite(2, 2, 0, 100);
        this.setTranslateY(this.getTranslateY() - 1);
    }

    /**
     * Translates bot positionY by (+1)
     */

    @Override
    public void goDown() {
        if (dir != prevDir)
            setSprite(2, 2, 40, 100);
        this.setTranslateY(this.getTranslateY() + 1);
    }

    /**
     * Tries to appear in front of a player (player direction + 4)
     * @param newAimX new aim's X position
     * @param newAimY new aim's Y position
     * @param dir direction of an aim
     */

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
    }
}
