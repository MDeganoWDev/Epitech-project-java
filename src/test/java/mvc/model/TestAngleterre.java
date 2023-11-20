package model;
import main.java.mvc.model.Faction.Angleterre;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAngleterre {

    @Test
    public void testAngleterreShips() {

        Angleterre angleterre = new Angleterre();
        Faction angleterreFaction = angleterre;
        assertTrue(angleterreFaction.getShips().size() == 8);

        for (Ship ship : angleterreFaction.getShips()) {
            assertTrue(ship.getLength() > 0);
            if (ship.getLength() == 4) {
                assertEquals("HMS Victory", ship.getName());
            } else if (ship.getLength() == 3) {
                assertTrue(ship.getName().equals("HMS Endeavour"));
            } else if (ship.getLength() == 2) {
                assertTrue(ship.getName().equals("HMS Defiant") || ship.getName().equals("HMS Swift"));
            } else if (ship.getLength() == 1) {
                assertTrue(ship.getName().equals("HMS Brave") || ship.getName().equals("HMS Arrow") || ship.getName().equals("HMS Dart") || ship.getName().equals("HMS Sparrow"));
            }
        }
    }
}
