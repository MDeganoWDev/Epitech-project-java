/*package test.java.mvc.model;

import test.java.mvc.model.*;

import main.java.mvc.model.Ship;

class ShipTest {

    // Test constructor
    void testConstructor() {
        Ship carrier = new Ship("Carrier", 0, 0, 5, true);
        assertEquals("Carrier", carrier.getName());
        assertEquals(0, carrier.getBowRow());
        assertEquals(0, carrier.getBowColumn());
        assertEquals(5, carrier.getLength());
        assertTrue(carrier.isHorizontal());
        assertFalse(carrier.isSunk());
    }

    // Test shootAt method for horizontal ship
    @Test
    void testShootAtHorizontalShip() {
        Ship battleship = new Ship("Battleship", 2, 2, 4, true);
        assertFalse(battleship.shootAt(1, 2)); // Miss
        assertTrue(battleship.shootAt(2, 2)); // Hit at bow
        assertTrue(battleship.shootAt(2, 5)); // Hit at stern
        assertFalse(battleship.isSunk());

        assertTrue(battleship.shootAt(2, 3)); // Hit in middle
        assertTrue(battleship.isSunk());
    }

    // Test shootAt method for vertical ship
    @Test
    void testShootAtVerticalShip() {
        Ship destroyer = new Ship("Destroyer", 4, 4, 3, false);
        assertFalse(destroyer.shootAt(4, 3)); // Miss
        assertTrue(destroyer.shootAt(4, 4)); // Hit at bow
        assertTrue(destroyer.shootAt(7, 4)); // Hit at stern
        assertFalse(destroyer.isSunk());

        assertTrue(destroyer.shootAt(5, 4)); // Hit in middle
        assertTrue(destroyer.isSunk());
    }

    // Test isSunk method
    @Test
    void testIsSunk() {
        Ship submarine = new Ship("Submarine", 6, 6, 2, true);
        assertFalse(submarine.isSunk());
        submarine.shootAt(6, 6); // Hit at bow
        submarine.shootAt(6, 7); // Hit at stern
        assertTrue(submarine.isSunk());
    }
}*/

