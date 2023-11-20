package main.java.mvc.model.AI;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VeryEasyAi implements AiStrategy {
    private final Random random = new Random();
    private final List<Point> availableMoves;

    public VeryEasyAi() {
        int size = GameController.getBoardSize();
        availableMoves = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                availableMoves.add(new Point(x, y));
            }
        }
    }
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
