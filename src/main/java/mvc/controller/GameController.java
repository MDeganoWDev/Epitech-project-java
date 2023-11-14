package main.java.mvc.controller;

import main.java.mvc.model.AI.AiStrategy;
import main.java.mvc.model.Board;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Player;
import main.java.mvc.view.GamePanel;
import main.java.mvc.view.MainFrame;
import main.java.mvc.view.SelectFactionPanel;
import main.java.mvc.view.ShipPlacementPanel;


import javax.swing.*;
import java.awt.*;

public class GameController {
    public enum GameState {
        NOT_STARTED,
        SELECTING_FACTIONS,
        PLACING_SHIPS,
        GAME_IN_PROGRESS,
        GAME_OVER
    }
    private static AiStrategy aiStrategy;
    private static MainFrame mainFrame;
    private static JPanel gamePanel;
    private static GameState gameState;
    private static Player player1;
    private static Player player2;

    public GameController() {
        gameState = GameState.NOT_STARTED;
        initializeGame();
    }
    public static void switchPanel(JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(panel);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }
    public void initializeGame() {
        if (gameState != GameState.NOT_STARTED) {
            JOptionPane.showMessageDialog(mainFrame, "Finish the current game before starting a new one.",
                    "Game In Progress", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mainFrame = new MainFrame();

        System.out.println("Game initialized");
        System.out.println("Game status : " + gameState);
    }
    public static void selectFactionView(){
        if (gameState != GameState.NOT_STARTED) {
            JOptionPane.showMessageDialog(mainFrame, "Finish the current game before starting a new one.",
                    "Game In Progress", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gameState = GameState.SELECTING_FACTIONS;

        System.out.println("Game status : " + gameState);
        System.out.println("Switching to faction selection view");

        switchPanel(new SelectFactionPanel());
    }
    public static void shipPlacementView(){
        if (gameState != GameState.SELECTING_FACTIONS) {
            JOptionPane.showMessageDialog(mainFrame, "Finish selecting factions before placing ships.",
                    "Factions Not Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gameState = GameState.PLACING_SHIPS;

        System.out.println("Game status : " + gameState);
        System.out.println("Player " + player1.getName() + " is placing ships");

        switchPanel(new ShipPlacementPanel(player1));
    }
    public static void gameView(){
        if (gameState != GameState.PLACING_SHIPS) {
            JOptionPane.showMessageDialog(mainFrame, "Finish placing ships before starting the game.",
                    "Ships Not Placed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gameState = GameState.GAME_IN_PROGRESS;
        gamePanel = new GamePanel(player1, player2);

        System.out.println("Game status : " + gameState);
        System.out.println("Game started");

        switchPanel(gamePanel);
    }
    public static void selectFaction (Faction faction1, Faction faction2, int gridSize) {
        player1 = new Player("Player 1", faction1, gridSize);
        player2 = new Player("player 2", faction2, gridSize);

        System.out.println("Player 1 : name is " + player1.getName() + ", faction is " + player1.getFaction().getName());
        System.out.println("Player 2 : name is " + player2.getName() + ", faction is " + player2.getFaction().getName());
        System.out.println("Grid size : " + gridSize);
    }
    public static void setAiStrategy(AiStrategy aiStrategy) {
        GameController.aiStrategy = aiStrategy;
        System.out.println("AI strategy : " + aiStrategy);
    }
    public static void aiShipPlacement() {
        player2.getOwnBoard().placeAllShips(player2.getFaction().getShips());
    }
    public static String attackPhase(Board ennemyBoard, int finalI, int finalJ){
        System.out.println("Coordinates: " + finalI + ", " + finalJ);
        if (ennemyBoard.takeShot(finalI, finalJ)) {
            System.out.println("Hit!");
            return "HIT";
        } else {
            System.out.println("Miss!");
            return "MISS";
        }
    }
    public static void aiTurn(Board playerBoard, Board aiBoard) {
        Point aiMove = aiStrategy.makeMove(aiBoard);
        String result = attackPhase(playerBoard, aiMove.x, aiMove.y);
        checkGameState();
    }
    private static void checkGameState() {
        if (player1.getOwnBoard().areAllShipsSunk()) {
            gameState = GameState.GAME_OVER;
            // Handle victory for AI
        } else if (player2.getOwnBoard().areAllShipsSunk()) {
            gameState = GameState.GAME_OVER;
            // Handle victory for player
        } else {
            gameState = GameState.GAME_IN_PROGRESS;
        }
    }
}
