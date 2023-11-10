package main.java.mvc.controller;

import main.java.mvc.model.Board;
import main.java.mvc.model.Faction;
import main.java.mvc.model.Player;
import main.java.mvc.view.GamePanel;
import main.java.mvc.view.MainFrame;
import main.java.mvc.view.PlaceShipPanel;

import javax.swing.*;

public class GameController {
    public enum GameState {
        NOT_STARTED,
        PLACING_SHIPS,
        GAME_IN_PROGRESS,
        GAME_OVER
    }

    private Player player1;
    private Player player2;
    private MainFrame mainFrame;
    private PlaceShipPanel placeShipPanel;
    private GameState gameState;

    public GameController(int boardSize, Faction factionPlayer1, Faction factionPlayer2) {
        this.player1 = new Player("Player 1", factionPlayer1, boardSize);
        this.player2 = new Player("Player 2", factionPlayer2, boardSize);
        this.mainFrame = new MainFrame(player1, player2);
        this.placeShipPanel = new PlaceShipPanel(player1, player2);
        this.gameState = GameState.NOT_STARTED;
    }

    public void initializeGame() {
        if (gameState != GameState.NOT_STARTED) {
            JOptionPane.showMessageDialog(mainFrame, "Finish the current game before starting a new one.",
                    "Game In Progress", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // You can call a method here to display a GUI for ship placement
        // or place ships automatically for simplicity.
        placeShipsForPlayer(player1);
        placeShipsForPlayer(player2);
        gameState = GameState.GAME_IN_PROGRESS;
        mainFrame.startGame();
    }

    private void placeShipsForPlayer(Player player) {
        this.placeShipPanel.

    }

    public boolean takeTurn(Player player, int row, int col) {
        if (gameState != GameState.GAME_IN_PROGRESS) {
            JOptionPane.showMessageDialog(mainFrame, "No game in progress. Start a new game first.",
                    "No Game", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        Player opponent = (player == player1) ? player2 : player1;
        boolean hit = opponent.getOwnBoard().takeShot(row, col);
        Board.Status status = hit ? Board.Status.HIT : Board.Status.MISS;
        player.getTrackingBoard().updateCellStatus(row, col, status);

        // After a turn, check if the game is over
        if (isGameOver()) {
            gameState = GameState.GAME_OVER;
            String winner = (player == player1 && player2.getOwnBoard().areAllShipsSunk()) ? "Player 1" : "Player 2";
            JOptionPane.showMessageDialog(mainFrame, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.endGame();
        }

        return hit;
    }

    public boolean isGameOver() {
        return player1.getOwnBoard().areAllShipsSunk() || player2.getOwnBoard().areAllShipsSunk();
    }

    public void resetGame() {
        // Reset boards and game state
        player1.reset();
        player2.reset();
        gameState = GameState.NOT_STARTED;
        mainFrame.resetGame();
    }
}
