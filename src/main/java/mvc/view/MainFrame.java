package main.java.mvc.view;

import javax.swing.*;
public class MainFrame extends JFrame {

    public MainFrame() {
        initializeFrame();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Battleship Game");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
