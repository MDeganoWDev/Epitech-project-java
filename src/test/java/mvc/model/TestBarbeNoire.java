package model;
import main.java.mvc.model.Faction.BarbeNoire;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Ship.Ship;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
public class TestBarbeNoire {
    @Test
    public void testBarbeNoireShips() {
        BarbeNoire barbeNoire = new BarbeNoire();
        Faction barbeNoireFaction = barbeNoire;
        assertTrue(barbeNoireFaction.getShips().size() == 8);
        for (Ship ship : barbeNoireFaction.getShips()) {
            assertTrue(ship.getLength() > 0);
            if (ship.getLength() == 3) {
                assertTrue(ship.getName().equals("Le Requin") || ship.getName().equals("Le Fantôme"));
            } else if (ship.getLength() == 2) {
                assertTrue(ship.getName().equals("Le Rapide") || ship.getName().equals("Le Vif") || ship.getName().equals("Le Fougueux"));
            } else if (ship.getLength() == 1) {
                assertTrue(ship.getName().equals("La Vengeance") || ship.getName().equals("Le Mirage") || ship.getName().equals("L'Éclair"));
            }
        }
    }
}
