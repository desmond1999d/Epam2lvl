package sample;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * class that changes sprites once per frame.
 */

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    /**
     * Constructor
     * @param imageView sprite sheet
     * @param duration obvious
     * @param count quantity of sprites
     * @param columns quantity of columns
     * @param offsetX x offset on the spite sheet
     * @param offsetY y offset on the spite sheet
     * @param width width of an animation unit
     * @param height height of an animation unit
     */

    public SpriteAnimation(
            final ImageView imageView,
            final Duration duration,
            final int count, final int columns,
            final int offsetX, final int offsetY,
            final int width, final int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    public void setDir(final int newOffX, final int newOffY) {
        this.offsetX = newOffX;
        this.offsetY = newOffY;
    }

    /**
     * switches sprites
     * @param k obvious
     */

    protected void interpolate(final double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}