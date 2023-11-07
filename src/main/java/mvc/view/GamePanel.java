package main.java.mvc.view;

import main.java.mvc.model.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

    public class GamePanel extends JPanel {
        private static final int GRID_SIZE = 30; // Size of the grid cell in pixels
        private Board board;
        private boolean interactive;
        private EventListener listener;

        public interface EventListener {
            void onCellClicked(int row, int col);
        }

        public GamePanel(Board board, boolean interactive, EventListener listener) {
            this.board = board;
            this.interactive = interactive;
            this.listener = listener;
            this.setPreferredSize(new Dimension(board.getColumns() * GRID_SIZE, board.getRows() * GRID_SIZE));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (interactive) {
                        int row = e.getY() / GRID_SIZE;
                        int col = e.getX() / GRID_SIZE;
                        listener.onCellClicked(row, col);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the grid
            for (int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    int x = col * GRID_SIZE;
                    int y = row * GRID_SIZE;
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, GRID_SIZE, GRID_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, GRID_SIZE, GRID_SIZE);

                    // Fill cell based on status
                    Board.Status status = board.getCellStatus(row, col);
                    switch (status) {
                        case SHIP:
                            g.setColor(Color.GRAY);
                            g.fillRect(x + 1, y + 1, GRID_SIZE - 2, GRID_SIZE - 2);
                            break;
                        case HIT:
                            g.setColor(Color.RED);
                            g.fillRect(x + 1, y + 1, GRID_SIZE - 2, GRID_SIZE - 2);
                            break;
                        case MISS:
                            g.setColor(Color.WHITE);
                            g.fillRect(x + 1, y + 1, GRID_SIZE - 2, GRID_SIZE - 2);
                            break;
                    }
                }
            }
        }

        // Other methods to update the view, handle user interactions, etc., would be placed here.
    }

