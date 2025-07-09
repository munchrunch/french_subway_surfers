package french_subway_surfers;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {
    // screen settings
    final int originalTileSize = 16; // 16 x 16 tile (player character)
    final int scale = 3; // scales tiles up by 3

    final int tileSize = originalTileSize * scale; // 48 x 48 tile
    final int maxScreenCol = 18;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 864 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of panel
        this.setBackground(Color.black); // make panel black
        this.setDoubleBuffered(true); // makes rendering performance better
    }
}