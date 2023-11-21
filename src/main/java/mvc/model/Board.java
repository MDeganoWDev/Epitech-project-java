package main.java.mvc.model;

import main.java.mvc.model.Ship.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    /**
     * Enum for the status of a cell on the board.
     * EMPTY: The cell is empty.
     * SHIP: The cell contains a ship.
     * HIT: The cell contains a ship that has been hit.
     * MISS: The cell does not contain a ship after hit.
     */
    public enum Status {
        EMPTY, SHIP, HIT, MISS
    }
    private final int rows;
    private final int columns;
    public List<Ship> ships = new ArrayList<>();
    private final Status[][] grid;

    /**
     * Constructor for Board.
     * @param size The size of the board.
     * The board will be a square with size x size.
     * The size must be between 10 and 20.
     * If the size is less than 10, the board will be 10 x 10.
     * If the size is greater than 20, the board will be 20 x 20.
     * The default size is 10 x 10.
     * The board will be initialized with all cells empty.
     */
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

    /**
     * Returns the number of rows on the board.
     * @return The number of rows on the board.
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Returns the number of columns on the board.
     * @return The number of columns on the board.
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Place the ship on the board.
     * @param bowRow The row of the bow (front) of the ship.
     * @param bowColumn The column of the bow (front) of the ship.
     * @param length The number of cells occupied by the ship.
     * @param horizontal Whether the ship occupies a single row or column.
     *If true, the ship occupies a single row.
     *If false, the ship occupies a single column.
     *The ship cannot wrap around the board.
     *The ship must not overlap another ship.
     * @return true if the ship was placed successfully, false otherwise.
     */
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

    /**
     * Place all ships randomly on the board.
     * @param shipsToFaction The list of ships to place..
     */
    public void placeAllShips(List<Ship> shipsToFaction) {
        for (Ship ship : shipsToFaction) {
            if (!placeShipRandomly(ship)) {
                return;
            }
        }
        System.out.println("All ships placed randomly");
    }

    /**
     * Place a ship randomly on the board.
     * @param ship The ship to place.
     * @return true if the ship was placed successfully, false otherwise.
     * The ship must not overlap another ship.
     * The ship cannot wrap around the board.
     */
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
        System.out.println("Ship placed randomly");
        return placed;
    }

    /**
     * Take a shot at the specified cell.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return true if the shot hit a ship, false otherwise.
     * If the shot hit a ship, the cell status is updated to HIT.
     * If the shot missed a ship, the cell status is updated to MISS.
     */
    public boolean takeShot(int row, int col) {
        Status status = grid[row][col];
        if (status == Status.SHIP) {
            for (Ship ship : ships) {
                if (ship.shootAt(row, col)) {
                    updateCellStatus(row, col, Status.HIT);
                    ship.getHit()[ship.isHorizontal() ? col - ship.getBowColumn() : row - ship.getBowRow()] = true;
                    System.out.println(ship.getName() + " is hit");
                    if (ship.isSunk()) {
                        for (int i = 0; i < ship.getLength(); i++) {
                            int currentRow = ship.isHorizontal() ? ship.getBowRow() : ship.getBowRow() + i;
                            int currentCol = ship.isHorizontal() ? ship.getBowColumn() + i : ship.getBowColumn();
                            updateCellStatus(currentRow, currentCol, Status.HIT);
                        }
                        System.out.println(ship.getName() + " is sunk");
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

    /**
     * Returns the ship at the specified cell.
     * @param point The coordinates of the cell.
     * @return The ship at the specified cell, null if there is no ship.
     */
    public Ship getShip(Point point){
        for (Ship ship : ships) {
            if (ship.isHorizontal()) {
                if (ship.getBowRow() == point.x && ship.getBowColumn() <= point.y && ship.getBowColumn() + ship.getLength() > point.y) {
                    return ship;
                }
            } else {
                if (ship.getBowColumn() == point.y && ship.getBowRow() <= point.x && ship.getBowRow() + ship.getLength() > point.x) {
                    return ship;
                }
            }
        }
        return null;
    }

    /**
     * Returns the status of the specified cell.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return The status of the specified cell.
     */
    public Status getCellStatus(int row, int col) {
        return grid[row][col];
    }

    /**
     *Checks if all ships on the board are sunk.
     * @return true if all ships on the board are sunk, false otherwise.
     */
    public boolean areAllShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        System.out.println("All ships sunk");
        return true;
    }

    /**
     * Updates the status of the specified cell.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param status The new status of the cell.
     */
    public void updateCellStatus(int row, int col, Status status) {
        grid[row][col] = status;
    }

    /**
     * Resets the board.
     * All cells are set to EMPTY.
     * All ships are removed.
     */
    public void resetBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = Status.EMPTY;
            }
        }
        ships.clear();
        System.out.println("Board reset");
    }
}