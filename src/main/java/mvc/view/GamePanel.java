package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.controller.GameObserver;
import main.java.mvc.model.Player;
import main.java.mvc.model.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    private Board offensiveBoard1;
    private Board defensiveBoard1;
    private JPanel offensivePanel;
    private JPanel defensivePanel;
    public GamePanel(Player player1) {
        this.offensiveBoard1 = player1.getTrackingBoard();
        this.defensiveBoard1 = player1.getOwnBoard();
        createGamePanel();
    }
    public void update() {
        updateDefensivePanel();
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
                    switch (GameController.combatLoop(finalI, finalJ)) {
                      case "HIT":
                          offensiveButtons.setBackground(Color.RED);
                          offensiveButtons.setEnabled(false);
                          GameController.checkGameState();

                          break;
                      case "MISS":
                          offensiveButtons.setBackground(Color.BLUE);
                          offensiveButtons.setEnabled(false);
                          GameController.checkGameState();
                          break;
                      default:
                          GameController.checkGameState();
                          break;
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
    private void updateDefensivePanel() {
        for (int i = 0; i < defensiveBoard1.getRows(); i++) {
            for (int j = 0; j < defensiveBoard1.getColumns(); j++) {
                JButton button = (JButton) defensivePanel.getComponent(i * defensiveBoard1.getColumns() + j);
                switch (defensiveBoard1.getCellStatus(i, j)) {
                    case SHIP:
                        button.setBackground(Color.GRAY);
                        break;
                    case HIT:
                        button.setBackground(Color.RED);
                        break;
                    case MISS:
                        button.setBackground(Color.BLUE);
                        break;
                    default:
                        button.setBackground(Color.WHITE);
                        break;
                }
            }
        }
    }
}
