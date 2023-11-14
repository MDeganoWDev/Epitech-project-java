package model;

import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestFaction {
    private Faction faction;

    @Before
    public void setUp() {
        faction = new Faction("Test Faction");
    }

    @Test
    public void testGetName() {
        assertEquals("Test Faction", faction.getName());
    }

    @Test
    public void testBonjour(){
        System.out.println("Bonjour");
    }

    @Test
    public void testAddShip() {
        Ship ship1 = new Ship("Ship 1", 3);
        Ship ship2 = new Ship("Ship 2", 4);

        faction.addShip(ship1);
        faction.addShip(ship2);

        List<Ship> ships = faction.getShips();
        assertEquals(2, ships.size());
        assertEquals("Ship 1", ships.get(0).getName());
        assertEquals("Ship 2", ships.get(1).getName());
    }

    @Test
    public void testSetName() {
        faction.setName("New Faction Name");
        assertEquals("New Faction Name", faction.getName());
    }
}
