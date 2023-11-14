package main.java.mvc.model.AI;

import main.java.mvc.model.Board;
import java.awt.Point;

public interface AiStrategy {
    Point makeMove(Board board);
}


