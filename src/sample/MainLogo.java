package sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainLogo {
    Image logoImage;
    ImageView pacManLogo;
    VBox LogoBox;

    public MainLogo()
    {
        Image logoImage = new Image("sample/Pacman_logo.png");
        ImageView pacManLogo = new ImageView(logoImage);
        LogoBox = new VBox();
        LogoBox.setMinHeight(250);
        pacManLogo.setScaleX(1.2);
        pacManLogo.setScaleY(1.2);
        LogoBox.getChildren().addAll(pacManLogo);
        LogoBox.setAlignment(Pos.BOTTOM_CENTER);
    }

    public void AddToPane(GridPane pane)
    {
        pane.setConstraints(LogoBox, 1, 2);
        pane.getChildren().addAll(LogoBox);
    }
}
