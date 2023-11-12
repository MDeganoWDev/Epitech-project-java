package test.java.mvc.model;

import main.java.mvc.model.Ship;
import org.junit.Test;
import static org.junit.Assert.*;



public class TestShip {

    @Test
    public void testShootAt() {
        Ship ship = new Ship("Test Ship", 3);
        
        ship.setBowRow(2);
        ship.setBowColumn(3);
        ship.setHorizontal(true);
        
        
        assertTrue(ship.shootAt(2, 3)); // Un tir réussi
        assertTrue(ship.shootAt(2, 4)); // Un tir réussi
        assertTrue(ship.shootAt(2, 5)); // Un tir réussi
        assertFalse(ship.shootAt(2, 6)); // Un tir manqué
        assertTrue(ship.isSunk()); // Le navire n'est pas coulé
    }

    @Test
    public void testIsSunk() {
        Ship ship = new Ship("Test Ship", 4);
        
        ship.setBowRow(3);
        ship.setBowColumn(2);
        ship.setHorizontal(false);
        
        assertFalse(ship.isSunk()); // Le navire n'est pas coulé
        ship.shootAt(3, 2);
        ship.shootAt(4, 2);
        ship.shootAt(5, 2);
        ship.shootAt(6, 2);
        assertTrue(ship.isSunk()); // Le navire est coulé
    }
}
