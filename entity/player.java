package entity;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamePanel;
import main.keyHandler;

public class player extends Entity {
    gamePanel gp;
    keyHandler keyH;
    
    public player (gamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    // setting mc default values
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "idle";
    }

    public void getPlayerImage(){
        // assigning mc pngs to variables
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_right2.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/res/player/mc_idle.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyH.upPressed == true){
            direction = "up";
            y -= speed; 
        } else if (keyH.downPressed == true){
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed == true){
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed == true){
            direction = "right";
            x += speed;
        } else {
            direction = "idle";
        }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // draws rectangle and fills w colour
    
        BufferedImage image = null;
        switch (direction){
            case "up" -> image = up1;
            case "down" -> image = down1;
            case "left" -> image = left1;
            case "right" -> image = right1;
            case "idle" -> image = idle;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        
    }
}
