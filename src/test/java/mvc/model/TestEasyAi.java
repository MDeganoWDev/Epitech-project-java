package model;

import main.java.mvc.model.AI.AiStrategy;
import main.java.mvc.model.AI.EasyAi;
import main.java.mvc.model.Board;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class TestEasyAi {

    @Test
    public void testMakeMove() {
        AiStrategy easyAi = new EasyAi();
        Board board = new Board(20);
        for (int i = 0; i < 100; i++) {
            Point move = easyAi.makeMove(board);
            assertTrue(move.x >= 0 && move.x < board.getRows());
            assertTrue(move.y >= 0 && move.y < board.getColumns());
            assertTrue(board.getCellStatus(move.x, move.y) == Board.Status.EMPTY || board.getCellStatus(move.x, move.y) == Board.Status.SHIP);
        }
    }
}
