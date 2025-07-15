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
     * - changed the map (used one off the internet for now, going to change it later)
     * - added a camera function so that i could expand the map
     * - updated some of the tiles because i noticed they looked a bit weird (shadows, etc.)
     */
}