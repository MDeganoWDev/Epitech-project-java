package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;
import main.java.mvc.model.Player;
import main.java.mvc.model.Ship.Ship;
import main.java.mvc.view.component.BackgroundGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShipPlacementPanel extends JPanel {
    private final Player player;
    private Image backgroundImage;
    private Ship selectedShip;
    private JPanel boardPanel;
    private JList<Ship> shipList;
    private DefaultListModel<Ship> shipListModel;
    private JPanel buttonPanel;
    private JButton toggleOrientationButton;
    private boolean horizontalPlacement = true;

    /**
     * Constructor for the ShipPlacementPanel class
     * @param player Player object
     */
    public ShipPlacementPanel(Player player) {
        this.player = player;
        initializeComponents();
        initializeButton();
        try {
            backgroundImage = new ImageIcon("src/main/resources/Ocean2.jpg").getImage();
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
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Initializes the components
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        shipListModel = new DefaultListModel<>();
        player.getFaction().getShips().forEach(shipListModel::addElement);

        shipList = new JList<>(shipListModel);
        shipList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shipList.addListSelectionListener(e -> selectedShip = shipList.getSelectedValue());

        shipList.setOpaque(false);
        shipList.setBackground(new Color(0, 0, 0, 0));

        JScrollPane scrollPanel = new JScrollPane(shipList);
        scrollPanel.setOpaque(false);
        scrollPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel shipListPanel = new BackgroundGamePanel("src/main/resources/Wood.jpg");

        shipListPanel.add(scrollPanel);
        add(shipListPanel, BorderLayout.WEST);

        boardPanel = createBoardPanel(player.getOwnBoard());
        add(boardPanel, BorderLayout.CENTER);
    }

    /**
     * Initializes the button
     */
    private void initializeButton(){
        this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(buttonPanel, BorderLayout.SOUTH);

        placeAllShipsButton();
        resetButton();
        startGameButton();
        toggleOrientationButton();
    }

    /**
     * Creates the place all ships button
     */
    private void placeAllShipsButton(){
        JButton placeAllShipsButton = new JButton("Place All Ships");
        placeAllShipsButton.addActionListener(e -> {
            resetShipPlacement();
            player.getOwnBoard().placeAllShips(player.getFaction().getShips());
            updateBoardUI(boardPanel, player.getOwnBoard());
            shipListModel.clear();
            System.out.println("Place all ships button clicked");
        });
        this.buttonPanel.add(placeAllShipsButton);
    }

    /**
     * Creates the reset button
     */
    private void resetButton(){
        JButton resetButton = new JButton("Reset Ships");
        resetButton.addActionListener(e -> {
            resetShipPlacement();
            System.out.println("Reset button clicked");
        });
        this.buttonPanel.add(resetButton);
    }

    /**
     * Creates the start game button
     */
    private void startGameButton(){
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            if (shipListModel.isEmpty()) {
                GameController.aiShipPlacement();
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

    /**
     * Creates the toggle orientation button
     */
    private void toggleOrientationButton(){
        this.toggleOrientationButton = new JButton("Toggle Orientation : Horizontal");
        toggleOrientationButton.addActionListener(e -> {
            horizontalPlacement = !horizontalPlacement;
            toggleOrientationButton.setText("Toggle Orientation : " + (horizontalPlacement ? "Horizontal" : "Vertical"));
            System.out.println("Toggle orientation button clicked : " + (horizontalPlacement ? "Horizontal" : "Vertical") + " orientation");
        });
        this.buttonPanel.add(toggleOrientationButton);
    }

    /**
     * Creates the board panel
     * @param board Board object
     * @return JPanel object
     */
    private JPanel createBoardPanel(Board board) {
        JPanel panel = new JPanel(new GridLayout(board.getRows(), board.getColumns()));
        panel.setOpaque(false);
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                JPanel cell = new JPanel();
                cell.setOpaque(false);
                cell.setBorder(BorderFactory.createLineBorder(Color.black));
                int finalRow = row;
                int finalCol = col;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedShip != null) {
                            selectedShip.setHorizontal(horizontalPlacement);

                            boolean placed = board.placeShip(finalRow, finalCol, selectedShip.getLength(), horizontalPlacement);
                            if (placed) {
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

    /**
     * Updates the board UI
     * @param boardPanel JPanel object
     * @param board Board object
     */
    private void updateBoardUI(JPanel boardPanel, Board board) {
        Component[] components = boardPanel.getComponents();
        int columns = board.getColumns();

        for (int i = 0; i < components.length; i++) {
            JPanel cell = (JPanel) components[i];
            int row = i / columns;
            int col = i % columns;

            if (board.getCellStatus(row, col) == Board.Status.SHIP) {
                cell.setBackground(Color.gray);
                cell.setOpaque(true);
            } else {
                cell.setBackground(null);
                cell.setOpaque(false);
            }
        }
    }

    /**
     * Resets the ship placement
     */
    private void resetShipPlacement() {
        player.getOwnBoard().resetBoard();
        updateBoardUI(boardPanel, player.getOwnBoard());
        resetShipList();
    }

    /**
     * Resets the ship list
     */
    private void resetShipList() {
        shipListModel.clear();
        player.getFaction().getShips().forEach(shipListModel::addElement);
    }
}
