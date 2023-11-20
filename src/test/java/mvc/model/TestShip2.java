package model;

import main.java.mvc.model.Ship.Ship2;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestShip2{

    @Test
    public void testConstructor() {
        Ship2 ship2 = new Ship2("Test Ship2");
        assertEquals("Test Ship2", ship2.getName());
        assertEquals(2, ship2.getLength());
    }

    @Test
    public void testShootAt() {
        Ship2 ship2 = new Ship2("Test Ship2");
        assertFalse(ship2.shootAt(2, 3));
        assertFalse(ship2.isSunk());
    }

    @Test
    public void testIsSunk() {
        Ship2 ship2 = new Ship2("Test Ship2");
        assertFalse(ship2.isSunk());
    }
}
