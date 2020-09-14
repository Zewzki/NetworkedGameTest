package game;

import java.util.ArrayList;

public class Player {

    private static final int MOVEMENT_SPEED = 5;

    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;

    private int xPos;
    private int yPos;

    public Player(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void movePlayer(int x, int y, ArrayList<Obstacle> obstacles) {
        for (int i = 0; i < obstacles.size(); i++) {
            // right pos of player to left pos of obstacle
            if (x + PLAYER_WIDTH < obstacles.get(i).getXPos()) {
                // left pos of player to right pos of obstacle
                if (x > obstacles.get(i).getXPos() + obstacles.get(i).getWidth()) {
                    xPos = x;
                }
            }

            if (y + PLAYER_HEIGHT < obstacles.get(i).getYPos()) {
                // left pos of player to right pos of obstacle
                if (y > obstacles.get(i).getYPos() + obstacles.get(i).getHeight()) {
                    yPos = y;
                }
            }


        }

    }

}
