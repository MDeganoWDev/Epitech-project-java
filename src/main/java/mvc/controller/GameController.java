package main.java.mvc.controller;

import main.java.mvc.model.Faction;
import main.java.mvc.model.Player;
import main.java.mvc.view.GamePanel;
import main.java.mvc.view.MainFrame;
import main.java.mvc.view.SelectFactionPanel;
import main.java.mvc.view.ShipPlacementPanel;


import javax.swing.*;

public class GameController {
    public enum GameState {
        NOT_STARTED,
        SELECTING_FACTIONS,
        PLACING_SHIPS,
        GAME_IN_PROGRESS,
        GAME_OVER
    }

    private static MainFrame mainFrame;
    private static GameState gameState;

    public GameController() {
        gameState = GameState.NOT_STARTED;
        initializeGame();
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
    public static void switchPanel(JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(panel);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }
    public static void selectFactionView(){
        gameState = GameState.SELECTING_FACTIONS;

        System.out.println("Game status : " + gameState);
        System.out.println("Switching to faction selection view");

        switchPanel(new SelectFactionPanel());
    }
    public static void shipPlacementView(Player player){
        gameState = GameState.PLACING_SHIPS;

        System.out.println("Game status : " + gameState);
        System.out.println("Player " + player.getName() + " is placing ships");

        switchPanel(new ShipPlacementPanel(player));
    }
    public static void gameView(){
        gameState = GameState.GAME_IN_PROGRESS;

        System.out.println("Game status : " + gameState);
        System.out.println("Game started");

        switchPanel(new GamePanel());
    }
    public static void selectFaction (Faction faction1, Faction faction2, int gridSize){
        Player player1 = new Player("Player 1", faction1, gridSize);
        Player player2 = new Player("player 2", faction2, gridSize);

        System.out.println("Player 1 : name is " + player1.getName() + ", faction is " + player1.getFaction().getName());
        System.out.println("Player 2 : name is " + player2.getName() + ", faction is " + player2.getFaction().getName());
        System.out.println("Grid size : " + gridSize);

        shipPlacementView(player1);
    }
}
