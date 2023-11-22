package model;

import main.java.mvc.model.Faction.BarbeRousse;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
public class TestBarbeRousse {
    @Test
    public void testBarbeRousseShips() {
        BarbeRousse barbeRousse = new BarbeRousse();
        assertTrue(barbeRousse.getShips().size() == 7);
        for (Ship ship : barbeRousse.getShips()) {
            assertTrue(ship.getLength() > 0);
            if (ship.getLength() == 3) {
                assertTrue(ship.getName().equals("La Tempête") || ship.getName().equals("Le Destructeur") || ship.getName().equals("L'Ardent"));
            } else if (ship.getLength() == 2) {
                assertTrue(ship.getName().equals("Le Faucon") || ship.getName().equals("L'Insaisissable"));
            } else if (ship.getLength() == 1) {
                assertTrue(ship.getName().equals("Le Féroce") || ship.getName().equals("Le Rapace"));
            }
        }
    }
}
