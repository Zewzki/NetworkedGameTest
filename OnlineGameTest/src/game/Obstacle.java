package game;

import java.awt.*;

public class Obstacle {

    private static final Color COLOR = new Color(9,2,1);

    private final int OBSTACLE_WIDTH;
    private final int OBSTACLE_HEIGHT;

    private int xPos;
    private int yPos;

    public Obstacle(int x, int y, int h, int w) {
        xPos = x;
        yPos = y;
        OBSTACLE_HEIGHT = h;
        OBSTACLE_WIDTH = w;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getWidth() {
        return OBSTACLE_WIDTH;
    }

    public int getHeight() {
        return OBSTACLE_HEIGHT;
    }

    public Color getColor() {
        return COLOR;
    }
}
