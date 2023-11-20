package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.controller.GameObserver;
import main.java.mvc.model.Player;
import main.java.mvc.model.Board;
import main.java.mvc.view.component.BackgroundGamePanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    private final int boardSize;
    private  int cellSize;
    private  Dimension panelSize;
    private final Board offensiveBoard1;
    private final Board defensiveBoard1;
    private JPanel offensivePanel;
    private JPanel defensivePanel;
    private Image backgroundImage;

    public GamePanel(Player player1, int boardSize) {
        this.boardSize = boardSize ;
        this.offensiveBoard1 = player1.getTrackingBoard();
        this.defensiveBoard1 = player1.getOwnBoard();
        JPanel gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BoxLayout(gameBoardPanel, BoxLayout.Y_AXIS));
        createGamePanel();
        createBottomPanel();
        try {
            backgroundImage = new ImageIcon("src/main/resources/Battle.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (this.getWidth() - backgroundImage.getWidth(null)) / 2;
            int y = (this.getHeight() - backgroundImage.getHeight(null)) / 2;
            g.drawImage(backgroundImage, x, y, this);
        }
    }
    public void update() {
        updateDefensivePanel();
    }

    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(createSurrenderButton());
    }

    private JButton createSurrenderButton() {
        JButton surrenderButton = new JButton("Surrender");
        surrenderButton.addActionListener(e -> GameController.surrender());
        return surrenderButton;
    }
    private void createGamePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        if (boardSize == 20) {
            this.cellSize = 20;
        } else if (boardSize >= 15) {
            this.cellSize = 25;
        } else {
            this.cellSize = 30;
        }

        this.panelSize = new Dimension(boardSize * this.cellSize, boardSize * this.cellSize);

        offensivePanel = new BackgroundGamePanel("src/main/resources/Ocean.jpg");
        defensivePanel = new BackgroundGamePanel("src/main/resources/Ocean.jpg");

        offensivePanel.setLayout(new GridLayout(boardSize, boardSize));
        defensivePanel.setLayout(new GridLayout(boardSize, boardSize));

        offensivePanel.setPreferredSize(panelSize);
        defensivePanel.setPreferredSize(panelSize);

        offensivePanel.setMinimumSize(panelSize);
        offensivePanel.setMaximumSize(panelSize);
        defensivePanel.setMinimumSize(panelSize);
        defensivePanel.setMaximumSize(panelSize);

        createOffensiveBoard();
        createDefensiveBoard();

        add(offensivePanel);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(defensivePanel);
    }
    private void createOffensiveBoard() {

        offensivePanel.setLayout(new GridBagLayout());
        offensivePanel.setOpaque(false);
        offensivePanel.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < offensiveBoard1.getRows(); i++) {
            for (int j = 0; j < offensiveBoard1.getColumns(); j++) {
                JButton offensiveButtons = new JButton();
                offensiveButtons.setPreferredSize(new Dimension(this.cellSize, this.cellSize));
                offensiveButtons.setOpaque(false);
                offensiveButtons.setBackground(new Color(0, 0, 0, 0));
                gbc.gridx = j;
                gbc.gridy = i;
                int finalI = i;
                int finalJ = j;
                offensivePanel.add(offensiveButtons, gbc);
                offensiveButtons.addActionListener(e -> {
                    switch (GameController.combatLoop( finalI, finalJ)) {
                      case "HIT":
                          offensiveButtons.setBackground(Color.RED);
                          offensiveButtons.setOpaque(true);
                          offensiveButtons.setEnabled(false);
                          GameController.checkGameState();
                          break;
                      case "MISS":
                          offensiveButtons.setBackground(Color.BLUE);
                          offensiveButtons.setOpaque(true);
                          offensiveButtons.setEnabled(false);
                          GameController.checkGameState();
                          break;
                      default:
                          GameController.checkGameState();
                          break;
                    }
                });
                offensivePanel.add(offensiveButtons, gbc);
            }
        }
    }
    private void createDefensiveBoard() {
        defensivePanel.setLayout(new GridBagLayout());
        defensivePanel.setOpaque(false);
        defensivePanel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < defensiveBoard1.getRows(); i++) {
            for (int j = 0; j < defensiveBoard1.getColumns(); j++) {
                JButton defensiveButtons = new JButton();
                defensiveButtons.setPreferredSize(new Dimension(this.cellSize, this.cellSize));
                defensiveButtons.setEnabled(false);
                defensiveButtons.setOpaque(false);
                defensiveButtons.setBackground(new Color(0, 0, 0, 0));
                gbc.gridx = j;
                gbc.gridy = i;
                defensivePanel.add(defensiveButtons, gbc);
                switch (defensiveBoard1.getCellStatus(i, j)) {
                    case SHIP:
                        defensiveButtons.setOpaque(true);
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
                defensivePanel.add(defensiveButtons, gbc);
            }
        }
    }
    private void updateDefensivePanel() {
        for (int i = 0; i < defensiveBoard1.getRows(); i++) {
            for (int j = 0; j < defensiveBoard1.getColumns(); j++) {
                JButton button = (JButton) defensivePanel.getComponent(i * defensiveBoard1.getColumns() + j);
                switch (defensiveBoard1.getCellStatus(i, j)) {
                    case SHIP:
                        button.setOpaque(true);
                        button.setBackground(Color.GRAY);
                        break;
                    case HIT:
                        button.setOpaque(true);
                        button.setBackground(Color.RED);
                        break;
                    case MISS:
                        button.setOpaque(true);
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
