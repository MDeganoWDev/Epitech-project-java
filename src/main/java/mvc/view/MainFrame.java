package main.java.mvc.view;

import main.java.mvc.model.BigMom;
import main.java.mvc.model.Player;
import main.java.mvc.model.Strawhat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {

    private Player player1;
    private Player player2;
    public MainFrame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        initializeUI();
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
                getContentPane().removeAll();
                getContentPane().add(new GamePanel(player1, player2));
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });

        setLayout(new BorderLayout());
        add(startGameButton, BorderLayout.SOUTH);
    }


}
