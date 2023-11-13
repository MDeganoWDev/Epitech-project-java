package main.java.mvc.model.AI;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Player;

public class AiFacile extends Ai{
    private Player player;
    public AiFacile(Player player) {
        super(player);
        this.player = player;
    }
    public void iaAttack() {
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        GameController.attackPhase(player.)
    }
}
