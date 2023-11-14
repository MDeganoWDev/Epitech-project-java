package main.java.mvc.controller;

import main.java.mvc.model.AI.AiStrategy;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Board;
import main.java.mvc.model.Player;
import main.java.mvc.view.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameController {
    public enum GameState {
        NOT_STARTED,
        SELECTING_FACTIONS,
        PLACING_SHIPS,
        GAME_IN_PROGRESS,
        GAME_OVER
    }
    private static final List<GameObserver> observers = new ArrayList<>();
    private static AiStrategy aiStrategy;
    private static MainFrame mainFrame;
    private static GameState gameState;
    private static Player player1;
    private static Player player2;

    public GameController() {
        gameState = GameState.NOT_STARTED;
        initializeGame();
    }
    public static void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    private static void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
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

        selectMainMenuView();
    }
    public static void selectMainMenuView() {
        if (gameState != GameState.NOT_STARTED) {
            JOptionPane.showMessageDialog(mainFrame, "Finish the current game before starting a new one.",
                    "Game In Progress", JOptionPane.WARNING_MESSAGE);
            return;
        }

        System.out.println("Game status : " + gameState);
        System.out.println("Switching to main menu view");

        switchPanel(new MainMenuPanel());
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
        GamePanel gamePanel = new GamePanel(player1);
        addObserver(gamePanel);

        System.out.println("Game status : " + gameState);
        System.out.println("Game started");

        switchPanel(gamePanel);
    }
    public static void victoryView(String winner){
        if (gameState != GameState.GAME_OVER) {
            JOptionPane.showMessageDialog(mainFrame, "Finish the game before viewing the victory screen.",
                    "Game Not Over", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gameState = GameState.NOT_STARTED;

        System.out.println("Game status : " + gameState);
        System.out.println("Switching to victory view");

        switchPanel(new VictoryPanel(winner));
    }
    public static void surrender() {
        if (gameState != GameState.GAME_IN_PROGRESS) {
            JOptionPane.showMessageDialog(mainFrame, "Start the game before surrendering.",
                    "Game Not Started", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gameState = GameState.GAME_OVER;

        System.out.println("Game status : " + gameState);
        System.out.println("Player " + player1.getName() + " surrendered");

        victoryView(player2.getName());
    }
    public static void selectFaction (Faction faction1, Faction faction2, int gridSize) {
        player1 = new Player("Player 1", faction1, gridSize);
        player2 = new Player("player 2", faction2, gridSize);

        player2.setAI(true);
        player1.setTurn(true);
        player2.setTurn(false);

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
    public static String combatLoop(int row, int col) {
        if (gameState != GameState.GAME_IN_PROGRESS) {
            JOptionPane.showMessageDialog(mainFrame, "Start the game before attacking.",
                    "Game Not Started", JOptionPane.WARNING_MESSAGE);
            return "ERROR";
        }
        Player currentPlayer = player1.isTurn() ? player1 : player2;
        Player opponentPlayer = player1.isTurn() ? player2 : player1;

        if (player2.isAI()) {
            String result = attackPhase(opponentPlayer.getOwnBoard(), row, col);
            currentPlayer.setTurn(false);
            opponentPlayer.setTurn(true);

            checkGameState();

            aiTurn(currentPlayer.getOwnBoard(), opponentPlayer.getTrackingBoard());
            currentPlayer.setTurn(true);
            opponentPlayer.setTurn(false);

            checkGameState();
            notifyObservers();
            return result;
        } else {
            String result = attackPhase(opponentPlayer.getOwnBoard(), row, col);
            currentPlayer.setTurn(false);
            opponentPlayer.setTurn(true);

            checkGameState();
            return result;
        }

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
    public static void aiTurn(Board playerBoard, Board aiTrackingBoard) {
        Point aiMove = aiStrategy.makeMove(aiTrackingBoard);
        String result = attackPhase(playerBoard, aiMove.x, aiMove.y);

        System.out.println("AI attack : " + aiMove.x + ", " + aiMove.y);
        System.out.println(result);

        playerBoard.updateCellStatus(aiMove.x, aiMove.y, Board.Status.valueOf(result));
        notifyObservers();
    }
    public static void checkGameState() {
        if (player1.getOwnBoard().areAllShipsSunk()) {
            gameState = GameState.GAME_OVER;
            switchPanel(new VictoryPanel(player2.getName()));
        } else if (player2.getOwnBoard().areAllShipsSunk()) {
            gameState = GameState.GAME_OVER;
            switchPanel(new VictoryPanel(player1.getName()));
        } else {
            gameState = GameState.GAME_IN_PROGRESS;
        }
    }
    public static void newGame() {
        player1 = null;
        player2 = null;
        aiStrategy = null;
        gameState = GameState.NOT_STARTED;
        selectFactionView();
    }
    public static void goToMainMenu(){
        player1 = null;
        player2 = null;
        aiStrategy = null;
        gameState = GameState.NOT_STARTED;
        selectMainMenuView();
    }
}
