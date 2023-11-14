package main.java.mvc.model.AI;

import main.java.mvc.model.Board;

import java.awt.*;
import java.util.Random;

public class EasyAi implements AiStrategy {
    private Random random = new Random();

    public Point makeMove(Board board) {
        int x, y;
        do {
            x = random.nextInt(board.getRows());
            y = random.nextInt(board.getColumns());
        } while (board.getCellStatus(x, y) != Board.Status.EMPTY && board.getCellStatus(x, y) != Board.Status.SHIP);
        return new Point(x, y);
    }
}
