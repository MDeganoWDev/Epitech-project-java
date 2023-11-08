package main.java.mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuView {
    private JFrame frame;
    private JButton startGameButton;
    private JButton optionsButton;
    private JButton creditsButton;

    public GameMenuView() {
        //la fenêtre du menu
        frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 1));

        //créez les boutons
        startGameButton = new JButton("Start Game");
        optionsButton = new JButton("Options");
        creditsButton = new JButton("Credits");

        //ajout des boutons à la fenêtre
        frame.add(startGameButton);
        frame.add(optionsButton);
        frame.add(creditsButton);
    }

    public void display() {
        // Affiche la fenêtre
        frame.setVisible(true);
    }

    // Ajouts des actions listeners pour les boutons
    public void addStartGameListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }

    public void addOptionsListener(ActionListener listener) {
        optionsButton.addActionListener(listener);
    }

    public void addCreditsListener(ActionListener listener) {
        creditsButton.addActionListener(listener);
    }
}
