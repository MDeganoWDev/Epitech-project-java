package main.java.mvc.model.AI;

import main.java.mvc.model.Player;

public abstract class Ai {
    private Player player;
    public Ai(Player player) {
        this.player = player;
    }
    public abstract void iaAttack();
}
