package test.java.mvc.model;

import main.java.mvc.model.Board;
import main.java.mvc.model.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestBoard {
    private Board board;

    // Configuration avant chaque test
    @Before
    public void setUp() {
        board = new Board(10); // Crée un nouveau plateau de jeu avec une taille de 10x10
    }

    // Teste le placement d'un navire sur le plateau
    @Test
    public void testPlaceShip() {
        Ship ship = new Ship("Test Ship", 3);
        assertTrue(board.placeShip(2, 3, 3, true)); // Place un navire de taille 3 horizontalement à la position (2, 3)

        // Vérifie si les cellules occupées par le navire ont le statut correct
        assertEquals(Board.Status.SHIP, board.getCellStatus(2, 3));
        assertEquals(Board.Status.SHIP, board.getCellStatus(2, 4));
        assertEquals(Board.Status.SHIP, board.getCellStatus(2, 5));

        // Tente de placer un autre navire, devrait renvoyer false car les cellules sont déjà occupées
        assertFalse(board.placeShip(2, 3, 3, true));
    }

    // Teste le placement de plusieurs navires sur le plateau
    @Test
    public void testPlaceAllShips() {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship("Ship 1", 3));
        ships.add(new Ship("Ship 2", 4));

        assertTrue(board.placeAllShips(ships)); // Place tous les navires sur le plateau

        assertEquals(Board.Status.SHIP, board.getCellStatus(ships.get(0).getBowRow(), ships.get(0).getBowColumn()));
        assertEquals(Board.Status.SHIP, board.getCellStatus(ships.get(1).getBowRow(), ships.get(1).getBowColumn()));
    }

    @Test
    public void testTakeShot() {
        Ship ship = new Ship("Test Ship", 2);
        assertTrue(board.placeShip(2, 3, 2, true)); // Place un navire de taille 2 horizontalement à la position (2, 3)
    
        assertFalse(board.takeShot(1, 2)); // Tire sur une cellule vide, devrait renvoyer false
        assertEquals(Board.Status.MISS, board.getCellStatus(1, 2)); // La cellule devrait être marquée comme ratée (MISS)
    
        assertTrue(board.takeShot(2, 3)); // Tire sur une cellule occupée par un navire, devrait renvoyer true
        assertFalse(ship.isSunk()); // Le navire n'est pas coulé
    
        assertTrue(board.takeShot(2, 4)); // Tire sur la dernière cellule occupée par le même navire, devrait renvoyer true
        assertTrue(board.areAllShipsSunk()); // Le navire est coulé après les tirs réussis
    }
    
    

    // Teste la réinitialisation du plateau
    @Test
    public void testResetBoard() {
        Ship ship = new Ship("Test Ship", 3);
        board.placeShip(2, 3, 3, true); // Place un navire sur le plateau

        board.resetBoard(); // Réinitialise le plateau

        // Vérifie que toutes les cellules sont maintenant vides
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                assertEquals(Board.Status.EMPTY, board.getCellStatus(row, col));
            }
        }

        assertTrue(board.ships.isEmpty()); // Vérifie que la liste des navires est vide après la réinitialisation
    }

    @Test
    public void testPlaceShipRandomly() {
        Ship ship = new Ship("Test Ship", 3);
        boolean placed = board.placeShipRandomly(ship);

        assertTrue(placed); // Le navire devrait être placé avec succès

        // Vérifie que le navire est bien sur le plateau
        assertEquals(Board.Status.SHIP, board.getCellStatus(ship.getBowRow(), ship.getBowColumn()));
        assertEquals(Board.Status.SHIP, board.getCellStatus(ship.getBowRow(), ship.getBowColumn() + 1));
        assertEquals(Board.Status.SHIP, board.getCellStatus(ship.getBowRow(), ship.getBowColumn() + 2));

        // Vérifie que le navire est ajouté à la liste des navires du plateau
        assertTrue(board.ships.contains(ship));
    }
}
