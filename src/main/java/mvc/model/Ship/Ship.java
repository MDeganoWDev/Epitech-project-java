package main.java.mvc.model.Ship;

public class Ship {
    private final String name;
    private int bowRow;
    private int bowColumn;
    private final int length;
    private boolean horizontal;
    private boolean[] hit;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.hit = new boolean[length];
        this.horizontal = false;
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
    public String getName() {
        return this.name;
    }
    public int getLength() {
        return this.length;
    }
    public int getBowRow() {
        return this.bowRow;
    }
    public int getBowColumn() {
        return this.bowColumn;
    }
    public boolean isHorizontal() {
        return this.horizontal;
    }
    public boolean[] getHit() {
        return this.hit;
    }
    public void setBowRow(int row) {
        this.bowRow = row;
    }
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
    public void setHit(boolean[] hit) {
        this.hit = hit;
    }
    public String toString() {
        return "Size: " + getLength() + " | " + getName();
    }
}
