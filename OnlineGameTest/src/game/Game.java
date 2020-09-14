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
        players = new ArrayList<>();
        projectiles = new ArrayList<>();
        generateMap();

    }

    public void generateMap() {
        obstacles.add(new Obstacle(9, 10, 20, 20));
        obstacles.add(new Obstacle(400, 10, 120, 20));
        obstacles.add(new Obstacle(400, 300, 50, 120));
        obstacles.add(new Obstacle(56, 300, 30, 60));

        players.add(new Player(10, 10));
    }

    public ArrayList<Obstacle> getObstacles() { return obstacles; }

    public ArrayList<Player> getPlayers() { return players; }

    public ArrayList<Projectile> getProjectiles() { return projectiles; }

    public int getWidth() { return WIDTH; }

    public int getHeight() { return HEIGHT; }

}
