package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.view.component.FadedBackgroundLabel;

import javax.swing.*;
import java.awt.*;

public class VictoryPanel extends JPanel {
private Image backgroundImage;

    /**
     * Constructor for the VictoryPanel class
     * Creates the victory panel
     * @param winner String
     */
    public VictoryPanel(String winner) {
        initializeUI(winner);
        try {
            backgroundImage = new ImageIcon("src/main/resources/Endgame.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the background image
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (this.getWidth() - backgroundImage.getWidth(null)) / 2;
            int y = (this.getHeight() - backgroundImage.getHeight(null)) / 2;
            g.drawImage(backgroundImage, x, y, this);
        }
    }

    /**
     * Initializes the UI
     * @param winner String
     */
    private void initializeUI(String winner) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel victoryLabel = new FadedBackgroundLabel(winner + " won the game!");
        victoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        victoryLabel.setFont(new Font("Arial", Font.BOLD, 40));
        victoryLabel.setForeground(Color.WHITE);
        add(Box.createVerticalGlue());
        add(victoryLabel);

        add(Box.createVerticalStrut(80));
        createMainMenuButton();
        createNewGameButton();
        createQuitButton();
        add(Box.createVerticalGlue());
    }

    /**
     * Creates the main menu button
     */
    private void createMainMenuButton() {
        JButton buttonMainMenu = new JButton("Main Menu");
        buttonMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMainMenu.addActionListener(e -> {
            GameController.goToMainMenu();
        });
        add(buttonMainMenu);
    }

    /**
     * Creates the new game button
     */
    private void createNewGameButton() {
        JButton buttonNewGame = new JButton("New Game");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGame.addActionListener(e -> {
            GameController.newGame();
        });
        add(buttonNewGame);
    }

    /**
     * Creates the quit button
     */
    private void createQuitButton() {
        JButton buttonQuit = new JButton("Quit");
        buttonQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonQuit.addActionListener(e -> {
            System.exit(0);
        });
        add(buttonQuit);
    }
}
