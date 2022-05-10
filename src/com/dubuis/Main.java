package com.dubuis;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	    MainFrame frame = new MainFrame();

        frame.setSize(700,700);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),(int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));

        frame.setResizable(false);
        frame.setTitle("Platformer Game");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
