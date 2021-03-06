package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * class builds map and adds meal onto it
 */

public class Map {
    public static String[] map = new String[]
            {
                    "111111111111111111111111111111",//0
                    "100000000000000000000000000001",//1
                    "101011110111111101110101101101",//2
                    "100010000000000000000100000001",//3
                    "111010110111011101010101110111",//4
                    "100010010131013101110101310001",//5
                    "111011110111011100000101110111",//6
                    "100000000000000001010000000001",//7
                    "101011111010111101110111101101",//8
                    "100000000010000000000000000001",//9
                    "111111111111111111111111111111",//10

//                    "1111111111111111111111111111111111111111111111111111111111",//0
//                    "1000000000000000000000000000000000000000000000000000000001",//1
//                    "1000000000000000000000000000000000000000000000000000000001",//2
//                    "1001100111111110011111111111111001111110011001111001111001",//3
//                    "1000000111111110000000000000000000000000011000000000000001",//4
//                    "1000000110000000000000000000000000000000011000000000000001",//5
//                    "1111100110000000011111100111111001111110011001111110011111",//6
//                    "1111100110011110011111100111111001111110011001111110011111",//7
//                    "1000000110000110011111100111111001111110011001111110000001",//8
//                    "1000000110000110011111100111111001111110011001111110000001",//9
//                    "1111100111111110011111100111111000000000011001111110011111",//10
//                    "1111100111111110011111100111111000000000011001111110011111",//11
//                    "1000000000000000000000000000000001111110000000000000000001",//12
//                    "1000000000000000000000000000000000011000000000000000000001",//12
//                    "1001100111111111100110011111111000011000011111111001111001",//13
//                    "1000000000000000000110000000000000000000000000000000000001",//14
//                    "1000000000000000000110000000000000000000000000000000000001",//15
//                    "1111111111111111111111111111111111111111111111111111111111",//16
            };
    public static final int xSize = 30;
    public static final int ySize = 11;
    public static final int blockSize = 35;
    private Image wallImg = new Image("Sprites/56ca90efe59291f.png", blockSize, blockSize, false, false);
    private Image pathImg = new Image("Sprites/untitled.png", blockSize, blockSize, false, false);
    private ImageView wall;
    private ImageView path;

    /**
     * Allocates meal and map blocks onto the pane
     * @param pane where to allocate map
     */

    public Map(Pane pane) {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (map[i].charAt(j) == '1') {
                    wall = new ImageView(wallImg);
                    wall.setTranslateX(j * blockSize);
                    wall.setTranslateY(i * blockSize);
                    pane.getChildren().addAll(wall);
                } else if (map[i].charAt(j) == '0' || map[i].charAt(j) == '3') {
                    path = new ImageView(pathImg);
                    path.setTranslateX(j * blockSize);
                    path.setTranslateY(i * blockSize);
                    pane.getChildren().addAll(path);
                }
            }
        }
    }
}
