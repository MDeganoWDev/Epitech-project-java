package main.java;

import main.java.mvc.controller.GameController;
import javax.swing.SwingUtilities;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameController gameController = new GameController(10);
            gameController.startNewGame();
        });

    }
    
}
