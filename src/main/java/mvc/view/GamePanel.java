package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private GameController gameController;
    private int cellSize;

    public GamePanel(GameController gameController, int cellSize) {
        this.gameController = gameController;
        this.cellSize = cellSize;
        int boardSize = gameController.getBoard().getRows();
        this.setPreferredSize(new Dimension(boardSize * cellSize, boardSize * cellSize));
        this.setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / cellSize;
                int column = e.getX() / cellSize;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    gameController.placeShip(row, column, 1, true);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    gameController.takeShot(row, column);
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Board board = gameController.getBoard();
        int boardSize = board.getRows();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(x, y, cellSize, cellSize);

                switch (board.getCellStatus(row, col)) {
                    case SHIP:
                        g.setColor(Color.GRAY);
                        g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                        break;
                    case HIT:
                        g.setColor(Color.RED);
                        g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                        break;
                    case MISS:
                        g.setColor(Color.BLUE);
                        g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                        break;
                    case EMPTY:
                    default:
                        break;
                }
            }
        }
    }
}
