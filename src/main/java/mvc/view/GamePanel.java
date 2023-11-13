package main.java.mvc.view;

import main.java.mvc.model.Player;
import main.java.mvc.model.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Board offensiveBoard1;
    private Board defensiveBoard1;
    private Board defensiveBoard2;
    private JPanel offensivePanel;
    private JPanel defensivePanel;
    public GamePanel(Player player1, Player player2) {
        this.offensiveBoard1 = player1.getTrackingBoard();
        this.defensiveBoard1 = player1.getOwnBoard();
        this.defensiveBoard2 = player2.getOwnBoard();
        createGamePanel();
    }
    private void createGamePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        offensivePanel = new JPanel(new GridLayout(offensiveBoard1.getRows(), offensiveBoard1.getColumns()));
        defensivePanel = new JPanel(new GridLayout(defensiveBoard1.getRows(), defensiveBoard1.getColumns()));

        createOffensiveBoard();
        createDefensiveBoard();

        add(offensivePanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(defensivePanel);

    }
    private void createOffensiveBoard() {
        for (int i = 0; i < offensiveBoard1.getRows(); i++) {
            for (int j = 0; j < offensiveBoard1.getColumns(); j++) {
                JButton offensiveButtons = new JButton();
                int finalI = i;
                int finalJ = j;
                offensiveButtons.addActionListener(e -> {
                    System.out.println("Coordinates: " + finalI + ", " + finalJ);
                    if (defensiveBoard2.takeShot(finalI, finalJ)) {
                        System.out.println("Hit!");
                        offensiveButtons.setBackground(Color.RED);
                        offensiveButtons.setEnabled(false);
                    } else {
                        System.out.println("Miss!");
                        offensiveButtons.setBackground(Color.blue);
                        offensiveButtons.setEnabled(false);
                    }
                });
                offensivePanel.add(offensiveButtons);
            }
        }
    }
    private void createDefensiveBoard() {
        for (int i = 0; i < defensiveBoard1.getRows(); i++) {
            for (int j = 0; j < defensiveBoard1.getColumns(); j++) {
                JButton defensiveButtons = new JButton();
                defensiveButtons.setEnabled(false);
                switch (defensiveBoard1.getCellStatus(i, j)) {
                    case SHIP:
                        defensiveButtons.setBackground(Color.GRAY);
                        break;
                    case HIT:
                        defensiveButtons.setBackground(Color.RED);
                        break;
                    case MISS:
                        defensiveButtons.setBackground(Color.BLUE);
                        break;
                    default:
                        defensiveButtons.setBackground(Color.WHITE);
                        break;
                }
                defensivePanel.add(defensiveButtons);
            }
        }
    }
}
