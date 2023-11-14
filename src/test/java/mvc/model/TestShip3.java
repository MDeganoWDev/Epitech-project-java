package model;

import main.java.mvc.model.Ship.Ship3;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestShip3 {

    @Test
    public void testConstructor() {
        Ship3 ship3 = new Ship3("Test Ship3");

        // Vérifie si le nom est correct
        assertEquals("Test Ship3", ship3.getName());

        // Vérifie si la longueur est correcte
        assertEquals(3, ship3.getLength());
    }

    @Test
    public void testShootAt() {
        Ship3 ship3 = new Ship3("Test Ship3");

        assertFalse(ship3.shootAt(2, 3)); // Un tir manqué
        assertFalse(ship3.isSunk()); // Le navire n'est pas coulé
    }

    @Test
    public void testIsSunk() {
        Ship3 ship3 = new Ship3("Test Ship3");

        assertFalse(ship3.isSunk()); // Le navire n'est pas coulé
    }
}
