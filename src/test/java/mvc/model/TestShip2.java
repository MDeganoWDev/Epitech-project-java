package model;

import main.java.mvc.model.Ship.Ship2;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestShip2{

    @Test
    public void testConstructor() {
        Ship2 ship2 = new Ship2("Test Ship2");

        // Vérifie si le nom est correct
        assertEquals("Test Ship2", ship2.getName());

        // Vérifie si la longueur est correcte
        assertEquals(2, ship2.getLength());
    }

    @Test
    public void testShootAt() {
        Ship2 ship2 = new Ship2("Test Ship2");

        assertFalse(ship2.shootAt(2, 3)); // Un tir manqué
        assertFalse(ship2.isSunk()); // Le navire n'est pas coulé
    }

    @Test
    public void testIsSunk() {
        Ship2 ship2 = new Ship2("Test Ship2");

        assertFalse(ship2.isSunk()); // Le navire n'est pas coulé
    }
}
