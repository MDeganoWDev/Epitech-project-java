package model;

import main.java.mvc.model.Board;
import main.java.mvc.model.Faction.Strawhat;
import main.java.mvc.model.Faction.BigMom;
import main.java.mvc.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class TestPlayer {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("Player1", new Strawhat(), 10);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Player1", player.getName());
        assertTrue(player.getFaction() instanceof Strawhat);
        assertNotNull(player.getOwnBoard());
        assertNotNull(player.getTrackingBoard());
        assertFalse(player.isTurn());
        assertFalse(player.isAI());
    }

    @Test
    public void testSetters() {
        player.setName("NewName");
        assertEquals("NewName", player.getName());

        player.setFaction(new BigMom());
        assertTrue(player.getFaction() instanceof BigMom);

        player.setTurn(true);
        assertTrue(player.isTurn());

        player.setAI(true);
        assertTrue(player.isAI());
    }

    @Test
    public void testBoardModification() {
        Board ownBoard = player.getOwnBoard();
        Board trackingBoard = player.getTrackingBoard();

        // Modifie les deux planches
        ownBoard.placeShip(1, 2, 2, true);
        trackingBoard.takeShot(1, 2);

        // Vérifie que les modifications sont correctes
        assertEquals(Board.Status.EMPTY, ownBoard.getCellStatus(0, 0));
        assertEquals(Board.Status.EMPTY, trackingBoard.getCellStatus(1, 3));
    }
}
