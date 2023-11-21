package main.java.mvc.model.AI;

import main.java.mvc.model.Board;
import java.awt.Point;

/**
 * This interface defines the strategy for an AI component in a battleship game.
 * It declares a method for determining the next move based on the current state
 * of the game board.
 */
public interface AiStrategy {

    /**
     * Determines the next move for the AI player.
     *
     * @param board The current state of the game board.
     * @return The Point object representing the coordinates of the next move.
     */
    Point makeMove(Board board);
}
