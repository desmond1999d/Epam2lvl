package sample;

import javafx.scene.layout.Pane;
import javafx.stage.Screen;

/**
 * animation of 4 units.
 */

public class MainMenuAnimation {

    private HorizontalAnimationControl animationControl1;
    private HorizontalAnimationControl animationControl2;
    private HorizontalAnimationControl animationControl3;
    private HorizontalAnimationControl animationControl4;

    /**
     * Constructor
     * @param pane where to add animation
     * @param posY Y offset in the pane
     */

    public MainMenuAnimation(final Pane pane, final int posY) {
        animationControl1 = new HorizontalAnimationControl(3, 3, 0, 0, 20, 20);
        animationControl2 = new HorizontalAnimationControl(2, 2, 80, 80, 20, 20);
        animationControl3 = new HorizontalAnimationControl(2, 2, 80, 100, 20, 20);
        animationControl4 = new HorizontalAnimationControl(2, 2, 80, 120, 20, 20);
        set(posY);
        activate(pane);
    }

    /**
     * moves all 4 animation units to the prefered position
     * @param posY an Y position of placement
     */

    private void set(final int posY) {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        animationControl1.iconSetTranslateX(screenWidth+100);
        animationControl2.iconSetTranslateX(screenWidth+200);
        animationControl3.iconSetTranslateX(screenWidth+250);
        animationControl4.iconSetTranslateX(screenWidth+300);
        animationControl1.iconSetTranslateY(posY);
        animationControl2.iconSetTranslateY(posY);
        animationControl3.iconSetTranslateY(posY);
        animationControl4.iconSetTranslateY(posY);
    }

    /**
     * launches all the 4 animation units
     * @param pane where to launch
     */

    private void activate(final Pane pane) {
        animationControl1.launch(pane);
        animationControl2.launch(pane);
        animationControl3.launch(pane);
        animationControl4.launch(pane);
    }
}
