package model;

import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Faction.Portugal;
import main.java.mvc.model.Ship.Ship;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class TestPortugal {

    @Test
    public void testPortugalShips() {
        Portugal portugal = new Portugal();
        assertTrue(portugal.getShips().size() == 6);

        for (Ship ship : portugal.getShips()) {
            assertTrue(ship.getLength() > 0);
            if (ship.getLength() == 4) {
                assertEquals("São Gabriel", ship.getName());
            } else if (ship.getLength() == 3) {
                assertTrue(ship.getName().equals("São Rafael") || ship.getName().equals("Santa Maria da Graça") || ship.getName().equals("Esperança"));
            } else if (ship.getLength() == 1) {
                assertTrue(ship.getName().equals("Ousado") || ship.getName().equals("Veloz"));
            }
        }
    }
}
