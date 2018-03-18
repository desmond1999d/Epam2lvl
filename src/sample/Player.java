package sample;

import javafx.animation.AnimationTimer;

enum direction
{
    LEFT,
    RIGHT,
    UP,
    DOWN,
    STABLE
}

public class Player extends IconsAnimated {

    private direction dir;
    private direction prevDir;
    private int posOnMapX;
    private int posOnMapY;
    private int wentX;
    private int wentY;
    private final int dist = Map.blockSize;
    private final int wentBorderPos = 25;
    private final int wentBorderNeg = -25;
    //private final int wentBorder = 5;

//    public int getWentBorder() {
//        return wentBorder;
//    }
//
//    public direction getDir() {
//        return dir;
//    }
//
//    public int getWentX() {
//        return wentX;
//    }
//
//    public int getWentY() {
//        return wentY;
//    }

    public Player() {
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        dir = direction.STABLE;
        posOnMapX = 1;
        posOnMapY = 1;
        setTranslateX(41);
        setTranslateY(42);
        wentX = 0;
        wentY = 0;
        prevDir = dir;
    }

    public void changeDir(direction i)
    {
        switch (i) {
            case LEFT: setRotate(0); break;
            case RIGHT: setRotate(180);  break;
            case UP: setRotate(90); break;
            case DOWN: setRotate(-90); break;
        }
        dir = i;
    }

    public void update() {
        switch (dir) {
            case UP:
                if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0') {
                    goUp();
                    wentY--;
                    if (Math.abs(wentY) >= dist) {
                        posOnMapY--;
                        System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
                        wentY = 0;
                    }
                }
                else {
                    //Поворот справа
                    if (wentX <= wentBorderNeg) {
                        if (Map.map[posOnMapY - 1].charAt(posOnMapX - 1) == '0') {
                            //сдвиг в цикле
                            while (Math.abs(wentX) <= dist) {
                                goLeft();
                                wentX--;
                            }
                            //изменение координаты
                            posOnMapX--;
                            wentX = 0;
                        }
                    }
                    //Поворот слева
                    else if (wentX >= wentBorderPos) {
                        if (Map.map[posOnMapY - 1].charAt(posOnMapX + 1) == '0') {
                            //сдвиг в цикле
                            while (Math.abs(wentX) <= dist) {
                                goRight();
                                wentX++;
                            }
                            //изменение координаты
                            posOnMapX++;
                            wentX = 0;
                        }
                    }
                }
                prevDir = dir;
                break;








            case DOWN:
                if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0') {
                    goDown();
                    wentY++;
                    if (wentY >= dist) {
                        posOnMapY++;
                        System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
                        wentY = 0;
                    }
                }
                else {
                    //Поворот справа
                    if (wentX <= wentBorderNeg) {
                        if (Map.map[posOnMapY + 1].charAt(posOnMapX - 1) == '0') {
                            //сдвиг в цикле
                            while (Math.abs(wentX) <= dist) {
                                goLeft();
                                wentX--;
                            }
                            //изменение координаты
                            posOnMapX--;
                            wentX = 0;
                        }
                    }
                    //Поворот слева
                    else if (wentX >= wentBorderPos) {
                        if (Map.map[posOnMapY + 1].charAt(posOnMapX + 1) == '0') {
                            //сдвиг в цикле
                            while (Math.abs(wentX) <= dist) {
                                goRight();
                                wentX++;
                            }
                            //изменение координаты
                            posOnMapX++;
                            wentX = 0;
                        }
                    }
                }
                prevDir = dir;
                break;






