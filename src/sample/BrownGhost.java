package sample;

import javafx.stage.Stage;

/**
 * Brown ghost class
 */

public class BrownGhost extends RedGhost {

    /**
     * @param plPosX current player x position
     * @param plPosY current player y position
     * @param pl player object (aim)
     * @param pacbot bot object (aim)
     * @param primaty primary stage from Main class
     * @param prevScene previous mapScene
     */

    public BrownGhost(int plPosX, int plPosY, Player pl, PacmanBot pacbot, Stage primaty, MapScene prevScene) {
        currentScene = prevScene;
        primaryStage = primaty;
        player = pl;
        bot = pacbot;
        setSprite(2, 2, 80, 140);
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
     * and changes it's sprite
     */

    @Override
    public void goLeft() {
        if (dir != prevDir)
            setSprite(2, 2, 80, 140);
        this.setTranslateX(this.getTranslateX() - 1);
    }

    /**
     * Translates bot positionX by (+1)
     * and changes it's sprite
     */

    @Override
    public void goRight() {
        if (dir != prevDir)
            setSprite(2, 2, 120, 140);
        this.setTranslateX(this.getTranslateX() + 1);
    }

    /**
     * Translates bot positionY by (-1)
     * and changes it's sprite
     */

    @Override
    public void goUp() {
        if (dir != prevDir)
            setSprite(2, 2, 0, 140);
        this.setTranslateY(this.getTranslateY() - 1);
    }

    /**
     * Translates bot positionY by (+1)
     * and changes it's sprite
     */

    @Override
    public void goDown() {
        if (dir != prevDir)
            setSprite(2, 2, 40, 140);
        this.setTranslateY(this.getTranslateY() + 1);
    }

    /**
     * Sets new aim for the bot
     * @param newAimX X position of an aim
     * @param newAimY Y position of an aim
     */

    public void setAim(int newAimX, int newAimY) {
        double distance;
        int distX, distY;
        distX = Math.abs(posOnMapX-newAimX);
        distY = Math.abs(posOnMapY-newAimY);
        distance = Math.sqrt(distX*distX + distY*distY);
        if(distance <= 5.65) {
            aimX = newAimX;
            aimY = newAimY;
        }
        else {
            aimX = 1;
            aimY = 8;
        }
    }
}
