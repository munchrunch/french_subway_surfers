package main;

import javax.swing.*;
import java.awt.*;

import entity.player;
import tile.tileManager;

public class gamePanel extends JPanel implements Runnable{
    // screen settings
    final int originalTileSize = 16; // 16 x 16 pixels (player character)
    final int scale = 3; // scales pixels up by 3

    public final int tileSize = originalTileSize * scale; // 48 x 48 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int fps = 60;

    tileManager tileM = new tileManager(this); //passing gamepanel class to tilemanager class

    keyHandler keyH = new keyHandler();
    Thread gameThread; // makes it so that fps works (animations)
    player Player = new player(this, keyH);

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of panel
        this.setBackground(Color.white); // makes background panel white
        this.setDoubleBuffered(true); // makes rendering performance better
        this.addKeyListener(keyH); // recognizes key input
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); //initializing thread
        gameThread.start(); // calls run method

    }

    @Override
    public void run() {
        // 1 billion nanoseconds = 1 second
        double drawInterval = 1000000000/fps; // 0.16666... seconds; draw screen 60 times/second
        double nextDrawTime = System.nanoTime() + drawInterval; 


        // as long as gameThread exists, will continue loop
        while (gameThread != null){
            // update information (ex character positions)
            update();

            // draw the screen with the updated information; controls fps
            repaint(); // calling paintComponent method

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // converting remainingTime to milliseconds

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) (remainingTime)); // pauses game loop until remaining time is over
                nextDrawTime += drawInterval; // resetting nextDrawTime
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        Player.update();
    }

    public void paintComponent(Graphics g) { // built-in java method for fps
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // changing graphics g object to graphics2d class

        // putting tiles and player graphics on screen
        tileM.draw(g2); // put bg tiles before player tiles to not hide player
        Player.draw(g2);

        g2.dispose();
    }
}