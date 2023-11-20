package model;

import main.java.mvc.model.Board;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Player;
import main.java.mvc.model.Ship.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPlayer {


    private Player player;
    private Faction faction;
    private List<Ship> ships;

    @Before
    public void setUp() {
        faction = new Faction("Test Faction");
        player = new Player("Test Player", faction, 10);


        ships = new ArrayList<>();
        ships.add(new Ship("Ship1", 3));
        ships.add(new Ship("Ship2", 4));

    }

    @Test
    public void testPlayerName() {
        assertEquals("Test Player", player.getName());
    }

    @Test
    public void testPlayerFaction() {
        assertEquals(faction, player.getFaction());
    }

    @Test
    public void testPlayerOwnBoard() {
        Board ownBoard = player.getOwnBoard();
        assertNotNull(ownBoard);

        assertEquals(10, ownBoard.getRows());
        assertEquals(10, ownBoard.getColumns());
    }

    @Test
    public void testPlayerTrackingBoard() {
        Board trackingBoard = player.getTrackingBoard();
        assertNotNull(trackingBoard);

        assertEquals(10, trackingBoard.getRows());
        assertEquals(10, trackingBoard.getColumns());
    }

    @Test
    public void testPlayerIsTurn() {
        assertFalse(player.isTurn());
        player.setTurn(true);
        assertTrue(player.isTurn());
    }

    @Test
    public void testPlayerIsAI() {
        assertFalse(player.isAI());
        player.setAI(true);
        assertTrue(player.isAI());
    }



}
