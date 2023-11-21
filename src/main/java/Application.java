package main.java;

import main.java.mvc.controller.GameController;

import javax.swing.SwingUtilities;

public class Application {

    /**
     * Main method
     * Starts the game
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameController::new);
    }

}
