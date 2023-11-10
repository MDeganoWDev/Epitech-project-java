package main.java.mvc.view;

import main.java.mvc.model.Board;
import main.java.mvc.model.Player;
import main.java.mvc.model.Ship;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.awt.*;
import javax.swing.*;
public class PlaceShipPanel extends JPanel {
    private Board board;
    private List<Ship> ships;
    private Ship selectedShip;
    private JPanel gridPanel;
    private boolean dragging = false;
    private Point dragPoint;

    public PlaceShipPanel(Player player1, Player player2) {
        this.board = player1.getOwnBoard();
        this.ships = player1.getFaction().getShips();
        setLayout(new BorderLayout());

        initializeDock();
        initializeGrid();

        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(800, 400));
    }
    private void initializeDock() {
        JPanel dockPanel = new JPanel();
        dockPanel.setLayout(new FlowLayout());
        for (Ship ship : ships) {
            JPanel shipPanel = new JPanel();
            shipPanel.setBackground(Color.GRAY);
            shipPanel.setPreferredSize(new Dimension(30 * ship.getLength(), 30));

            shipPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    selectedShip = ship;
                    dragging = true;
                    dragPoint = e.getPoint();
                    getParent().setComponentZOrder(shipPanel, 0);
                }
                public void mouseReleased(MouseEvent e) {
                    dragging = false;
                    dragPoint = null;  // Reset dragPoint when done dragging
                    getParent().setComponentZOrder(shipPanel, getParent().getComponentCount() - 1);
                    // Handle drop logic...
                }
            });
            shipPanel.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dragging && dragPoint != null) {
                        // Update the position of the dragged ship panel
                        Point currentPoint = e.getPoint();
                        shipPanel.setLocation(shipPanel.getX() + currentPoint.x - dragPoint.x,
                                shipPanel.getY() + currentPoint.y - dragPoint.y);
                    }
                }
            });
            dockPanel.add(shipPanel);
        }
        add(dockPanel, BorderLayout.WEST);
    }
    private void initializeGrid() {
        gridPanel = new JPanel(new GridLayout(board.getRows(), board.getColumns()));
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                JPanel cellPanel = createCellPanel(row, col);
                gridPanel.add(cellPanel);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    private JPanel createCellPanel(int row, int col) {
        JPanel cellPanel = new JPanel();
        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cellPanel.setBackground(Color.WHITE);
        cellPanel.setPreferredSize(new Dimension(30, 30));
        cellPanel.addMouseListener(new CellPanelMouseListener(row, col));
        return cellPanel;
    }

    private class CellPanelMouseListener extends MouseAdapter {
        private final int row;
        private final int col;

        public CellPanelMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (dragging && selectedShip != null) {
                // Assuming each cell is 30x30 pixels
                int cellWidth = 30;
                int cellHeight = 30;

                // Convert the point to grid coordinates
                Point gridPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), gridPanel);
                int dropRow = gridPoint.y / cellHeight;
                int dropCol = gridPoint.x / cellWidth;

                if (placeShip(selectedShip, dropRow, dropCol)) {
                    updateGridUI();
                } else {
                    // Handle failed placement (e.g., show error message)
                }
                dragging = false;
            }
        }
    }
    private void updateGridUI() {
        Component[] gridCells = gridPanel.getComponents();
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                JPanel cellPanel = (JPanel) gridCells[row * board.getColumns() + col];
                switch (board.getCellStatus(row, col)) {
                    case EMPTY:
                        cellPanel.setBackground(Color.WHITE);
                        break;
                    case SHIP:
                        cellPanel.setBackground(Color.GRAY); // Change color to indicate a ship
                        break;
                    // You can add cases for HIT and MISS if needed
                }
            }
        }
    }
    public boolean placeShip(Ship ship, int x, int y) {
        this.selectedShip = ship;
        if (this.board.placeShip(x, y, ship.getLength(), ship.isHorizontal())) {
            ships.remove(ship);
            updateGridUI();
            return true;
        } else {
            // Handle failed placement
            return false;
        }
    }
    public void rotateShip() {
        if (this.selectedShip != null) {
            this.selectedShip.setHorizontal(!this.selectedShip.isHorizontal());
            // Update UI to reflect rotation
        }
    }
}
