package main.java.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public enum Status {
        EMPTY, SHIP, HIT, MISS
    }
    private int rows;
    private int columns;
    private List<Ship> ships = new ArrayList<>();
    private Status[][] grid;
    public Board(int size) {
        if (size < 10) {
            size = 10;
        } else if (size > 20) {
            size = 20;
        }
        this.rows = size;
        this.columns = size;
        this.grid = new Status[rows][columns];

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                grid[row][col] = Status.EMPTY;
            }
        }
    }
    public int getRows() {
        return this.rows;
    }
    public int getColumns() {
        return this.columns;
    }
    public boolean placeShip(int bowRow, int bowColumn, int length, boolean horizontal) {
        if(horizontal) {
            if(bowColumn + length > this.columns) return false;
        } else {
            if(bowRow + length > this.rows) return false;
        }

        for (int i = 0; i < length; i++) {
            int currentRow = horizontal ? bowRow : bowRow + i;
            int currentColumn = horizontal ? bowColumn + i : bowColumn;

            if(grid[currentRow][currentColumn] != Status.EMPTY) {
                return false;
            }
        }

        for (int i = 0; i < length; i++) {
            int currentRow = horizontal ? bowRow : bowRow + i;
            int currentColumn = horizontal ? bowColumn + i : bowColumn;

            grid[currentRow][currentColumn] = Status.SHIP;
        }
        return true;
    }
    public boolean takeShot(int row, int col) {
        Status status = grid[row][col];

        if (status == Status.SHIP) {
            for (Ship ship : ships) {
                if (ship.shootAt(row, col)) {
                    grid[row][col] = Status.HIT;
                    break;
                }
            }
            return true;
        } else if (status == Status.EMPTY) {
            grid[row][col] = Status.MISS;
            return false;
        }
        return false;
    }
    public Status getCellStatus(int row, int col) {
        return grid[row][col];
    }
    public boolean areAllShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }
    public void resetBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = Status.EMPTY;
            }
        }
        ships.clear();
    }
}

