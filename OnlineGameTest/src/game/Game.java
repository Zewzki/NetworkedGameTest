package game;

import java.util.ArrayList;

public class Game {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    private int nPlayers;

    private ArrayList<Obstacle> obstacles;
    private ArrayList<Player> players;
    private ArrayList<Projectile> projectiles;

    public Game(int nPlayers) {

        this.nPlayers = nPlayers;
        obstacles = new ArrayList<>();

    }

    public void test() {}

}
