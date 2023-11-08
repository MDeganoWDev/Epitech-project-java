package main.java.mvc.model;

import java.util.Arrays;

public class Ship {
    private String name;
    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit; // Array to store hits

    public Ship(String name, int bowRow, int bowColumn, int length, boolean horizontal) {
        this.name = name;
        this.bowRow = bowRow;
        this.bowColumn = bowColumn;
        this.length = length;
        this.horizontal = horizontal;
        this.hit = new boolean[length]; // All false initially
    }

    // Registers a shot at the ship and returns whether it was a hit
    public boolean shootAt(int row, int column) {
        if(horizontal) {
            if(row == this.bowRow && column >= this.bowColumn && column < this.bowColumn + length) {
                hit[column - this.bowColumn] = true;
                return true;
            }
        } else {
            if(column == this.bowColumn && row >= this.bowRow && row < this.bowRow + length) {
                hit[row - this.bowRow] = true;
                return true;
            }
        }
        return false;
    }

    // Check if the ship is sunk
    public boolean isSunk() {
        for (boolean touch : hit) {
            if (!touch) return false; // If any part is not hit, the ship is not sunk
        }
        return true; // All parts are hit, the ship is sunk
    }

    // Getters and setters omitted for brevity...
}
