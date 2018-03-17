package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;

enum direction
{
    LEFT,
    RIGHT,
    UP,
    DOWN,
    STABLE
}

public class Player extends IconsAnimated {

    private direction dir;
    private int posOnMapX;
    private int posOnMapY;
    private double wentX;
    private double wentY;

    public Player() {
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        dir = direction.STABLE;
        posOnMapX = 1;
        posOnMapY = 1;
        setTranslateX(40);
        setTranslateY(33);
        wentX = 0;
        wentY = 0;
    }

    public void changeDir(direction i)
    {
        switch (i) {
            case LEFT: setRotate(0); break;
            case RIGHT: setRotate(180);  break;
            case UP: setRotate(90); break;
            case DOWN: setRotate(-90); break;
        }
        dir = i;
    }

    @Override
    public void goLeft() {
        this.setTranslateX(this.getTranslateX() - 1);
        wentX -= 1;
        if (wentX <= -32) {
            posOnMapX--;
            System.out.println("X: " + posOnMapX);
            System.out.println("Y: " + posOnMapY);
            wentX = 0;
        }
    }
    @Override
    public void goRight() {
        this.setTranslateX(this.getTranslateX() + 1);
        wentX += 1;
        if (wentX >= 32) {
            posOnMapX++;
            System.out.println("X: " + posOnMapX);
            System.out.println("Y: " + posOnMapY);
            wentX = 0;
        }
    }
    @Override
    public void goUp() {
        this.setTranslateY(this.getTranslateY() - 1);
        wentY -= 1;
        System.out.println("wentY: " + wentY);
        if (wentY <= -21) {
            posOnMapY--;
            System.out.println("X: " + posOnMapX);
            System.out.println("Y: " + posOnMapY);
            wentY = 0;
        }
    }
    @Override
    public void goDown() {
        this.setTranslateY(this.getTranslateY() + 1);
        wentY += 1;
        System.out.println("wentY: " + wentY);
        if (wentY >= 21) {
            posOnMapY++;
            System.out.println("X: " + posOnMapX);
            System.out.println("Y: " + posOnMapY);
            wentY = 0;
        }
    }

    public void update() {
        if (posOnMapX > Map.xSize || posOnMapY > Map.ySize || posOnMapX < 0 || posOnMapY < 0) {
            return;
        }
       switch (dir) {
           case UP:
               if (Map.map[posOnMapY-1].charAt(posOnMapX)=='0') {
                   goUp();
                   goUp();
               }
               break;
           case DOWN:
               if (Map.map[posOnMapY+1].charAt(posOnMapX)=='0') {
                   goDown();
                   goDown();
               }
               break;
           case LEFT:
               if (Map.map[posOnMapY].charAt(posOnMapX-1)=='0') {
                   goLeft();
                   goLeft();
               }
               break;
           case RIGHT:
               if (Map.map[posOnMapY].charAt(posOnMapX+1)=='0') {
                   goRight();
                   goRight();
               }
               break;
       }
    }
}
