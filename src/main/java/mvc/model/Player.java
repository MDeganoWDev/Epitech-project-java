package main.java.mvc.model;

import main.java.mvc.model.Faction.Faction;

public class Player {
    private String name;
    private Faction faction;
    private Board ownBoard;
    private Board trackingBoard;
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
    public void setName(String name) {
        this.name = name;
    }
    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}