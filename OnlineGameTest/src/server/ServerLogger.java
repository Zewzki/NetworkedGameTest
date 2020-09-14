package server;

import javax.swing.*;

public class ServerLogger {

    private JFrame frame;
    private JTextArea textArea;

    private int lineNum;

    public ServerLogger() {

        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea("Server Log\n", 50, 80);

        frame.add(textArea);

        frame.setVisible(true);

        lineNum = 0;

    }

    public void log(String info) { textArea.append(Integer.toString(lineNum++) + ": " + info + "\n"); }

}
