package main.java.mvc.model;

import main.java.mvc.model.Ship.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    public enum Status {
        EMPTY, SHIP, HIT, MISS
    }
    private int rows;
    private int columns;
    public List<Ship> ships = new ArrayList<>();
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
    public void placeAllShips(List<Ship> shipsToFaction) {
        for (Ship ship : shipsToFaction) {
            if (!placeShipRandomly(ship)) {
                return;
            }
        }
    }


    private boolean placeShipRandomly(Ship ship) {

        Random random = new Random();
        boolean placed = false;
        int maxAttempts = 100;

        while (!placed && maxAttempts > 0) {
            int bowRow = random.nextInt(this.rows);
            int bowColumn = random.nextInt(this.columns);
            boolean horizontal = random.nextBoolean();

            placed = placeShip(bowRow, bowColumn, ship.getLength(), horizontal);

            if (placed) {
                ship.setBowRow(bowRow);
                ship.setBowColumn(bowColumn);
                ship.setHorizontal(horizontal);
                ship.setHit(new boolean[ship.getLength()]);
                this.ships.add(ship);
            }
            maxAttempts--;
        }

        return placed;
    }
    public boolean takeShot(int row, int col) {
        Status status = grid[row][col];
    
        if (status == Status.SHIP) {
            for (Ship ship : ships) {
                if (ship.shootAt(row, col)) {
                    updateCellStatus(row, col, Status.HIT);
                    ship.getHit()[ship.isHorizontal() ? col - ship.getBowColumn() : row - ship.getBowRow()] = true;
    
                    if (ship.isSunk()) {
                        // Si le navire est coulé, mettez à jour les cellules du plateau en conséquence
                        for (int i = 0; i < ship.getLength(); i++) {
                            int currentRow = ship.isHorizontal() ? ship.getBowRow() : ship.getBowRow() + i;
                            int currentCol = ship.isHorizontal() ? ship.getBowColumn() + i : ship.getBowColumn();
                            updateCellStatus(currentRow, currentCol, Status.HIT);
                        }
                    }
    
                    return true;
                }
            }
            return true;
        } else if (status == Status.EMPTY) {
            updateCellStatus(row, col, Status.MISS);
            return false;
        }
        return false;
    }
    public Status getCellStatus(int row, int col) {
        return grid[row][col];
    }
    public boolean areAllShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false; // Si un navire n'est pas coulé, retourne false
            }
        }
        return true; // Tous les navires sont coulés
    }
    public void updateCellStatus(int row, int col, Status status) {
        grid[row][col] = status;
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