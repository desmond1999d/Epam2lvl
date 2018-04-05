package sample;

import javafx.animation.AnimationTimer;

public class RedGhost extends IconsAnimated {
    private int aimX;
    private int aimY;
    private int posOnMapX;
    private int posOnMapY;
    private direction dir = direction.STABLE;
    private direction prevDir = direction.STABLE;
    private final int dist = Map.blockSize;
    private boolean going = false;
    private int went = 0;
    private direction whereToGo = direction.STABLE;

    public RedGhost(int plPosX, int plPosY, int mapPosX, int mapPosY) {
        setSprite(2, 2, 80, 80);
        aimX = plPosX;
        aimY = plPosY;
        posOnMapY = mapPosY;
        posOnMapX = mapPosX;
        setTranslateX(/*dist*posOnMapX*/40*25 - 10);
        setTranslateY(/*dist*posOnMapY*/42);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    @Override
    public void goLeft() {
        if (dir != prevDir)
            setSprite(2, 2, 80, 80);
        this.setTranslateX(this.getTranslateX() - 1);
    }

    @Override
    public void goRight() {
        if (dir != prevDir)
            setSprite(2, 2, 120, 80);
        this.setTranslateX(this.getTranslateX() + 1);
    }

    @Override
    public void goUp() {
        if (dir != prevDir)
            setSprite(2, 2, 0, 80);
        this.setTranslateY(this.getTranslateY() - 1);
    }

    @Override
    public void goDown() {
        if (dir != prevDir)
            setSprite(2, 2, 40, 80);
        this.setTranslateY(this.getTranslateY() + 1);
    }

    public void setAim(int newAimX, int newAimY)
    {
        aimX = newAimX;
        aimY = newAimY;
    }

    private direction decideWichWay() {
        if ((Math.abs(aimX - posOnMapX)) >= Math.abs(aimY - posOnMapY)) {
            if ((aimX - posOnMapX) > 0)
                return direction.RIGHT;
            else
                return direction.LEFT;
        }
        else {
            if ((aimY - posOnMapY) > 0)
                return direction.DOWN;
            else
                return direction.UP;
        }
    }

    private direction findWayDown()
    {
        int offset = 1;
        direction where;
        if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0')
            return direction.DOWN;
        else if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' ||
                Map.map[posOnMapY].charAt(posOnMapX + 1) == '0') {
            while (true) {
                if (Map.map[posOnMapY + 1].charAt(posOnMapX - offset) == '0') {
                    return direction.LEFT;
                }
                if (Map.map[posOnMapY + 1].charAt(posOnMapX + offset) == '0') {
                    return direction.RIGHT;
                }
                offset++;
            }
        }
        else if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0'){
            return direction.UP;
        }
        return direction.STABLE;
    }

    private direction findWayUp()
    {
        int offset = 1;
        direction where;
        if (Map.map[posOnMapY - 1].charAt(posOnMapX) == '0')
            return direction.UP;
        else if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0' ||
                Map.map[posOnMapY].charAt(posOnMapX + 1) == '0') {
            while (true) {
                if (Map.map[posOnMapY - 1].charAt(posOnMapX - offset) == '0') {
                    return direction.LEFT;
                }
                if (Map.map[posOnMapY - 1].charAt(posOnMapX + offset) == '0') {
                    return direction.RIGHT;
                }
                offset++;
            }
        }
        else if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0'){
            return direction.DOWN;
        }
        return direction.STABLE;
    }

    private direction findWayRight()
    {
        int offset = 1;
        direction where;
        if (Map.map[posOnMapY].charAt(posOnMapX + 1) == '0')
            return direction.RIGHT;
        else if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' ||
                Map.map[posOnMapY - 1].charAt(posOnMapX) == '0') {
            while (true) {
                if (Map.map[posOnMapY - offset].charAt(posOnMapX) == '0') {
                    return direction.UP;
                }
                if (Map.map[posOnMapY + offset].charAt(posOnMapX) == '0') {
                    return direction.DOWN;
                }
                offset++;
            }
        }
        else if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0'){
            return direction.LEFT;
        }
        return direction.STABLE;
    }

    private direction findWayLeft()
    {
        int offset = 1;
        direction where;
        if (Map.map[posOnMapY].charAt(posOnMapX - 1) == '0')
            return direction.LEFT;
        else if (Map.map[posOnMapY + 1].charAt(posOnMapX) == '0' ||
                Map.map[posOnMapY - 1].charAt(posOnMapX) == '0') {
            while (true) {
                if (Map.map[posOnMapY - offset].charAt(posOnMapX) == '0') {
                    return direction.UP;
                }
                if (Map.map[posOnMapY + offset].charAt(posOnMapX) == '0') {
                    return direction.DOWN;
                }
                offset++;
            }
        }
        else if (Map.map[posOnMapY].charAt(posOnMapX + 1) == '0'){
            return direction.RIGHT;
        }
        return direction.STABLE;
    }

    private void update() {
        if(!going) {
            prevDir = dir;
            dir = decideWichWay();
            switch (dir) {
                case DOWN:
                    whereToGo = findWayDown();
                    break;
                case UP:
                    whereToGo = findWayUp();
                    break;
                case LEFT:
                    whereToGo = findWayLeft();
                    break;
                case RIGHT:
                    whereToGo = findWayRight();
                    break;
            }
            going = true;
        }
        else {
            switch (whereToGo) {
                case DOWN:
                    goDown();
                    break;
                case UP:
                    goUp();
                    break;
                case LEFT:
                    goLeft();
                    break;
                case RIGHT:
                    goRight();
                    break;
            }
            went++;
            if(went == dist) {
                going = false;
                switch (whereToGo) {
                    case DOWN:
                        posOnMapY++;
                        break;
                    case UP:
                        posOnMapY--;
                        break;
                    case LEFT:
                        posOnMapX--;
                        break;
                    case RIGHT:
                        posOnMapX++;
                        break;
                }
                System.out.println("X: " + posOnMapX + " Y: " + posOnMapY + "\n");
                went = 0;
            }
        }
    }
}
