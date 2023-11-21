package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructor for the MainMenuPanel class
     * Creates the main menu panel
     */
    public MainMenuPanel() {
        initializeUI();
        try {
            backgroundImage = new ImageIcon("src/main/resources/MainMenu.png").getImage();
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
     */
    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createNewGameButton();
        createQuitButton();
    }

    /**
     * Creates the new game button
     */
    private void createNewGameButton(){
        JButton buttonNewGame = new JButton("New Game");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGame.addActionListener(e -> {
            GameController.selectFactionView();
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
