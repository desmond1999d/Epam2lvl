package sample;

import Constants.ConstantClass;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * class that controls horizontal movements
 * for an animation.
 * @
 */

class HorizontalAnimationControl {

    private final Image image = new Image("Sprites/Pacman10-hp-sprite.png");
    private IconsAnimated iconsAnimated;

    /**
     * Constructor for the class.
     * sets the scale of an image and
     * creates an element with sprite animation
     * @param columns sets the quantity of columns of a sprite animation
     * @param count sets the quantity of sprites in a sprite animation
     * @param offsetX sets the X position on a sprite sheet
     * @param offsetY sets the Y position on a sprite sheet
     * @param height sets height of an animation unit
     * @param width sets width of animation unit
     */

    HorizontalAnimationControl(final int columns, final int count, final int offsetX,
                               final int offsetY, final int width, final int height) {
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(ConstantClass.ANIMATIONUNITSCALE);
        imageView.setScaleY(ConstantClass.ANIMATIONUNITSCALE);
        iconsAnimated = new IconsAnimated(imageView, columns, count, offsetX, offsetY, width, height);
    }

    /**
     * Method that is called once per frame
     * Analizes the position of an animation unit and sends it back
     * to the left border of the screen
     */

    public void update() {
        iconsAnimated.goLeft();
        if (iconsAnimated.getTranslateX() < ConstantClass.LEFTBORDEROFFSET) {
            iconsAnimated.setTranslateX(iconsAnimated.getScene().getWidth() + ConstantClass.RIGHTBORDEROFFSET);
        }
    }

    /**
     * adds animation unit to the pane
     * and launches update() method once per frame
     * @param pane plase, where animation unit will be plased
     */

    public void launch(final Pane pane) {
        pane.getChildren().addAll(iconsAnimated);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    /**
     * sets the X position of an animation unit
     * @param x
     */

    public void iconSetTranslateX(final double x) {
        iconsAnimated.setTranslateX(x);
    }

    /**
     * sets the Y position of an animation unit
     * @param y
     */

    public void iconSetTranslateY(final double y) {
        iconsAnimated.setTranslateY(y);
    }
}
