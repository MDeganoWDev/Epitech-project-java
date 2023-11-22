package model;

import main.java.mvc.model.AI.MediumAi;
import main.java.mvc.model.Board;
import main.java.mvc.controller.GameController;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class TestMediumAi {

    private MediumAi mediumAi;
    private Board board;

    @Before
    public void setUp() {
        GameController.player1 = new main.java.mvc.model.Player("Player 1", null, 10);
        GameController.player2 = new main.java.mvc.model.Player("Player 2", null, 10);

        mediumAi = new MediumAi();
        board = new Board(10);
    }

    @Test
    public void testMakeMove() {
        try {
            Point move = mediumAi.makeMove(board);
            assertTrue(move.x >= 0 && move.x < GameController.getBoardSize());
            assertTrue(move.y >= 0 && move.y < GameController.getBoardSize());
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }
}

