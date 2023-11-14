package main.java.mvc.model;

import main.java.mvc.model.Faction.Faction;

public class Player {
    private final String name;
    private final Faction faction;
    private final Board ownBoard;
    private final Board trackingBoard;
    private boolean isTurn;
    private boolean isAI = false;
    public Player(String name, Faction faction, int boardSize) {
        this.name = name;
        this.faction = faction;
        this.ownBoard = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);
    }
    public String getName() {
        return this.name;
    }
    public Faction getFaction() {
        return this.faction;
    }
    public Board getOwnBoard() {
        return this.ownBoard;
    }

    public Board getTrackingBoard() {
        return this.trackingBoard;
    }
    public boolean isTurn() {
        return this.isTurn;
    }
    public void setTurn(boolean turn) {
        this.isTurn = turn;
    }
    public boolean isAI() {
        return this.isAI;
    }
    public void setAI(boolean AI) {
        this.isAI = AI;
    }
}