package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MealClass extends ImageView {
    private int posOnMapX;
    private int posOnMapY;

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

    public int getPosOnMapX() {
        return posOnMapX;
    }

    public int getPosOnMapY() {
        return posOnMapY;
    }
}
