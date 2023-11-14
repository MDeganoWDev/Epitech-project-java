package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class VictoryPanel extends JPanel {
    public VictoryPanel(String winner) {
        initializeUI(winner);
    }

    private void initializeUI(String winner) {
        JLabel victoryLabel = new JLabel(winner + " won the game!");
        victoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(victoryLabel);

        createMainMenuButton();
        createNewGameButton();
        createQuitButton();
    }
    private void createMainMenuButton() {
        JButton buttonMainMenu = new JButton("Main Menu");
        buttonMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMainMenu.addActionListener(e -> {
            GameController.switchPanel(new MainMenuPanel());
        });
        add(buttonMainMenu);
    }
    private void createNewGameButton() {
        JButton buttonNewGame = new JButton("New Game");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGame.addActionListener(e -> {
            GameController.switchPanel(new SelectFactionPanel());
        });
        add(buttonNewGame);
    }
    private void createQuitButton() {
        JButton buttonQuit = new JButton("Quit");
        buttonQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonQuit.addActionListener(e -> {
            System.exit(0);
        });
        add(buttonQuit);
    }
}
