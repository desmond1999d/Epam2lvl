package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class HorizontalAnimationControl {

    private Image image = new Image("sample/Pacman10-hp-sprite.png");
    private IconsAnimated iconsAnimated;

    HorizontalAnimationControl(final int columns, final int count, final int offsetX,
                               final int offsetY, final int width, final int height) {
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        iconsAnimated = new IconsAnimated(imageView, columns, count, offsetX, offsetY, width, height);
    }

    public void update() {
        iconsAnimated.goLeft();
        if (iconsAnimated.getTranslateX() < -20) {
            iconsAnimated.setTranslateX(iconsAnimated.getScene().getWidth() + 100);
        }
    }

    public void launch(Pane pane) {
        pane.getChildren().addAll(iconsAnimated);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    public void iconSetTranslateX(final double x) {
        iconsAnimated.setTranslateX(x);
    }

    public void iconSetTranslateY(final double y) {
        iconsAnimated.setTranslateY(y);
    }
}
