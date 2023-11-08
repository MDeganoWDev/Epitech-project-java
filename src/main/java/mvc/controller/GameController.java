package main.java.mvc.controller;

import main.java.mvc.model.Board;
import main.java.mvc.model.Faction;
import main.java.mvc.model.Player;
import main.java.mvc.view.MainFrame;
import javax.swing.*;

public class GameController {
    private int boardSize;
    private MainFrame mainFrame;
    private boolean isGameInProgress;

    public GameController(int board) {
        this.boardSize = board;
        mainFrame = new MainFrame(this);
        isGameInProgress = false;
    }
    public void selectFaction(String name, Faction faction, Faction faction2) {
        Player player1 = new Player(name, faction, boardSize);
        Player player2 = new Player("Player 2",faction2, boardSize);
    }
    public void startNewGame() {
        isGameInProgress = true;
        board.resetBoard();
        // TODO: Implement ship placement logic here, potentially with user input
        if (!placeShip(2, 3, 4, true)) {
            JOptionPane.showMessageDialog(mainFrame, "Failed to place ship at the start of the game.",
                    "Ship Placement Error", JOptionPane.ERROR_MESSAGE);
        }
        mainFrame.repaintBoard();
    }
    public boolean placeShip(int row, int col, int length, boolean horizontal) {
        if (!isGameInProgress) {
            JOptionPane.showMessageDialog(mainFrame, "Can't place ships until the game starts.",
                    "Game Not In Progress", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (board.placeShip(row, col, length, horizontal)) {
            mainFrame.repaintBoard();
            return true;
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Can't place a ship there.",
                    "Invalid Ship Placement", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public void takeShot(int row, int col) {
        if (!isGameInProgress) {
            JOptionPane.showMessageDialog(mainFrame, "Can't take shots until the game starts.",
                    "Game Not In Progress", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (board.getCellStatus(row, col) == Board.Status.EMPTY ||
                board.getCellStatus(row, col) == Board.Status.SHIP) {
            board.takeShot(row, col);
            if (areAllShipsSunk()) {
                JOptionPane.showMessageDialog(mainFrame, "All ships have been sunk! Game over.",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                isGameInProgress = false;
            }
            mainFrame.repaintBoard();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "You've already shot there.",
                    "Invalid Shot", JOptionPane.WARNING_MESSAGE);
        }
    }
    public boolean areAllShipsSunk() {
        return board.areAllShipsSunk();
    }
}
