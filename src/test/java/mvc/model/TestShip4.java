package model;

import main.java.mvc.model.Ship.Ship4;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestShip4 {

    @Test
    public void testConstructor() {
        Ship4 ship4 = new Ship4("Test Ship4");

        // Vérifie si le nom est correct
        assertEquals("Test Ship4", ship4.getName());

        // Vérifie si la longueur est correcte
        assertEquals(4, ship4.getLength());
    }

    @Test
    public void testShootAt() {
        Ship4 ship4 = new Ship4("Test Ship4");

        assertFalse(ship4.shootAt(2, 3)); // Un tir manqué
        assertFalse(ship4.isSunk()); // Le navire n'est pas coulé
    }

    @Test
    public void testIsSunk() {
        Ship4 ship4 = new Ship4("Test Ship4");

        assertFalse(ship4.isSunk()); // Le navire n'est pas coulé
    }
}
