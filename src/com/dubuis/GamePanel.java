package com.dubuis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    Player player;
    ArrayList<Wall> walls = new ArrayList<>();

    int cameraX;
    int offset;
    Timer gameTimer;

    Rectangle restartRect;
    Rectangle homeRect;

    Font buttonFont = new Font("Arial", Font.BOLD, 30);

    public GamePanel() {

        restartRect = new Rectangle(550, 25, 50, 50);
        homeRect = new Rectangle(625, 25, 50, 50);

        player = new Player(400,300, this);
        
        reset();
        
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                //Get the x for the last wall (in the Wall array)
                if (walls.get(walls.size()-1).x < 800) {

                    offset += 700;
                    makeWalls(offset);
                }

                player.set();
                for (Wall wall: walls) wall.set(cameraX);

                //Delete walls that came out of the right screen
                for (int i = 0; i < walls.size(); ++i) {
                    if (walls.get(i).x < - 800) walls.remove(i);
                }

                repaint();
            }
        }, 0, 17);
    }

    public void reset() {
        player.x = 200;
        player.y = 150;
        cameraX = 150;
        player.xspeed = 0;
        player.yspeed = 0;
        walls.clear();
        offset = -150;
        makeWalls(offset);
    }

    private void makeWalls(int offset) {
        int s = 50; // wallSize
        Random rand = new Random();
        int index = rand.nextInt(9);
        switch (index) {
            case 0: //Flat walls
                for (int i = 0; i<=13; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 12*s, s, s));
                break;
            case 1:
                walls.add(new Wall(Color.WHITE, offset + 0*s, 12*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 6*s, 12*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 13*s, 12*s, s, s));
                break;
            case 2:
                for (int i = 0; i<=5; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 12*s, s, s));
                for (int i = 2; i<=5; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 11*s, s, s));
                for (int i = 3; i<=5; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 10*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 5*s, 9*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 13*s, 12*s, s, s));
                break;
            case 3:
                for (int i = 0; i<=4; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 12*s, s, s));
                for (int i = 1; i<=4; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 11*s, s, s));
                for (int i = 2; i<=4; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 10*s, s, s));
                for (int i = 3; i<=4; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 9*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 4*s, 8*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 12*s, 12*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 13*s, 12*s, s, s));
                break;
            case 4:
                walls.add(new Wall(Color.WHITE, offset + 0*s, 12*s, s, s));
                for (int i = 11; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + 2*s, i*s, s, s));
                for (int i = 10; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + 4*s, i*s, s, s));
                for (int i = 9; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + 7*s, i*s, s, s));
                for (int i = 8; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + 8*s, i*s, s, s));
                for (int i = 7; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + 10*s, i*s, s, s));
                for (int i = 6; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + 12*s, i*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 13*s, 12*s, s, s));
                break;
            case 5:
                for (int i = 0; i<=13; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 12*s, s, s));
                for (int i = 2; i<=11; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 11*s, s, s));
                for (int i = 5; i<=8; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 10*s, s, s));
                for (int i = 6; i<=7; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 9*s, s, s));
                break;
            case 6:
                for (int i = 0; i<=13; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 12*s, s, s));
                //Heart
                for (int i = 1; i<=2; ++i) walls.add(new Wall(Color.RED, offset + i*s, 1*s, s, s));
                for (int i = 4; i<=5; ++i) walls.add(new Wall(Color.RED, offset + i*s, 1*s, s, s));
                for (int i = 0; i<=6; ++i) walls.add(new Wall(Color.RED, offset + i*s, 2*s, s, s));
                for (int i = 0; i<=6; ++i) walls.add(new Wall(Color.RED, offset + i*s, 3*s, s, s));
                for (int i = 1; i<=5; ++i) walls.add(new Wall(Color.RED, offset + i*s, 4*s, s, s));
                for (int i = 2; i<=4; ++i) walls.add(new Wall(Color.RED, offset + i*s, 5*s, s, s));
                walls.add(new Wall(Color.RED, offset + 3*s, 6*s, s, s));
                //Heart
                for (int i = 8; i<=9; ++i) walls.add(new Wall(Color.RED, offset + i*s, 4*s, s, s));
                for (int i = 11; i<=12; ++i) walls.add(new Wall(Color.RED, offset + i*s, 4*s, s, s));
                for (int i = 7; i<=13; ++i) walls.add(new Wall(Color.RED, offset + i*s, 5*s, s, s));
                for (int i = 7; i<=13; ++i) walls.add(new Wall(Color.RED, offset + i*s, 6*s, s, s));
                for (int i = 8; i<=12; ++i) walls.add(new Wall(Color.RED, offset + i*s, 7*s, s, s));
                for (int i = 9; i<=11; ++i) walls.add(new Wall(Color.RED, offset + i*s, 8*s, s, s));
                walls.add(new Wall(Color.RED, offset + 10*s, 9*s, s, s));
                break;
            case 7:
                for (int i = 0; i<=13; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 12*s, s, s));
                for (int i = 1; i<=7; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 11*s, s, s));
                for (int i = 2; i<=5; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 10*s, s, s));
                for (int i = 3; i<=4; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 9*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 4*s, 8*s, s, s));

                for (int i = 9; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 9*s, s, s));
                for (int i = 7; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 8*s, s, s));
                for (int i = 6; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 7*s, s, s));
                for (int i = 7; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 6*s, s, s));
                for (int i = 8; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 5*s, s, s));
                for (int i = 9; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 4*s, s, s));
                for (int i = 10; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 3*s, s, s));
                for (int i = 11; i<=12; ++i) walls.add(new Wall(Color.WHITE, offset + i*s, 2*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 12*s, 1*s, s, s));

                break;
            case 8:
                walls.add(new Wall(Color.WHITE, offset + 0*s, 12*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 1*s, 12*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 6*s, 11*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 9*s, 11*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 10*s, 11*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 12*s, 12*s, s, s));
                walls.add(new Wall(Color.WHITE, offset + 13*s, 12*s, s, s));
                break;

            default: //Flat walls black (not implemented)
                for (int i = 0; i<14; ++i) walls.add(new Wall(Color.BLACK, offset + i*s, 12*s, s, s));
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);
        for (Wall wall: walls) wall.draw(gtd);

        gtd.setColor(Color.BLACK);
        gtd.drawRect(550, 25, 50, 50);
        gtd.drawRect(625, 25, 50, 50);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(551, 26, 48, 48);
        gtd.fillRect(626, 26, 48, 48);
        gtd.setColor(Color.BLACK);
        gtd.setFont(buttonFont);
        gtd.drawString("R", 564, 60);
        gtd.drawString("H", 639, 60);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'z') player.keyUp = true;
        if (e.getKeyChar() == 's') player.keyDown = true;
        if (e.getKeyChar() == 'q') player.keyLeft = true;
        if (e.getKeyChar() == 'd') player.keyRight = true;
        if (e.getKeyChar() == 'r') reset();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'z') player.keyUp = false;
        if (e.getKeyChar() == 's') player.keyDown = false;
        if (e.getKeyChar() == 'q') player.keyLeft = false;
        if (e.getKeyChar() == 'd') player.keyRight = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        if (restartRect.contains(new Point(e.getPoint().x, e.getPoint().y - 27))) reset();
    }
}
