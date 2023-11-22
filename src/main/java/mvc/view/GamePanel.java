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
    Color brown = new Color(70, 42, 42);
    private  int cellSize;
    private final Board offensiveBoard1;
    private final Board defensiveBoard1;
    private final JPanel gameBoardPanel;
    private final JPanel buttonPanel;
    private JPanel offensivePanel;
    private JPanel defensivePanel;
    private Image backgroundImage;

    /**
     * Constructor for the GamePanel class
     * @param player1 Player object
     * @param boardSize int
     */
    public GamePanel(Player player1, int boardSize) {
        this.boardSize = boardSize ;
        this.offensiveBoard1 = player1.getTrackingBoard();
        this.defensiveBoard1 = player1.getOwnBoard();

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBackground(new Color(0, 0, 0, 0));
        gamePanel.setOpaque(false);
        add(gamePanel);

        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BoxLayout(gameBoardPanel, BoxLayout.X_AXIS));
        gameBoardPanel.setBackground(new Color(0, 0, 0, 0));
        gameBoardPanel.setOpaque(false);
        createGamePanel();
        gamePanel.add(gameBoardPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(new Color(0, 0, 0, 0));
        buttonPanel.setOpaque(false);
        createSurrenderButton();
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        try {
            backgroundImage = new ImageIcon("src/main/resources/Battle.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the background image
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (this.getWidth() - backgroundImage.getWidth(null)) / 2;
            int y = (this.getHeight() - backgroundImage.getHeight(null)) / 2;
            g.drawImage(backgroundImage, x, y, this);
        }
    }

    /**
     * Updates the defensive panel
     * Use the observer pattern
     */
    public void update() {
        updateDefensivePanel();
    }

    /**
     * Creates the surrender button
     */
    private void createSurrenderButton() {
        JButton surrenderButton = new JButton("Surrender");
        surrenderButton.addActionListener(e -> GameController.surrender());
        buttonPanel.add(surrenderButton);
    }

    /**
     * Creates the game panel
     */
    private void createGamePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        if (boardSize == 20) {
            this.cellSize = 25;
        } else if (boardSize >= 15) {
            this.cellSize = 30;
        } else {
            this.cellSize = 35;
        }

        Dimension panelSize = new Dimension(boardSize * this.cellSize, boardSize * this.cellSize);

        offensivePanel = new BackgroundGamePanel("src/main/resources/Ocean2.jpg");
        defensivePanel = new BackgroundGamePanel("src/main/resources/Ocean2.jpg");

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

        gameBoardPanel.add(Box.createHorizontalGlue());
        gameBoardPanel.add(offensivePanel);
        gameBoardPanel.add(Box.createHorizontalStrut(80));
        gameBoardPanel.add(defensivePanel);
        gameBoardPanel.add(Box.createHorizontalGlue());
    }

    /**
     * Creates the offensive board
     */
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

    /**
     * Creates the defensive board
     */
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
                        defensiveButtons.setBackground(brown);
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

    /**
     * Updates the defensive panel
     */
    private void updateDefensivePanel() {
        for (int i = 0; i < defensiveBoard1.getRows(); i++) {
            for (int j = 0; j < defensiveBoard1.getColumns(); j++) {
                JButton button = (JButton) defensivePanel.getComponent(i * defensiveBoard1.getColumns() + j);
                switch (defensiveBoard1.getCellStatus(i, j)) {
                    case SHIP:
                        button.setOpaque(true);
                        button.setBackground(brown);
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