            case LEFT:
                if (Map.map[posOnMapY].charAt(posOnMapX-1) == '0') {
                    if (prevDir == direction.LEFT || prevDir == direction.RIGHT) {
                        wentX--;
                        goLeft();
                        if (Math.abs(wentX) >= dist) {
                            posOnMapX--;
                            System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
                            wentX = 0;
                        }
                    }
                }
                //Поворот
                else {
                    if (wentY <= wentBorderNeg) {
                        if (Map.map[posOnMapY-1].charAt(posOnMapX-1) == '0') {
                            while (Math.abs(wentY) <= dist) {
                                goUp();
                                wentY--;
                            }
                            posOnMapY--;
                            wentY = 0;
                        }
                    }
                    else if (wentY >= wentBorderPos) {
                        if (Map.map[posOnMapY+1].charAt(posOnMapX-1) == '0') {
                            while (wentY <= dist) {
                                goDown();
                                wentY++;
                            }
                            posOnMapY++;
                            wentY = 0;
                        }
                    }
                }
                prevDir = dir;
                break;








            case RIGHT:
                if (Map.map[posOnMapY].charAt(posOnMapX+1) == '0') {
                    if (prevDir == direction.LEFT || prevDir == direction.RIGHT) {
                        wentX++;
                        goRight();
                        if (wentX >= dist) {
                            posOnMapX++;
                            System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
                            wentX = 0;
                        }
                    }
                }
                //Поворот
                else {
                    if (wentY <= wentBorderNeg) {
                        if (Map.map[posOnMapY-1].charAt(posOnMapX+1) == '0') {
                            while (Math.abs(wentY) < dist) {
                                goUp();
                                wentY--;
                            }
                            posOnMapY--;
                            wentY = 0;
                        }
                    }
                    else if (wentY >= wentBorderPos) {
                        if (Map.map[posOnMapY+1].charAt(posOnMapX+1) == '0') {
                            while (wentY < dist) {
                                goDown();
                                wentY++;
                            }
                            posOnMapY++;
                            wentY = 0;
                        }
                    }
                }
                prevDir = dir;
                break;
        }
    }

//    public void update() {
//        switch (dir) {
//            case UP:
//                if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0') {
//                    goUp();
//                    wentY--;
//                    if (Math.abs(wentY) >= dist) {
//                        posOnMapY--;
//                        System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                        wentY = 0;
//                    }
//                    goUp();
//                    wentY--;
//                    if (Math.abs(wentY) >= dist) {
//                        posOnMapY--;
//                        System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                        wentY = 0;
//                    }
//                }
//                prevDir = dir;
//                break;
//            case DOWN:
//                if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0') {
//                    goDown();
//                    wentY++;
//                    if (wentY >= dist) {
//                        posOnMapY++;
//                        System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                        wentY = 0;
//                    }
//                    goDown();
//                    wentY++;
//                    if (wentY >= dist) {
//                        posOnMapY++;
//                        System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                        wentY = 0;
//                    }
//                }
//                prevDir = dir;
//                break;
//            case LEFT:
//                if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0') {
//                    if (prevDir == direction.LEFT || prevDir == direction.RIGHT) {
//                        wentX--;
//                        goLeft();
//                        if (Math.abs(wentX) >= dist) {
//                            posOnMapX--;
//                            System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                            wentX = 0;
//                        }
//                        wentX--;
//                        goLeft();
//                        if (Math.abs(wentX) >= dist) {
//                            posOnMapX--;
//                            System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                            wentX = 0;
//                        }
//                    }
//                }
//                prevDir = dir;
//                break;
//            case RIGHT:
//                if (Map.map[posOnMapY].charAt(posOnMapX + 1) == '0') {
//                    if (prevDir == direction.LEFT || prevDir == direction.RIGHT) {
//                        wentX++;
//                        goRight();
//                        if (wentX >= dist) {
//                            posOnMapX++;
//                            System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                            wentX = 0;
//                        }
//                        wentX++;
//                        goRight();
//                        if (wentX >= dist) {
//                            posOnMapX++;
//                            System.out.println("X: " + posOnMapX + "\nY: " + posOnMapY);
//                            wentX = 0;
//                        }
//                    }
//                }
//                prevDir = dir;
//                break;
//        }
//    }
}
