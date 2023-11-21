package main.java.mvc.controller;

import javax.swing.SwingUtilities;

public class StartApp {

    /**
     * Main method
     * Starts the game
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameController::new);
    }

}
