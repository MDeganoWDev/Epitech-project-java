package main.java.mvc.model.AI;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;
import main.java.mvc.model.Ship.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a very hard difficulty AI for a battleship game.
 * This AI strategy is a balance between random moves and targeting potential ship locations
 * after a successful hit.
 * It also switches to known ship locations after 5 turns.
 * it also directly hit ship after touching them.
 */
public class VeryHardAi implements AiStrategy{
    private final Random random = new Random();
    private final Board boardPlayer1;
    private Ship shipTouched;
    private  boolean isHit = false;
    private boolean init = false;
    private final List<Point> availableMoves;
    private final List<Point> potentialHits;
    private final List<Point> knowLocations;
    private int turn = 1;

    /**
     * Constructor for HardAi. Initializes the list of available moves and potential hits
     * based on the size of the game board.
     */
    public VeryHardAi() {
        this.boardPlayer1 = GameController.player1.getOwnBoard();
        int size = GameController.getBoardSize();
        availableMoves = new ArrayList<>();
        potentialHits = new ArrayList<>();
        knowLocations = new ArrayList<>();
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
        if (!init) setKnowLocations();
        if (availableMoves.isEmpty()) {
            throw new IllegalStateException("No more moves available");
        }
        Point move;
        int index;
        isShipSunk();
        if (turn > 5 && !isHit) {
            move = switchToKnowLocations();
        } else if (isHit) {
            index = random.nextInt(potentialHits.size());
            move = potentialHits.get(index);
            potentialHits.remove(index);
            availableMoves.remove(move);
            knowLocations.remove(move);
        } else {
            index = random.nextInt(availableMoves.size());
            move = availableMoves.get(index);
            availableMoves.remove(index);
            knowLocations.remove(move);
            turn++;
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
        Ship ship = boardPlayer1.getShip(initialHit);
        ship.getShipLocation().forEach(point -> {
            if (boardPlayer1.getCellStatus(point.x, point.y) == Board.Status.SHIP) {
                potentialHits.add(point);
                potentialHits.remove(initialHit);
                System.out.println("Added potential hit: " + point);
            }
        });
    }

    /**
     * Checks the status of a cell and updates the AI's state accordingly.
     *
     * @param cell The coordinates of the cell to check.
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

    /**
     * Checks if the last hit ship is sunk and updates the state of the AI.
     */
    private void setKnowLocations(){
        for (int i = 0; i < GameController.getBoardSize(); i++) {
            for (int j = 0; j < GameController.getBoardSize(); j++) {
                if (boardPlayer1.getCellStatus(i, j) == Board.Status.SHIP) {
                    knowLocations.add(new Point(i, j));
                }
            }
        }
        init = true;
    }

    /**
     * Switches to known locations after 5 turns.
     *
     * @return The Point object representing the coordinates of the chosen move.
     */
    public Point switchToKnowLocations(){
        int index = random.nextInt(knowLocations.size());
        Point move = knowLocations.get(index);
        knowLocations.remove(index);
        availableMoves.remove(move);
        turn = 1;
        return move;
    }
}
