package client;

import game.Game;

import java.awt.*;

public class ClientContainer extends Container {

    private Game game;
    private GamePanel gamePanel;

    private int frameWidth;
    private int frameHeight;

    public ClientContainer(int nPlayers, int frameWidth, int frameHeight) {

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        game = new Game(nPlayers);
        gamePanel = new GamePanel(game, frameWidth, frameHeight);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        add(gamePanel, gc);

        setVisible(true);

    }

    public void setSize(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        gamePanel.setSize(this.frameWidth, this.frameHeight);
    }

}
