package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private boolean isSoundMuted = false;
    String filepath = "/main/resources/epic-sound.wav";
    long clipTimePosition;
    Clip clip;

    /**
     * Constructor for the MainFrame class
     * Creates the main frame for the game
     */
    public MainFrame() {
        playMusic(filepath);
        initializeMenu();
        initializeFrame();
        setVisible(true);
    }

    /**
     * Initializes the frame
     */
    private void initializeFrame() {
        setTitle("Battleship Game");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Initializes the menu
     */
    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenu soundMenu = new JMenu("Sound");

        gameMenu.add(createMainMenuItem());
        gameMenu.add(createNewGameItem());
        gameMenu.add(createExitItem());

        soundMenu.add(createMuteSoundItem());

        menuBar.add(gameMenu);
        menuBar.add(soundMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Creates the main menu item
     * @return JMenuItem
     */
    private JMenuItem createMainMenuItem() {
        JMenuItem mainMenuItem = new JMenuItem("Main menu");
        mainMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.goToMainMenu();
            }
        });
        return mainMenuItem;
    }

    /**
     * Creates the new game item
     * @return JMenuItem
     */
    private JMenuItem createNewGameItem() {
        JMenuItem newGameItem = new JMenuItem("New game");
        newGameItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.newGame();
            }
        });
        return newGameItem;
    }

    /**
     * Creates the exit item
     * @return JMenuItem
     */
    private JMenuItem createExitItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exitItem;
    }

    /**
     * Creates the mute sound item
     * @return JMenuItem
     */
    private JMenuItem createMuteSoundItem() {
        JMenuItem muteSoundItem = new JMenuItem("Mute sound");
        muteSoundItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (isSoundMuted) {
                   muteSoundItem.setText("Mute sound");
                   isSoundMuted = false;
                   resumeMusic();
               } else {
                   muteSoundItem.setText("Unmute sound");
                   isSoundMuted = true;
                   stopMusic();
               }
            }
        });
        return muteSoundItem;
    }

    /**
     * Plays the music
     * @param filepath String
     */
    private void playMusic(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the music
     */
    private void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            clip.close();
        }
    }

    /**
     * Resumes the music
     */
    private void resumeMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
