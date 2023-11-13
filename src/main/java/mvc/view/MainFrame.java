package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {

    public MainFrame() {
        initializeUI();
        setVisible(true);
    }

    private void initializeUI() {
        setTitle("Battleship Game");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add a button to start a new game
        JButton startGameButton = new JButton("Start New Game");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.selectFactionView();
            }
        });

        setLayout(new BorderLayout());
        add(startGameButton, BorderLayout.SOUTH);
    }
}
