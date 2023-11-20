package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private Image backgroundImage;
    public MainMenuPanel() {
        initializeUI();
        try {
            backgroundImage = new ImageIcon("src/main/resources/MainMenu.png").getImage();
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
    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createNewGameButton();
        createQuitButton();
    }
    private void createNewGameButton(){
        JButton buttonNewGame = new JButton("New Game");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGame.addActionListener(e -> {
            GameController.selectFactionView();
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
