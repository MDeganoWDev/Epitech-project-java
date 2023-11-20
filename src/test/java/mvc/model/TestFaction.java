package model;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class TestFaction {

    private Faction faction;
    private Ship ship1;
    private Ship ship2;

    @Before
    public void setUp() {
        faction = new Faction("Test Faction");
        ship1 = new Ship("Ship1",1);
        ship2 = new Ship("Ship2",2);
    }

    @Test
    public void testFactionName() {
        assertEquals("Test Faction", faction.getName());
    }

    @Test
    public void testAddShip() {
        faction.addShip(ship1);
        faction.addShip(ship2);

        List<Ship> ships = faction.getShips();

        assertTrue(ships.contains(ship1));
        assertTrue(ships.contains(ship2));
        assertEquals(2, ships.size());

    }

}
