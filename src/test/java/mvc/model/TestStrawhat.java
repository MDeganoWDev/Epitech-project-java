package test.java.mvc.model;

import main.java.mvc.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStrawhat {

    private Strawhat strawhat;

    @Before
    public void setUp() {
        strawhat = new Strawhat();
    }

    @Test
    public void testGetName() {
        assertEquals("Strawhat", strawhat.getName());
    }

    @Test
    public void testGetShips() {
        List<Ship> ships = strawhat.getShips();
        assertEquals(6, ships.size());

        // Vérifie que tous les navires sont présents
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Franky battle 1")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Franky battle 2")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Franky battle 4")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Franky battle 6")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Franky battle 8")));
        assertTrue(ships.stream().anyMatch(ship -> ship.getName().equals("Sunny go")));
    }

    @Test
    public void testAddShip() {
        Ship newShip = new Ship1("New Ship");
        strawhat.addShip(newShip);

        List<Ship> ships = strawhat.getShips();
        assertEquals(7, ships.size());
        assertTrue(ships.contains(newShip));
    }
}

