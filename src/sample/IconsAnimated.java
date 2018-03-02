package sample;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class IconsAnimated extends Pane {

    private int columns  =   3;
    private int count    =  3;
    private int offset_x =  0;
    private int offset_y =  0;
    private int width    = 20;
    private int height   = 20;
    private SpriteAnimation animation;
    private ImageView imageView = null;

    /**Constructor - creating a new object.*/
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
                Duration.millis(600),
                count, columns,
                offset_x, offset_y,
                width, height
        );
        getChildren().addAll(imageView);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public void setDirectionInfo(final int offX, final int offY) {
        animation.setDir(offX, offY);
    }

    public void goLeft () {
        this.setTranslateX(this.getTranslateX() - 1);
    }
}
