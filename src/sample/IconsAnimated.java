package sample;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    public IconsAnimated(ImageView img, int Col, int Cou, int Offx, int Offy, int Wid, int Hei) {
        imageView = img;
        columns = Col;
        count = Cou;
        offset_x = Offx;
        offset_y = Offy;
        width = Wid;
        height = Hei;
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

    public void setDirectionInfo(int offX, int offY)
    {
        animation.setDir(offX, offY);
    }

    public void goLeft ()
    {
        this.setTranslateX(this.getTranslateX() - 1);
    }
}