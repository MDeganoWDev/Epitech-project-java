package model;

import main.java.mvc.model.Faction.BigMom;
import main.java.mvc.model.Ship.Ship;
import main.java.mvc.model.Ship.Ship1;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBigMom {

    private BigMom bigMom;

    @Before
    public void setUp() {
        bigMom = new BigMom();
    }

    @Test
    public void testGetName() {
        assertEquals("Big Mom", bigMom.getName());
    }

    @Test
    public void testGetShips() {
        List<Ship> ships = bigMom.getShips();
        assertEquals(6, ships.size());

        // Vérifie que tous les navires sont présents
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Big mom child 43")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Big mom child 27")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Big mom child 49")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Big mom child 61")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Big mom child 6")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Big mom admiral battleShip")));
    }

    @Test
    public void testAddShip() {
        Ship newShip = new Ship1("New Ship");
        bigMom.addShip(newShip);

        List<Ship> ships = bigMom.getShips();
        assertEquals(7, ships.size());
        assertTrue(ships.contains(newShip));
    }
}