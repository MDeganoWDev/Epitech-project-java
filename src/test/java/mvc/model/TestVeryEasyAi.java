package model;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.AI.VeryEasyAi;
import main.java.mvc.model.Board;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
public class TestVeryEasyAi {

    private VeryEasyAi veryEasyAi;
    private Board board;

    @Before
    public void setUp() {
        veryEasyAi = new VeryEasyAi();
        board = new Board(10);
    }
    @Test
    public void testMakeMove() {
        int boardSize = GameController.getBoardSize();
        Set<Point> possibleMoves = new HashSet<>();
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                possibleMoves.add(new Point(x, y));
            }
        }
        for (int i = 0; i < boardSize * boardSize; i++) {
            Point move = veryEasyAi.makeMove(board);
            assertTrue(possibleMoves.contains(move));
            possibleMoves.remove(move);
        }

        assertTrue(possibleMoves.isEmpty());
    }
}
