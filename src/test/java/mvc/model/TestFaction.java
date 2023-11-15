package model;

import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestFaction {

    private Faction faction;

    @Before
    public void setUp() {
        faction = new Faction("Alliance");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Alliance", faction.getName());
        assertNotNull(faction.getShips());
        assertTrue(faction.getShips().isEmpty());
    }

    @Test
    public void testAddShip() {
        Ship ship1 = new Ship1("Ship1");
        Ship ship2 = new Ship2("Ship2");

        faction.addShip(ship1);
        faction.addShip(ship2);

        List<Ship> ships = faction.getShips();
        assertEquals(2, ships.size());
        assertTrue(ships.contains(ship1));
        assertTrue(ships.contains(ship2));
    }


    @Test
    public void testSetters() {
        faction.setName("Horde");
        assertEquals("Horde", faction.getName());

        List<Ship> newShips = new ArrayList<>();
        newShips.add(new Ship1("NewShip1"));
        newShips.add(new Ship2("NewShip2"));

        faction.setShips(newShips);

        List<Ship> ships = faction.getShips();
        assertEquals(2, ships.size());
        assertTrue(ships.contains(newShips.get(0)));
        assertTrue(ships.contains(newShips.get(1)));
    }
}
