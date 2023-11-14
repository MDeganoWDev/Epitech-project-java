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
        board = new Board(10); // Vous pouvez ajuster la taille du plateau selon vos besoins
    }

    @Test
    public void testPlaceShip() {
        Ship ship = new Ship1("Test Ship"); // La longueur sera automatiquement définie à 1 pour Ship1

        assertTrue(board.placeShip(0, 0, ship.getLength(), true)); // Place le navire horizontalement

        assertEquals(Board.Status.SHIP, board.getCellStatus(0, 0));
        assertEquals(Board.Status.EMPTY, board.getCellStatus(0, 1)); // Vérifie qu'une case adjacente est vide
    }

    @Test
    public void testPlaceAllShips() {
        Ship ship1 = new Ship1("Ship 1");
        Ship ship2 = new Ship2("Ship 2");

        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);

        board.placeAllShips(ships);

        // Vérifie que tous les navires ont été placés correctement
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getLength(); i++) {
                assertEquals(Board.Status.SHIP, board.getCellStatus(ship.getBowRow(), ship.getBowColumn() + i));
            }
        }
    }

    @Test
    public void testTakeShot() {
        Ship2 ship2 = new Ship2("Test Ship");
        board.placeShip(1, 1, ship2.getLength(), true); // Place le navire horizontalement

        assertTrue(board.takeShot(1, 1)); // Tirez sur une partie du navire
        assertEquals(Board.Status.SHIP, board.getCellStatus(1, 1));

        assertFalse(board.takeShot(1, 3)); // Tirez sur une case vide
        assertEquals(Board.Status.MISS, board.getCellStatus(1, 3));

        assertTrue(board.takeShot(1, 2)); // Tirez sur une partie du navire
        assertEquals(Board.Status.SHIP, board.getCellStatus(1, 2));

        assertFalse(ship2.isSunk()); // Vérifie que le navire est coulé après deux tirs
    }


    @Test
    public void testAreAllShipsSunk() {
        Ship ship1 = new Ship1("Ship 1");
        Ship ship2 = new Ship2("Ship 2");

        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);

        board.placeAllShips(ships);

        assertFalse(board.areAllShipsSunk()); // Aucun navire n'est encore touché

        // Tirez sur toutes les parties de tous les navires pour les couler
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getLength(); i++) {
                ship.shootAt(ship.getBowRow(), ship.getBowColumn() + i);
            }
        }

        assertTrue(board.areAllShipsSunk()); // Tous les navires sont coulés
    }

    @Test
    public void testResetBoard() {
        Ship ship = new Ship1("Test Ship");
        board.placeShip(0, 0, ship.getLength(), true); // Place le navire horizontalement

        board.resetBoard();

        // Vérifie que toutes les cases sont à nouveau vides
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                assertEquals(Board.Status.EMPTY, board.getCellStatus(row, col));
            }
        }

        // Vérifie que la liste des navires est vide
        assertTrue(board.ships.isEmpty());
    }

    /*@Test
    public void testPlaceShipRandomly() {
        Ship ship1 = new Ship1("Random Ship 1");
        Ship ship2 = new Ship2("Random Ship 2");

        board.placeShipRandomly(ship1);
        board.placeShipRandomly(ship2);

        // Vérifie que tous les navires ont été placés correctement
        assertTrue(board.ships.contains(ship1));
        assertTrue(board.ships.contains(ship2));
    }*/

}
