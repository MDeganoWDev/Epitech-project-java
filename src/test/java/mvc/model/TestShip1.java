package model;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.mvc.model.Ship.Ship1;

public class TestShip1 {

    @Test
    public void testConstructor() {
        Ship1 ship1 = new Ship1("Test Ship1");
        assertEquals("Test Ship1", ship1.getName());
        assertEquals(1, ship1.getLength());
    }

    @Test
    public void testShootAt() {
        Ship1 ship1 = new Ship1("Test Ship1");
        assertFalse(ship1.shootAt(2, 3));
        assertFalse(ship1.isSunk());
    }

    @Test
    public void testIsSunk() {
        Ship1 ship1 = new Ship1("Test Ship1");
        assertFalse(ship1.isSunk());
    }
}
