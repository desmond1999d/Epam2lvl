package sample;

import Constants.ConstantClass;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Class that provides controls for the SpriteAnimation
 */

public class IconsAnimated extends Pane {

    private int columns;
    private int count;
    private int offset_x;
    private int offset_y;
    private int width;
    private int height;
    private SpriteAnimation animation;
    private ImageView imageView = null;

    /**
     * @param img an animation sprite sheet
     * @param col sets the quantity of columns of a sprite animation
     * @param cou sets the quantity of sprites in a sprite animation
     * @param offx sets the X position on a sprite sheet
     * @param offy sets the Y position on a sprite sheet
     * @param hei sets height of an animation unit
     * @param wid sets width of animation unit
     */

    public IconsAnimated(final ImageView img, final int col, final int cou,
                         final int offx, final int offy, final int wid, final int hei) {
        imageView = img;
        columns = col;
        count = cou;
        offset_x = offx;
        offset_y = offy;
        width = wid;
        height = hei;
        imageView.setViewport(new Rectangle2D(offset_x, offset_y, width, height));
        animation = new SpriteAnimation(
                imageView,
                Duration.millis(ConstantClass.ANIMATIONDURATIONMS),
                count, columns,
                offset_x, offset_y,
                width, height
        );
        getChildren().addAll(imageView);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public IconsAnimated() {
        imageView = new ImageView(new Image("Sprites/Pacman10-hp-sprite.png"));
        imageView.setScaleX(ConstantClass.ANIMATIONUNITSCALE);
        imageView.setScaleY(ConstantClass.ANIMATIONUNITSCALE);
        columns = 3;
        count = 3;
        offset_x = 0;
        offset_y = 0;
        width = 20;
        height = 20;
        imageView.setViewport(new Rectangle2D(offset_x, offset_y, width, height));
        animation = new SpriteAnimation(
                imageView,
                Duration.millis(ConstantClass.ANIMATIONDURATIONMS),
                count, columns,
                offset_x, offset_y,
                width, height
        );
        getChildren().addAll(imageView);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    /**
     * sets the spawn point of an animation unit
     * @param columns quantity of columns
     * @param count quantity of sprites
     * @param offsetX X position
     * @param offsetY Y position
     */

    public void setSprite(final int count, final int columns, final int offsetX, final int offsetY) {
        animation.setDir(count, columns, offsetX, offsetY);
    }

    /**
     * moves an object to the left side of a scene
     */

    public void goLeft() {
        this.setTranslateX(this.getTranslateX() - 1);
    }

    /**
     * moves an object to the right side of a scene
     */

    public void goRight() {
        this.setTranslateX(this.getTranslateX() + 1);
    }

    /**
     * moves an object to the top of a scene
     */

    public void goUp() {
        this.setTranslateY(this.getTranslateY() - 1);
    }

    /**
     * moves an object to the bottomn of a scene
     */

    public void goDown() {
        this.setTranslateY(this.getTranslateY() + 1);
    }
}
