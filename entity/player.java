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

    // where player is drawn on the screen
    public final int screenX;
    public final int screenY;
    
    public player (gamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        // returns halfway point of screen; player's screen position doesn't change
        screenX = gp.screenWidth / 2 - (gp.tileSize/2); // subtracting half a tile size from player pos so that player is acc in the center
        screenY = gp.screenHeight / 2 - (gp.tileSize/2); // not just having top left corner of image be in the center (if that makes sense?)

        setDefaultValues();
        getPlayerImage();
    }

    // setting mc default values
    public void setDefaultValues(){
        worldX = gp.tileSize * 23; // in world01.txt, 23 tiles to the right
        worldY = gp.tileSize * 21; // in world01.txt, 21 tiles down
        speed = 4; // 4 pixel speed rate
        direction = "idle"; // default direction is idle png
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

    // called 60 times per second
    public void update(){
        // setting direction of character during each instance of key pressed
        if (keyH.upPressed == true){
            direction = "up";
            worldY -= speed; 
        } else if (keyH.downPressed == true){
            direction = "down";
            worldY += speed;
        } else if (keyH.leftPressed == true){
            direction = "left";
            worldX -= speed;
        } else if (keyH.rightPressed == true){
            direction = "right";
            worldX += speed;
        } else {
            direction = "idle";
        }

        // alternating between spritenums to alternate between character images -> animation
        spriteCounter++;
        // player image changes every 12 frames
        if (spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // draws rectangle and fills w colour
        
        BufferedImage image = null;
        // alternating pictures every few seconds for each keyboard command to create an animation effect
        switch (direction){
            case "up" :
                if (spriteNum == 1){
                    image = up1;
                } if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down" :
                if (spriteNum == 1){
                    image = down1;
                } if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left" :
                if (spriteNum == 1){
                    image = left1;
                } if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right" :
                if (spriteNum == 1){
                    image = right1;
                } if (spriteNum == 2){
                    image = right2;
                }
                break;
            case "idle" :
                image = idle;
                break;
        }
        // replacing og white rectangle with character image
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        
    }
}
