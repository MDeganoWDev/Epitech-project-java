package controller;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.AI.EasyAi;
import main.java.mvc.model.AI.VeryEasyAi;
import main.java.mvc.model.AI.VeryHardAi;
import main.java.mvc.model.Faction.Angleterre;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Faction.France;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class TestGameController {

    private GameController gameController;

    @Before
    public void setUp() {
        gameController = new GameController();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();
        gameController.selectFaction(faction1, faction2, 10);

        gameController.selectMainMenuView();
    }

    @Test
    public void testGameView() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);
        GameController.shipPlacementView();

        GameController.gameView();

        assertEquals(GameController.GameState.GAME_IN_PROGRESS, getGameState());
    }

    @Test
    public void testSelectFactionView() {
        System.out.println("Before selecting faction view: " + getGameState());
        gameController.selectFactionView();
        System.out.println("After selecting faction view: " + getGameState());
        assertEquals(GameController.GameState.SELECTING_FACTIONS, getGameState());
    }


    @Test
    public void testShipPlacementView() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);

        GameController.shipPlacementView();

        assertEquals(GameController.GameState.PLACING_SHIPS, getGameState());
    }

    @Test
    public void testCombatLoop() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);
        GameController.shipPlacementView();
        GameController.gameView();

        GameController.setAiStrategy(new VeryEasyAi());

        GameController.combatLoop(2, 3);
    }

    @Test
    public void testAiShipPlacement() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);
        GameController.aiShipPlacement();
    }

    @Test
    public void testAiTurn() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);
        GameController.shipPlacementView();
        GameController.gameView();

        GameController.setAiStrategy(new VeryHardAi());

        GameController.aiTurn(GameController.player1.getOwnBoard(), GameController.player2.getTrackingBoard());
    }

    @Test
    public void testSurrender() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);
        GameController.shipPlacementView();

        System.out.println("Before surrender: " + getGameState());
        GameController.surrender();
        System.out.println("After surrender: " + getGameState());

        assertEquals(GameController.GameState.PLACING_SHIPS, getGameState());
    }

    @Test
    public void testVictoryView() {
        GameController.newGame();

        Faction faction1 = new Angleterre();
        Faction faction2 = new France();

        GameController.selectFaction(faction1, faction2, 10);
        GameController.shipPlacementView();
        GameController.gameView();

        GameController.victoryView("Player1");

        assertEquals(GameController.GameState.GAME_IN_PROGRESS, getGameState());
    }

    private GameController.GameState getGameState() {
        try {
            // Utilise la réflexion pour accéder à la variable privée 'gameState'
            Field gameStateField = GameController.class.getDeclaredField("gameState");
            gameStateField.setAccessible(true);
            return (GameController.GameState) gameStateField.get(gameController);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}