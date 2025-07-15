package main;

import javax.swing.*;

public class Main {
    public static void main (String [] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // window closes properly
        window.setResizable(false); // window is no longer resizable
        window.setTitle("French Vocab"); // sets title of window

        gamePanel gamePan = new gamePanel();
        window.add(gamePan);

        window.pack();

        window.setLocationRelativeTo(null); // window displayed in center of screen
        window.setVisible(true);

        gamePan.startGameThread();
    }

    /* program notes:
     *  - oh my god the tiles are so ugly all together F;SJLFKDSLDFSLKDFJLSKD
     *  - dw i'm going to change that in the next update TRUST
     *  - trust these were js for testing guys !! (i didn't spend 3 hours picking/drawing them wdym)
     */
}