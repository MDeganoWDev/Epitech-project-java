package main.java.mvc.model.AI;

import main.java.mvc.model.Board;
import main.java.mvc.model.Player;

import java.awt.*;

public abstract class Ai {
    private Player player;
    public Ai(Player player) {
        this.player = player;
    }
    public abstract Point makeMove(Board board);
}
