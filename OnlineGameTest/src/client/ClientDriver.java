package client;

import javax.swing.*;

public class ClientDriver {

    public static void main(String[] args) {

        int w = 800;
        int h = 600;

        ClientContainer cc = new ClientContainer(2);

        JFrame frame = new JFrame();
        frame.setTitle("Online Game");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cc);
        frame.setVisible(true);

    }

}
