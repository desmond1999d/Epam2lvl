package sample;

import Constants.ConstantClass;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainLogo {
    private ImageView pacManLogo;
    private VBox logoBox;

    /**
     * Constructor
     * Sets the main logo image ond position
     */

    public MainLogo() {
        Image logoImage = new Image("Sprites/Pacman_logo.png");
        pacManLogo = new ImageView(logoImage);
        logoBox = new VBox();
        logoBox.setMinHeight(ConstantClass.MAINLOGOMINHEIGHT);
        pacManLogo.setScaleX(ConstantClass.MAINLOGOSCALE);
        pacManLogo.setScaleY(ConstantClass.MAINLOGOSCALE);
        logoBox.getChildren().addAll(pacManLogo);
        logoBox.setAlignment(Pos.BOTTOM_CENTER);
    }

    /**
     * Adds main logo to the pane
     * @param pane where to add
     */

    public void addToPane(final GridPane pane) {
        pane.setConstraints(logoBox, ConstantClass.MAINLOGOCOLUMN, ConstantClass.MAINLOGORAW);
        pane.getChildren().addAll(logoBox);
    }
}
