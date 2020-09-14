package client;

import game.Game;

import java.awt.*;

public class ClientContainer extends Container {

    private Game game;
    private GamePanel gamePanel;

    public ClientContainer(int nPlayers) {

        game = new Game(nPlayers);
        gamePanel = new GamePanel(game);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        add(gamePanel, gc);

    }


}
