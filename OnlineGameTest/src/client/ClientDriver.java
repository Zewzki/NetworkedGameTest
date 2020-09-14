package client;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ClientDriver {

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;

        ClientContainer cc = new ClientContainer(2, w, h);

        JFrame frame = new JFrame();
        frame.setTitle("Online Game");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cc);

        // listen for window resizes
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                cc.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {}

            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        frame.setVisible(true);

    }

}
