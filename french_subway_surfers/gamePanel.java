package french_subway_surfers;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel implements Runnable{
    // screen settings
    final int originalTileSize = 16; // 16 x 16 tile (player character)
    final int scale = 3; // scales tiles up by 3

    final int tileSize = originalTileSize * scale; // 48 x 48 tile
    final int maxScreenCol = 18;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 864 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int fps = 60;

    keyHandler keyH = new keyHandler();
    Thread gameThread; // makes it so that fps works (animations)

    // set player's default position
    int playerX = 100; // 100 pixels to the right
    int playerY = 100; // 100 pixels down
    int playerSpeed = 4; // 4 pixels

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of panel
        this.setBackground(Color.black); // make panel black
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
        if (keyH.upPressed == true){
            playerY -= playerSpeed; 
        } else if (keyH.downPressed == true){
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true){
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true){
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g) { // built-in java method for fps
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // changing graphics g object to graphics2d class
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize); // draws rectangle and fills w colour
        g2.dispose();
    }
}