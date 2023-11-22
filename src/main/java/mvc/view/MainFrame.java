package main.java.mvc.view;

import javax.swing.*;
public class MainFrame extends JFrame {

    /**
     * Constructor for the MainFrame class
     * Creates the main frame for the game
     */
    public MainFrame() {
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
}
