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

    public Player() {
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        dir = direction.STABLE;
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

    public void update() {
       switch (dir) {
           case UP: goUp(); goUp(); break;
           case DOWN: goDown(); goDown(); break;
           case LEFT: goLeft(); goLeft(); break;
           case RIGHT: goRight(); goRight(); break;
       }
    }
}
