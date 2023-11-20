package model;

import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Faction.France;
import main.java.mvc.model.Ship.Ship;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFrance {
    @Test
    public void testFranceShips() {

        France france = new France();
        Faction franceFaction = france;
        assertTrue(franceFaction.getShips().size() == 7);

        for (Ship ship : franceFaction.getShips()) {
            assertTrue(ship.getLength() > 0);

            if (ship.getLength() == 4) {
                assertEquals("La Gloire", ship.getName());
            } else if (ship.getLength() == 3) {
                assertTrue(ship.getName().equals("L'Espérance") || ship.getName().equals("Le Triomphant"));
            } else if (ship.getLength() == 2) {
                assertTrue(ship.getName().equals("Le Brave"));
            } else if (ship.getLength() == 1) {
                assertTrue(ship.getName().equals("Le Fidèle") || ship.getName().equals("L'Agile") || ship.getName().equals("Le Vigilant"));
            }
        }
    }
}
