package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;
import main.java.mvc.model.Player;
import main.java.mvc.model.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ShipPlacementPanel extends JPanel {
    private Player player;
    private Ship selectedShip;
    private JPanel boardPanel;
    private JList<Ship> shipList;
    private DefaultListModel<Ship> shipListModel;
    private JPanel buttonPanel;
    private JButton resetButton;
    private JButton startGameButton;
    private JButton toggleOrientationButton;
    private boolean horizontalPlacement = true; // Default placement orientation

    public ShipPlacementPanel(Player player) {
        this.player = player;
        initializeComponents();
        initializeButton();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Initialize ship list
        shipListModel = new DefaultListModel<>();
        player.getFaction().getShips().forEach(shipListModel::addElement);
        shipList = new JList<>(shipListModel);
        shipList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shipList.addListSelectionListener(e -> selectedShip = shipList.getSelectedValue());
        add(new JScrollPane(shipList), BorderLayout.WEST);

        // Initialize board panel
        boardPanel = createBoardPanel(player.getOwnBoard());
        add(boardPanel, BorderLayout.CENTER);

    }
    private void initializeButton(){
        // Panel to hold buttons
        this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(buttonPanel, BorderLayout.SOUTH);

        resetButton();
        startGameButton();
        toggleOrientationButton();
    }
    private void resetButton(){
        this.resetButton = new JButton("Reset Ships");
        resetButton.addActionListener(e -> {
            resetShipPlacement();
            System.out.println("Reset button clicked");
        });
        this.buttonPanel.add(resetButton);
    }
    private void startGameButton(){
        this.startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            if (shipListModel.isEmpty()) {
                GameController.gameView();
                System.out.println("Start game button clicked");
            } else {
                JOptionPane.showMessageDialog(this, "Place all ships before starting the game.",
                        "Ships Not Placed", JOptionPane.WARNING_MESSAGE);
                System.out.println("Start game button cant be clicked");
            }
        });
        this.buttonPanel.add(startGameButton);
    }
    private void toggleOrientationButton(){
        this.toggleOrientationButton = new JButton("Toggle Orientation : Horizontal");
        toggleOrientationButton.addActionListener(e -> {
            horizontalPlacement = !horizontalPlacement;
            toggleOrientationButton.setText("Toggle Orientation : " + (horizontalPlacement ? "Horizontal" : "Vertical"));
            System.out.println("Toggle orientation button clicked : " + (horizontalPlacement ? "Horizontal" : "Vertical") + " orientation");
        });
        this.buttonPanel.add(toggleOrientationButton);
    }
    private JPanel createBoardPanel(Board board) {
        JPanel panel = new JPanel(new GridLayout(board.getRows(), board.getColumns()));
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.black));
                int finalRow = row;
                int finalCol = col;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedShip != null) {
                            // Set the orientation of the ship before placing
                            selectedShip.setHorizontal(horizontalPlacement);

                            // Attempt to place the ship at the clicked cell
                            boolean placed = board.placeShip(finalRow, finalCol, selectedShip.getLength(), horizontalPlacement);
                            if (placed) {
                                // Update UI to reflect ship placement
                                updateBoardUI(panel, board);
                                shipListModel.removeElement(selectedShip);
                                selectedShip = null;
                            }
                        }
                    }
                });
                panel.add(cell);
            }
        }
        return panel;
    }

    private void updateBoardUI(JPanel boardPanel, Board board) {
        Component[] components = boardPanel.getComponents();
        int columns = board.getColumns(); // Number of columns in the board

        for (int i = 0; i < components.length; i++) {
            JPanel cell = (JPanel) components[i];
            int row = i / columns; // Calculate row
            int col = i % columns; // Calculate column

            // Directly use board's grid status
            if (board.getCellStatus(row, col) == Board.Status.SHIP) {
                cell.setBackground(Color.gray);
            } else {
                cell.setBackground(null);
            }
        }
    }
    private void resetShipPlacement() {
        player.getOwnBoard().resetBoard();
        updateBoardUI(boardPanel, player.getOwnBoard());
        resetShipList();
    }

    private void resetShipList() {
        shipListModel.clear();
        player.getFaction().getShips().forEach(shipListModel::addElement);
    }
}
