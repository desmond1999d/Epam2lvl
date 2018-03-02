package sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainLogo {
    private Image logoImage;
    private ImageView pacManLogo;
    private VBox logoBox;

    public MainLogo() {
        logoImage = new Image("sample/Pacman_logo.png");
        pacManLogo = new ImageView(logoImage);
        logoBox = new VBox();
        logoBox.setMinHeight(250);
        pacManLogo.setScaleX(1.2);
        pacManLogo.setScaleY(1.2);
        logoBox.getChildren().addAll(pacManLogo);
        logoBox.setAlignment(Pos.BOTTOM_CENTER);
    }

    public void addToPane(final GridPane pane) {
        pane.setConstraints(logoBox, 1, 2);
        pane.getChildren().addAll(logoBox);
    }
}
