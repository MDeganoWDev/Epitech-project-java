package model;

import main.java.mvc.model.Ship.Ship3;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestShip3 {

    @Test
    public void testConstructor() {
        Ship3 ship3 = new Ship3("Test Ship3");
        assertEquals("Test Ship3", ship3.getName());
        assertEquals(3, ship3.getLength());
    }

    @Test
    public void testShootAt() {
        Ship3 ship3 = new Ship3("Test Ship3");
        assertFalse(ship3.shootAt(2, 3));
        assertFalse(ship3.isSunk());
    }

    @Test
    public void testIsSunk() {
        Ship3 ship3 = new Ship3("Test Ship3");
        assertFalse(ship3.isSunk());
    }
}
