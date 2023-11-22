package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.sound.sampled.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class MainMenuPanel extends JPanel {
    String filepath = "/main/resources/epic-sound.wav";
    private Image backgroundImage;

    /**
     * Constructor for the MainMenuPanel class
     * Creates the main menu panel
     */
    public MainMenuPanel() {
        initializeUI();
        try {
            backgroundImage = new ImageIcon("src/main/resources/MainMenu.png").getImage();
            playMusic(filepath);

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
        add(Box.createVerticalGlue());
        createNewTitle();
        add(Box.createRigidArea(new Dimension(0, 20)));
        createStyledButton("New Game", (e) -> GameController.selectFactionView());
        add(Box.createRigidArea(new Dimension(0, 10)));
        createStyledButton("Quit", (e) -> System.exit(0));
        add(Box.createVerticalGlue());
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

    private void createNewTitle(){
        Font titleFont = new Font("Comic Sans MS", Font.BOLD, 36);
        JLabel menuTitle = new JLabel("Battle Of Ship");
        menuTitle.setFont(titleFont);
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(menuTitle);
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

    private void createStyledButton(String text, Consumer<ActionEvent> action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(new Color(53, 124, 165));
                } else {
                    g.setColor(new Color(78, 143, 198));
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });

        button.addActionListener(e -> action.accept(e));

        add(button);
    }

    private void playMusic(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
