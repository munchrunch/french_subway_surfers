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

    /* program notes/preliminary logic:
     *  - need to make it so that character automatically keeps going forward
     *  - 2d game, character can only go up and down (maybe a bird??)
     *  - add obstacles later, just make sure that character keeps going forward
     *  - need to design character and background (something pixelated, y2k style)
     *  - player object, make screen little smaller than laptop monitor size, etc.
     */
}