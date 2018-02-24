package sample;

import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class MainMenuAnimation {

    private HorizontalAnimationControl animationControl1;
    private HorizontalAnimationControl animationControl2;
    private HorizontalAnimationControl animationControl3;
    private HorizontalAnimationControl animationControl4;

    public MainMenuAnimation(Pane pane)
    {
        animationControl1 = new HorizontalAnimationControl(3, 3, 0, 0, 20, 20);
        animationControl2 = new HorizontalAnimationControl(2, 2, 80, 80, 20, 20);
        animationControl3 = new HorizontalAnimationControl(2, 2, 80, 100, 20, 20);
        animationControl4 = new HorizontalAnimationControl(2, 2, 80, 120, 20, 20);
        Set();
        Activate(pane);
    }

    public void Set()
    {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        animationControl1.IconSetTranslateX(screenWidth+100);
        animationControl2.IconSetTranslateX(screenWidth+200);
        animationControl3.IconSetTranslateX(screenWidth+250);
        animationControl4.IconSetTranslateX(screenWidth+300);
        animationControl1.IconSetTranslateY(500);
        animationControl2.IconSetTranslateY(500);
        animationControl3.IconSetTranslateY(500);
        animationControl4.IconSetTranslateY(500);
    }

    public void Activate(Pane pane)
    {
        animationControl1.Launch(pane);
        animationControl2.Launch(pane);
        animationControl3.Launch(pane);
        animationControl4.Launch(pane);
    }
}
