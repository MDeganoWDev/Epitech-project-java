package main.java.mvc.model;

import main.java.mvc.model.Faction.Faction;

public class Player {
    private final String name;
    private final Faction faction;
    private final Board ownBoard;
    private final Board trackingBoard;
    private boolean isTurn;
    private boolean isAI = false;

    /**
     * Constructor for Player.
     * @param name The name of the player.
     * @param faction The faction of the player.
     * @param boardSize The size of the board.
     */
    public Player(String name, Faction faction, int boardSize) {
        this.name = name;
        this.faction = faction;
        this.ownBoard = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);
    }

    /**
     * Getter for name.
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for faction.
     * @return The faction of the player.
     */
    public Faction getFaction() {
        return this.faction;
    }

    /**
     * Getter for ownBoard.
     * @return The board of the player.
     */
    public Board getOwnBoard() {
        return this.ownBoard;
    }

    /**
     * Getter for trackingBoard.
     * @return The tracking board of the player.
     */
    public Board getTrackingBoard() {
        return this.trackingBoard;
    }

    /**
     * Getter for isTurn.
     * @return Whether it is the player's turn.
     */
    public boolean isTurn() {
        return this.isTurn;
    }

    /**
     * Setter for isTurn.
     * @param turn Whether it is the player's turn.
     */
    public void setTurn(boolean turn) {
        this.isTurn = turn;
    }

    /**
     * Getter for isAI.
     * @return Whether the player is an AI.
     */
    public boolean isAI() {
        return this.isAI;
    }

    /**
     * Setter for isAI.
     * @param AI Whether the player is an AI.
     */
    public void setAI(boolean AI) {
        this.isAI = AI;
    }
}