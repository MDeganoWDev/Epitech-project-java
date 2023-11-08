package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private GameController gameController;
    private GamePanel gamePanel;

    public MainFrame(GameController gameController) {
        this.gameController = gameController;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        gamePanel = new GamePanel(gameController, 30);
        add(gamePanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startNewGame();
            }
        });

        gameMenu.add(newGame);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void repaintBoard() {
        gamePanel.repaint();
    }
}
