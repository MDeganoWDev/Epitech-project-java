package model;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.AI.VeryHardAi;
import main.java.mvc.model.Board;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class TestVeryHardAi {

    private VeryHardAi veryHardAi;
    private Board board;

    @Before
    public void setUp() {
        GameController.player1 = new main.java.mvc.model.Player("Player 1", null, 10);
        GameController.player2 = new main.java.mvc.model.Player("Player 2", null, 10);

        veryHardAi = new VeryHardAi();
        board = new Board(10);
    }
    @Test
    public void testMakeMove() {
        try {
            Point move = veryHardAi.makeMove(board);
            assertTrue(move.x >= 0 && move.x < GameController.getBoardSize());
            assertTrue(move.y >= 0 && move.y < GameController.getBoardSize());
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }
}
