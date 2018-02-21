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

    private int Columns  =   3;
    private int Count    =  3;
    private int Offset_x =  0;
    private int Offset_y =  0;
    private int Width    = 20;
    private int Height   = 20;
    private SpriteAnimation animation;
    private ImageView imageView = null;

    public IconsAnimated(ImageView img, int Col, int Cou, int Offx, int Offy, int Wid, int Hei) {
        imageView = img;
        Columns = Col;
        Count = Cou;
        Offset_x = Offx;
        Offset_y = Offy;
        Width = Wid;
        Height = Hei;
        imageView.setViewport(new Rectangle2D(Offset_x, Offset_y, Width, Height));
        animation = new SpriteAnimation(
                imageView,
                Duration.millis(800),
                Count, Columns,
                Offset_x, Offset_y,
                Width, Height
        );
        getChildren().addAll(imageView);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public void Go ()
    {
        this.setTranslateX(this.getTranslateX() - 1);
    }
}