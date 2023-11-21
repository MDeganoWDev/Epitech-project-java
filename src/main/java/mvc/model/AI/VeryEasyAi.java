package main.java.mvc.model.AI;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a very easy difficulty level for the AI in a battleship game.
 * It randomly selects its moves from a list of all available moves on the board.
 */
public class VeryEasyAi implements AiStrategy {
    private final Random random = new Random();
    private final List<Point> availableMoves;

    /**
     * Constructor for VeryEasyAi. Initializes the list of available moves
     * based on the size of the game board.
     */
    public VeryEasyAi() {
        int size = GameController.getBoardSize();
        availableMoves = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                availableMoves.add(new Point(x, y));
            }
        }
    }

    /**
     * Chooses a move for the AI player randomly from the list of available moves.
     * Once a move is chosen, it is removed from the list of available moves.
     *
     * @param board The current state of the game board (not used in this strategy).
     * @return The Point object representing the coordinates of the chosen move.
     * @throws IllegalStateException if there are no more moves available.
     */
    public Point makeMove(Board board) {
        if (availableMoves.isEmpty()) {
            throw new IllegalStateException("No more moves available");
        }
        int index = random.nextInt(availableMoves.size());
        Point move = availableMoves.get(index);
        availableMoves.remove(index);
        return move;
    }
}
