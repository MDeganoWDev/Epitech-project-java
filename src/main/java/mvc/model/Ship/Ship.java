package main.java.mvc.model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Ship
 * Represents a ship in the game.
 * A ship has a name, a length, a position (bowRow, bowColumn), a direction (horizontal or vertical) and a number of hit boxes.
 * A ship can be hit by a shot.
 * A ship can be sunk.
 * A ship can be placed on the board.
 * A ship can be moved on the board.
 * A ship can be rotated on the board.
 * A ship can be removed from the board.
 */
public class Ship {
    private final String name;
    private int bowRow;
    private int bowColumn;
    private final int length;
    private boolean horizontal;
    private boolean[] hit;
    private final Image imageHorizontal;
    private final Image imageVertical;

    /**
     * Constructor for Ship.
     * @param name The name of the ship.
     * @param length The length of the ship.
     */
    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.hit = new boolean[length];
        this.horizontal = false;
        this.imageHorizontal = Toolkit.getDefaultToolkit().getImage("src/main/resources/ship-horizontal.png");
        this.imageVertical = Toolkit.getDefaultToolkit().getImage("src/main/resources/ship-vertical.png");
    }

    /**
     * Shoots at the ship.
     * @param row The row of the shot.
     * @param column The column of the shot.
     * @return true if the ship is hit, false otherwise.
     */
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

    /**
     * Checks if the ship is sunk.
     * @return true if the ship is sunk, false otherwise.
     */
    public boolean isSunk() {
        for (boolean touch : hit) {
            if (!touch) return false;
        }
        return true;
    }

    /**
     * Gets the location of the ship.
     * @return A list of points representing the location of the ship.
     */
    public List<Point> getShipLocation() {
        List<Point> shipLocation = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int currentRow = horizontal ? bowRow : bowRow + i;
            int currentColumn = horizontal ? bowColumn + i : bowColumn;
            shipLocation.add(new Point(currentRow, currentColumn));
        }
        return shipLocation;
    }

    /**
     * Gets the name of the ship.
     * @return the name of the ship.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the length of the ship.
     * @return the length of the ship.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Gets the row of the bow of the ship.
     * @return the row of the bow of the ship.
     */
    public int getBowRow() {
        return this.bowRow;
    }

    /**
     * Gets the column of the bow of the ship.
     * @return the column of the bow of the ship.
     */
    public int getBowColumn() {
        return this.bowColumn;
    }

    /**
     * Gets the direction of the ship.
     * @return true if the ship is horizontal, false otherwise.
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    /**
     * Gets the hit boxes of the ship.
     * @return an array of booleans representing the hit boxes of the ship.
     */
    public boolean[] getHit() {
        return this.hit;
    }

    /**
     * Sets the row of the bow of the ship.
     * @param row The row of the bow of the ship.
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * Sets the column of the bow of the ship.
     * @param column The column of the bow of the ship.
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * Sets the direction of the ship.
     * @param horizontal true if the ship is horizontal, false otherwise.
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Sets the hit boxes of the ship.
     * @param hit An array of booleans representing the hit boxes of the ship.
     */
    public void setHit(boolean[] hit) {
        this.hit = hit;
    }

    /**
     * Gets the string representation of the ship.
     * @return the string representation of the ship.
     */
    public String toString() {
        return "Size: " + getLength() + " | " + getName();
    }

    /**
     * Gets the image of the ship.
     * if the ship is horizontal, returns the horizontal image.
     * if the ship is vertical, returns the vertical image.
     * @return the image of the ship.
     */
    public Image getImage(){
        if(horizontal){
            return imageHorizontal;
        }else{
            return imageVertical;
        }
    }
}
