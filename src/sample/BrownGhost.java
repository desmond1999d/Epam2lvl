package sample;

import javafx.stage.Stage;

public class BrownGhost extends RedGhost {
    public BrownGhost(int plPosX, int plPosY, int mapPosX, int mapPosY, Player pl, RecordPanel record, Stage primaty) {
        primaryStage = primaty;
        recordPanel = record;
        player = pl;
        setSprite(2, 2, 80, 140);
        aimX = plPosX;
        aimY = plPosY;
        posOnMapY = mapPosY;
        posOnMapX = mapPosX;
        setTranslateX(/*dist*posOnMapX*/40*25 - 10);
        setTranslateY(/*dist*posOnMapY*/42);
    }

    @Override
    public void goLeft() {
        if (dir != prevDir)
            setSprite(2, 2, 80, 140);
        this.setTranslateX(this.getTranslateX() - 1);
    }

    @Override
    public void goRight() {
        if (dir != prevDir)
            setSprite(2, 2, 120, 140);
        this.setTranslateX(this.getTranslateX() + 1);
    }

    @Override
    public void goUp() {
        if (dir != prevDir)
            setSprite(2, 2, 0, 140);
        this.setTranslateY(this.getTranslateY() - 1);
    }

    @Override
    public void goDown() {
        if (dir != prevDir)
            setSprite(2, 2, 40, 140);
        this.setTranslateY(this.getTranslateY() + 1);
    }

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
        //System.out.println("aimX " + aimX + " aimY " + aimY + " dir " + dir + "\n");
    }
}