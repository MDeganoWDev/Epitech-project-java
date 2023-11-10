package main.java;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.BigMom;
import main.java.mvc.model.Strawhat;

import javax.swing.SwingUtilities;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Strawhat strawhat = new Strawhat();
            BigMom bigMom = new BigMom();
            GameController gameController = new GameController(15, strawhat, bigMom);
            gameController.initializeGame();
        });

    }

}
