package main.java.mvc.model;

import java.util.Arrays;

public class Ship {
    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;

    public Ship(int bowRow, int bowColumn, int length, boolean horizontal) {
        this.bowRow = bowRow;
        this.bowColumn = bowColumn;
        this.length = length;
        this.horizontal = horizontal;
        this.hit = new boolean[length];
    }
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
    public boolean isSunk() {
        for (boolean touch : hit) {
            if (!touch) return false;
        }
        return true;
    }
}
