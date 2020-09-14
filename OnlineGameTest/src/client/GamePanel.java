package client;

import game.Game;
import game.Obstacle;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    private int panelWidth;
    private int panelHeight;

    public GamePanel(Game game, int panelWidth, int panelHeight) {

        this.game = game;

        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

    }

    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        float xMapping = (float) (panelWidth) / (float) (game.getWidth());
        float yMapping = (float) (panelHeight) / (float) (game.getHeight());

        for (Obstacle o : game.getObstacles()) {

            g2d.setColor(o.getColor());
            g2d.setStroke(new BasicStroke(4));

            int x = o.getXPos();
            int y = o.getYPos();
            int w = o.getWidth();
            int h = o.getHeight();

            g2d.drawRect(x, y, w, h);

        }

    }

    public void setSize(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        repaint();
    }

}
