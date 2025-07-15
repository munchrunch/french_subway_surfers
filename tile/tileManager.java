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
        mapTileNum = new int [gp.maxScreenCol] [gp.maxScreenRow];

        getTileImage();
        loadMap("/res/maps/map01.txt");
    }

    public void getTileImage() {
        try { 
            //initilizing tile array w/diff tile images
            Tile[0] = new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/snowv3.png"));

            Tile[1] = new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/fencing.png"));

            Tile[2] = new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ice.png"));

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

            // maxscreencol and maxscreenrow are limit of loop; no more data beyond that (16x12)
            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine(); // reads line of text

                while (col < gp.maxScreenCol){
                    //splits line and gets tile numbers 1 by 1, stores them in array
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]); // changing from string to int to use data appropriately
                    
                    // continue until everything in numbres is stored in maptilenum
                    mapTileNum [col] [row] = num; // store extracted number in mapTileNum array
                    col++;
                }

                if (col == gp.maxScreenCol) {
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
            // extract tile num stored in mapTileNum
            int tileNum = mapTileNum[col] [row];

            g2.drawImage(Tile[tileNum].image, x, y, gp.tileSize, gp.tileSize,  null);
            col++;
            x += gp.tileSize;

            // increase col 1 by 1, if reaches max screen column num (16), reset col and x
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
