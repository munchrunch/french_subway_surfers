package tile;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import main.gamePanel;

public class tileManager {
    gamePanel gp;
    tile[] Tile;

    // making map with text file, 16x12 nums, each num represents one 48x48 pixel tile on the map
    int mapTileNum [] [];

    public tileManager(gamePanel gp){
        this.gp = gp;
        Tile = new tile[10]; //set size of tile array, 10 kinds of tiles

        // 2d array
        mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage() {
        try { 
            // initilizing tile array w/diff tile images (loading images)
            Tile[0] = new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/snowv3.png"));

            Tile[1] = new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/fencing.png"));

            Tile[2] = new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ice.png"));

            Tile[3] = new tile();
            Tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            Tile[4] = new tile();
            Tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/snowtree.png"));

            Tile[5] = new tile();
            Tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/stepping_stones.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // scanning text file line by line and store data in mapTileNum
    public void loadMap(String filePath) {
        try {
            // using input stream to import text file, using buffered reader to read content of text file
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader (new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // maxscreencol and maxscreenrow are boundaries of loop; no more data beyond that (50x50)
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine(); // reads line of text

                while (col < gp.maxWorldCol){
                    //splits line and gets tile numbers 1 by 1, stores them in array
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]); // changing from string to int to use data appropriately
                    
                    // continue until everything in numbres is stored in maptilenum
                    mapTileNum [col] [row] = num; // store extracted number in mapTileNum array
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            // closing buffered reader, no longer necessary
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // putting tile pngs on array to draw a background 
    public void draw (Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            // extract tile num stored in mapTileNum
            int tileNum = mapTileNum[worldCol] [worldRow];

            // position on the map
            int worldX = worldCol * gp.tileSize; 
            int worldY = worldRow * gp.tileSize;
            // where on the screen the tile is drawn, returns tile's screen position
            int screenX = worldX - gp.Player.worldX + gp.Player.screenX; // need to offset difference, why you add gp.player.screen_
            int screenY = worldY - gp.Player.worldY + gp.Player.screenY;
            
            // creating a boundary from center of screen, +1 tile around the boundary to avoid a black border around camera
            if (worldX + gp.tileSize > gp.Player.worldX - gp.Player.screenX && 
                worldX - gp.tileSize < gp.Player.worldX + gp.Player.screenX &&
                worldY + gp.tileSize > gp.Player.worldY - gp.Player.screenY &&
                worldY - gp.tileSize < gp.Player.worldY + gp.Player.screenY){

                // making it so that only the tiles that are within the boundary are generated
                g2.drawImage(Tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,  null);
            }

            worldCol++;

            // increase col 1 by 1, if reaches max world column num (50), reset col and row
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
