package model;
import main.java.mvc.model.AI.MediumAi;
import main.java.mvc.model.Board;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TestMediumAi {

    @Test
    public void testMakeMove() {
        Board board = new Board(10);
        MediumAi mediumAi = new MediumAi();
        Point move = mediumAi.makeMove(board);
        assertNotNull(move);
        assertTrue(move.x >= 0 && move.x < board.getRows());
        assertTrue(move.y >= 0 && move.y < board.getColumns());
    }
}
