package model;

import main.java.mvc.model.Faction.Espagne;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEspagne {

    @Test
    public void testEspagneShips() {
        Espagne espagne = new Espagne();
        assertTrue(espagne.getShips().size() == 6);

        for (Ship ship : espagne.getShips()) {
            assertTrue(ship.getLength() > 0);
            if (ship.getLength() == 4) {
                assertTrue(ship.getName().equals("Santa María") || ship.getName().equals("San Sebastián"));
            } else if (ship.getLength() == 3) {
                assertEquals("El Cazador", ship.getName());
            } else if (ship.getLength() == 2) {
                assertEquals("La Rápida", ship.getName());
            } else if (ship.getLength() == 1) {
                assertTrue(ship.getName().equals("El Pequeño") || ship.getName().equals("El Ágil"));
            }
        }
    }
}
