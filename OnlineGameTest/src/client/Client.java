package client;

import javax.swing.*;
import java.awt.*;


public class Client {

    // deleted because i got angry


    public static void main(String[] args){
        Pog();
        System.out.println("Haha");
        System.out.println("Nice");
        JFrame Pogframe = new JFrame();
        Pogframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel Poglabel = new JLabel("Pog");

        Pogframe.add(Poglabel);

        Pogframe.setPreferredSize(new Dimension(400,400));
        Pogframe.pack();
        Pogframe.setVisible(true);
    }

    public static void Pog() {
        int Poggers = 69;
        for (int Pog = 0; Pog < Poggers; Pog++) {
            System.out.println("Pog");
        }
    }

}




