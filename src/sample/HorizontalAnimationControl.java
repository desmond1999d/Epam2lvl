package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HorizontalAnimationControl {

    Image image = new Image("sample/Pacman10-hp-sprite.png");
    IconsAnimated iconsAnimated;

    public HorizontalAnimationControl(int columns, int count, int offsetX, int offsetY, int width, int height)
    {
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        iconsAnimated = new IconsAnimated(imageView, columns, count, offsetX, offsetY, width, height);
    }

    public void update()
    {
        iconsAnimated.GoLeft();
        if (iconsAnimated.getTranslateX() < -20)
            iconsAnimated.setTranslateX(iconsAnimated.getScene().getWidth() + 100);
    }

    public void Launch(Pane pane)
    {
        pane.getChildren().addAll(iconsAnimated);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    public void IconSetTranslateX(double x)
    {
        iconsAnimated.setTranslateX(x);
    }

    public void IconSetTranslateY(double y)
    {
        iconsAnimated.setTranslateY(y);
    }
}
