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

    /**
     * Enum for the game state.
     * NOT_STARTED: The game has not started.
     * SELECTING_FACTIONS: The players are selecting their factions.
     * PLACING_SHIPS: The players are placing their ships.
     * GAME_IN_PROGRESS: The game is in progress.
     * GAME_OVER: The game is over.
     */
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
    public static Player player1;
    public static Player player2;
    private static int boardSize;

    /**
     * Constructor for GameController.
     * Initializes the game state to NOT_STARTED.
     */
    public GameController() {
        gameState = GameState.NOT_STARTED;
        initializeGame();
    }

    /**
     * Adds an observer to the list of observers.
     * @param observer The observer to add.
     */
    public static void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers.
     */
    private static void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * Switches the current panel to the specified panel.
     * @param panel The panel to switch to.
     * The current panel is removed and the new panel is added.
     * The frame is then revalidated and repainted.
     * If an exception occurs, an error message is displayed.
     */
    public static void switchPanel(JPanel panel) {
        try {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().add(panel);
            mainFrame.getContentPane().revalidate();
            mainFrame.getContentPane().repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Erreur lors du changement de panneau: " + e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initializes the game.
     * If the game is already in progress, a warning message is displayed.
     * Otherwise, the main frame is initialized and the main menu view is selected.
     */
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

    /**
     * Selects the main menu view.
     * If the game is already in progress, a warning message is displayed.
     * Otherwise, the main menu view is selected.
     */
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

    /**
     * Selects the faction selection view.
     * If the game is already in progress, a warning message is displayed.
     * Otherwise, the faction selection view is selected.
     */
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

    /**
     * Selects the ship placement view.
     * If the game is not in the SELECTING_FACTIONS state, a warning message is displayed.
     * Otherwise, the ship placement view is selected.
     */
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

    /**
     * Selects the game view.
     * If the game is not in the PLACING_SHIPS state, a warning message is displayed.
     * Otherwise, the game view is selected.
     */
    public static void gameView(){
        if (gameState != GameState.PLACING_SHIPS) {
            JOptionPane.showMessageDialog(mainFrame, "Finish placing ships before starting the game.",
                    "Ships Not Placed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gameState = GameState.GAME_IN_PROGRESS;
        GamePanel gamePanel = new GamePanel(player1, boardSize);
        addObserver(gamePanel);

        System.out.println("Game status : " + gameState);
        System.out.println("Game started");

        switchPanel(gamePanel);
    }

    /**
     * Selects the victory view.
     * If the game is not in the GAME_OVER state, a warning message is displayed.
     * Otherwise, the victory view is selected.
     * @param winner The name of the winner.
     */
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

    /**
     * Selects the defeat view.
     * Allow the player to surrender the game.
     * If the game is not in the GAME_OVER state, a warning message is displayed.
     * Otherwise, the defeat view is selected.
     */
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

    /**
     * Selects the defeat view.
     * If the game is not in the GAME_OVER state, a warning message is displayed.
     * Otherwise, the defeat view is selected.
     * @param faction1 The faction of player 1.
     * @param faction2 The faction of player 2.
     * @param gridSize The size of the board.
     */
    public static void selectFaction (Faction faction1, Faction faction2, int gridSize) {
        try {
            player1 = new Player("Player 1", faction1, gridSize);
            player2 = new Player("player 2", faction2, gridSize);
            boardSize = gridSize;

            player2.setAI(true);
            player1.setTurn(true);
            player2.setTurn(false);

            System.out.println("Player 1 : name is " + player1.getName() + ", faction is " + player1.getFaction().getName());
            System.out.println("Player 2 : name is " + player2.getName() + ", faction is " + player2.getFaction().getName());
            System.out.println("Grid size : " + gridSize);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Erreur lors de la sélection de la faction: " + e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sets the AI strategy.
     * @param aiStrategy The AI strategy.
     */
    public static void setAiStrategy(AiStrategy aiStrategy) {
        GameController.aiStrategy = aiStrategy;
        System.out.println("AI strategy : " + aiStrategy);
    }

    /**
     * Places all ships on the board.
     * If the game is not in the PLACING_SHIPS state, a warning message is displayed.
     * Otherwise, all ships are placed on the board.
     */
    public static void aiShipPlacement() {
        player2.getOwnBoard().placeAllShips(player2.getFaction().getShips());
    }

    /**
     * Dictate the combat loop.
     * If the game is not in the GAME_IN_PROGRESS state, a warning message is displayed.
     * Otherwise, the combat loop is dictated.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return The result of the attack.
     * Check if the player is an AI.
     * If the player is an AI, the AI makes a move.
     * Otherwise, the player makes a move.
     * Check if the game is over.
     */
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

    /**
     * Dictate the attack phase.
     * @param ennemyBoard The board of the opponent.
     * @param finalI The row of the cell.
     * @param finalJ The column of the cell.
     * @return The result of the attack.
     */
    public static String attackPhase(Board ennemyBoard, int finalI, int finalJ){
        if (ennemyBoard == player1.getOwnBoard()) {
            System.out.println("Player 2 is attacking: " + finalI + ", " + finalJ);
        } else {
            System.out.println("Player 1 is attacking: " + finalI + ", " + finalJ);
        }
        if (ennemyBoard.takeShot(finalI, finalJ)) {
            return "HIT";
        } else {
            System.out.println("Miss!");
            return "MISS";
        }
    }

    /**
     * Dictate the AI turn.
     * @param playerBoard The board of the player.
     * @param aiTrackingBoard The tracking board of the AI.
     */
    public static void aiTurn(Board playerBoard, Board aiTrackingBoard) {
        Point aiMove = aiStrategy.makeMove(aiTrackingBoard);
        String result = attackPhase(playerBoard, aiMove.x, aiMove.y);
        playerBoard.updateCellStatus(aiMove.x, aiMove.y, Board.Status.valueOf(result));
        notifyObservers();
    }

    /**
     * Check the game state.
     * If the game is over, the victory view is selected.
     * Otherwise, the game is in progress.
     */
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

    /**
     * Starts a new game.
     * Resets the game state and selects the faction selection view.
     */
    public static void newGame() {
        player1 = null;
        player2 = null;
        aiStrategy = null;
        gameState = GameState.NOT_STARTED;
        selectFactionView();
    }

    /**
     * Returns to the main menu.
     * Resets the game state and selects the main menu view.
     */
    public static void goToMainMenu(){
        player1 = null;
        player2 = null;
        aiStrategy = null;
        gameState = GameState.NOT_STARTED;
        selectMainMenuView();
    }

    /**
     * Getter for boardSize.
     * @return The size of the board.
     */
    public static int getBoardSize() {
        return boardSize;
    }

}
