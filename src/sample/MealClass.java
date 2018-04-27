package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class that represents meal and it's characteristics
 */

public class MealClass extends ImageView {
    private int posOnMapX;
    private int posOnMapY;

    /**
     * @param x X position on the map
     * @param y Y position on the map
     * @param image Image for the meal object
     */

    public MealClass(int x, int y, Image image) {
        super(image);
        setViewport(new Rectangle2D(0, 180, 10, 10));
        setScaleX(2);
        setScaleY(2);
        posOnMapX = x;
        posOnMapY = y;
        setTranslateX(35 * posOnMapX + 8);
        setTranslateY(35 * posOnMapY + 8);
    }

    /**
     * Returns X position on the map
     * @return posOnMapX
     */

    public int getPosOnMapX() {
        return posOnMapX;
    }

    /**
     * Returns Y position on the map
     * @return posOnMapY
     */

    public int getPosOnMapY() {
        return posOnMapY;
    }
}
