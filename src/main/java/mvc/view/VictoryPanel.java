package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class VictoryPanel extends JPanel {
private Image backgroundImage;
    public VictoryPanel(String winner) {
        initializeUI(winner);
        try {
            backgroundImage = new ImageIcon("src/main/resources/Endgame.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (this.getWidth() - backgroundImage.getWidth(null)) / 2;
            int y = (this.getHeight() - backgroundImage.getHeight(null)) / 2;
            g.drawImage(backgroundImage, x, y, this);
        }
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
            GameController.goToMainMenu();
        });
        add(buttonMainMenu);
    }
    private void createNewGameButton() {
        JButton buttonNewGame = new JButton("New Game");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGame.addActionListener(e -> {
            GameController.newGame();
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
