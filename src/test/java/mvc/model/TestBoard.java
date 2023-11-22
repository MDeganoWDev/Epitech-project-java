package model;

import main.java.mvc.model.Board;
import main.java.mvc.model.Ship.Ship;
import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestBoard {

    private Board board;

    @Before
    public void setUp() {
        board = new Board(10);
    }

    @Test
    public void testPlaceShip() {
        Ship ship = new Ship1("Test Ship");
        assertTrue(board.placeShip(0, 0, ship.getLength(), true));
        assertEquals(Board.Status.SHIP, board.getCellStatus(0, 0));
        assertEquals(Board.Status.EMPTY, board.getCellStatus(0, 1));
    }

    @Test
    public void testPlaceAllShips() {
        Ship ship1 = new Ship1("Ship 1");
        Ship ship2 = new Ship2("Ship 2");
        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);
        board.placeAllShips(ships);

        for (Ship ship : ships) {
            int bowRow = ship.getBowRow();
            int bowColumn = ship.getBowColumn();
            boolean isHorizontal = ship.isHorizontal();

            for (int i = 0; i < ship.getLength(); i++) {
                if (isHorizontal) {
                    assertEquals(Board.Status.SHIP, board.getCellStatus(bowRow, bowColumn + i));
                } else {
                    assertEquals(Board.Status.SHIP, board.getCellStatus(bowRow + i, bowColumn));
                }
            }
        }
    }

    @Test
    public void testTakeShot() {
        Ship2 ship2 = new Ship2("Test Ship");
        board.placeShip(1, 1, ship2.getLength(), true);

        assertTrue(board.takeShot(1, 1));
        assertEquals(Board.Status.SHIP, board.getCellStatus(1, 1));

        assertFalse(board.takeShot(1, 3));
        assertEquals(Board.Status.MISS, board.getCellStatus(1, 3));

        assertTrue(board.takeShot(1, 2));
        assertEquals(Board.Status.SHIP, board.getCellStatus(1, 2));

        assertFalse(ship2.isSunk());
    }


    @Test
    public void testAreAllShipsSunk() {
        Ship ship1 = new Ship1("Ship 1");
        Ship ship2 = new Ship2("Ship 2");

        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);

        board.placeAllShips(ships);

        assertFalse(board.areAllShipsSunk());

        for (Ship ship : ships) {
            for (int i = 0; i < ship.getLength(); i++) {
                ship.shootAt(ship.getBowRow(), ship.getBowColumn() + i);
            }
        }

        assertTrue(board.areAllShipsSunk());
    }

    @Test
    public void testResetBoard() {
        Ship ship = new Ship1("Test Ship");
        board.placeShip(0, 0, ship.getLength(), true);

        board.resetBoard();

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                assertEquals(Board.Status.EMPTY, board.getCellStatus(row, col));
            }
        }
        assertTrue(board.ships.isEmpty());
    }

}
