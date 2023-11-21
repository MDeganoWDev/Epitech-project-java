package main.java.mvc.model.AI;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;
import main.java.mvc.model.Ship.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a medium difficulty AI for a battleship game.
 * This AI strategy is a balance between random moves and targeting potential ship locations
 * after a successful hit.
 */
public class MediumAi implements AiStrategy{
    private final Random random = new Random();
    private final Board boardPlayer1;
    private Ship shipTouched;
    private  boolean isHit = false;
    private final List<Point> availableMoves;
    private final List<Point> potentialHits;

    /**
     * Constructor for MediumAi. Initializes the list of available moves and potential hits
     * based on the size of the game board.
     */
    public MediumAi() {
        this.boardPlayer1 = GameController.player1.getOwnBoard();
        int size = GameController.getBoardSize();
        availableMoves = new ArrayList<>();
        potentialHits = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                availableMoves.add(new Point(x, y));
            }
        }
    }

    /**
     * Chooses the next move for the AI player. It prioritizes moves around the last successful hit,
     * if available, otherwise makes a random choice.
     *
     * @param board The current state of the game board (not used in this strategy).
     * @return The Point object representing the coordinates of the chosen move.
     * @throws IllegalStateException if no moves are available.
     */
    public Point makeMove(Board board) {
        if (availableMoves.isEmpty()) {
            throw new IllegalStateException("No more moves available");
        }
        Point move;
        int index;
        isShipSunk();
        if (isHit) {
            index = random.nextInt(potentialHits.size());
            move = potentialHits.get(index);
            potentialHits.remove(index);
            availableMoves.remove(move);
        } else {
            index = random.nextInt(availableMoves.size());
            move = availableMoves.get(index);
            availableMoves.remove(index);
        }
        testCell(move);
        return move;
    }

    /**
     * Adds potential hit points around a given hit point, considering only orthogonal directions.
     *
     * @param initialHit The point where the last hit occurred.
     */
    public void addPotentialHits(Point initialHit){
        checkPotentialHit(initialHit.x + 1, initialHit.y);
        checkPotentialHit(initialHit.x - 1, initialHit.y);
        checkPotentialHit(initialHit.x, initialHit.y + 1);
        checkPotentialHit(initialHit.x, initialHit.y - 1);
    }

    /**
     * Checks and adds a point to potential hits if it's within the board and not already hit.
     *
     * @param x The x-coordinate of the point to check.
     * @param y The y-coordinate of the point to check.
     */
    private void checkPotentialHit(int x, int y) {
        Point potentialHit = new Point(x, y);
        if (x >= 0 && y >= 0 && x < GameController.getBoardSize() && y < GameController.getBoardSize() && availableMoves.contains(potentialHit)) {
            potentialHits.add(potentialHit);
        }
    }

    /**
     * Tests a cell for the presence of a ship and updates the state accordingly.
     *
     * @param cell The Point object representing the coordinates of the cell to test.
     */
    public void testCell(Point cell){
        Board.Status status = boardPlayer1.getCellStatus(cell.x, cell.y);
        if (status == Board.Status.SHIP) {
            this.shipTouched = boardPlayer1.getShip(cell);
            this.isHit = true;
            addPotentialHits(cell);
        }
    }

    /**
     * Checks if the last hit ship is sunk and updates the state of the AI.
     */
    public void isShipSunk(){
        if (this.shipTouched != null && this.shipTouched.isSunk()) {
            this.isHit = false;
            this.shipTouched = null;
            this.potentialHits.clear();
        }
    }
}
