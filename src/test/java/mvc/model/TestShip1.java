package model;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.mvc.model.Ship.Ship1;

public class TestShip1 {

    @Test
    public void testConstructor() {
        Ship1 ship1 = new Ship1("Test Ship1");

        // Vérifie si le nom est correct
        assertEquals("Test Ship1", ship1.getName());

        // Vérifie si la longueur est correcte
        assertEquals(1, ship1.getLength());
    }

    @Test
    public void testShootAt() {
        Ship1 ship1 = new Ship1("Test Ship1");

        assertFalse(ship1.shootAt(2, 3)); // Un tir manqué
        assertFalse(ship1.isSunk()); // Le navire n'est pas coulé
    }

    @Test
    public void testIsSunk() {
        Ship1 ship1 = new Ship1("Test Ship1");

        assertFalse(ship1.isSunk()); // Le navire n'est pas coulé
    }
}
