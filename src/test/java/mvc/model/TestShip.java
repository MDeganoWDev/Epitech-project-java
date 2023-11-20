package model;

import main.java.mvc.model.Ship.Ship;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestShip {
    private Ship ship;

    @Before
    public void setUp() {
        ship = new Ship("TestShip", 3);
    }

    @Test
    public void testShipName() {
        assertEquals("TestShip", ship.getName());
    }

    @Test
    public void testShipLength() {
        assertEquals(3, ship.getLength());
    }

    @Test
    public void testShipBowRow() {
        ship.setBowRow(2);
        assertEquals(2, ship.getBowRow());
    }

    @Test
    public void testShipBowColumn() {
        ship.setBowColumn(4);
        assertEquals(4, ship.getBowColumn());
    }

    @Test
    public void testShipHorizontal() {
        assertFalse(ship.isHorizontal());
        ship.setHorizontal(true);
        assertTrue(ship.isHorizontal());
    }

    @Test
    public void testShipHit() {
        boolean[] initialHit = ship.getHit();
        for (boolean hit : initialHit) {
            assertFalse(hit);
        }
        boolean[] newHit = {true, false, true};
        ship.setHit(newHit);
        assertArrayEquals(newHit, ship.getHit());
    }

    @Test
    public void testShipSettersAndGetters() {
        ship.setBowRow(2);
        ship.setBowColumn(4);
        ship.setHorizontal(true);
        assertEquals(2, ship.getBowRow());
        assertEquals(4, ship.getBowColumn());
        assertTrue(ship.isHorizontal());
    }

    @Test
    public void testShipToString() {
        assertEquals("Size: 3 | TestShip", ship.toString());
    }

    @Test
    public void testShipShootAt() {
        ship.setHorizontal(true);
        ship.setBowRow(0);
        ship.setBowColumn(0);
        assertTrue(ship.shootAt(0, 0));
        assertTrue(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);
        assertFalse(ship.getHit()[2]);
        assertFalse(ship.shootAt(2, 2));
    }

    @Test
    public void testShipIsSunk() {
        ship.setHorizontal(true);
        ship.setBowRow(0);
        ship.setBowColumn(0);
        assertFalse(ship.isSunk());
        ship.shootAt(0, 0);
        ship.shootAt(0, 1);
        ship.shootAt(0, 2);
        assertTrue(ship.isSunk());
    }
}
