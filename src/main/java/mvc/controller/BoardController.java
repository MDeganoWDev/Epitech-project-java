package main.java.mvc.controller;

import main.java.mvc.model.Board;
import main.java.mvc.model.Ship;

public class BoardController {
    private Board board;

    public BoardController(int size) {
        this.board = new Board(size);
    }
    public boolean placeShip(int row, int col, int length, boolean horizontal) {
        return board.placeShip(row, col, length, horizontal);
    }
    public boolean takeShot(int row, int col) {
        return board.takeShot(row, col);
    }
    public Board.Status getCellStatus(int row, int col) {
        return board.getCellStatus(row, col);
    }
    public boolean areAllShipsSunk() {
        return board.areAllShipsSunk();
    }
}
