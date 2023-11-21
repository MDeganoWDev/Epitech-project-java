package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a faction in the game.
 */
public class Faction {
    private final String name;
    private final List<Ship> ships;

    /**
     * Constructor for Faction.
     *
     * @param name The name of the faction.
     */
    public Faction(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
    }

    /**
     * Adds a ship to the faction.
     *
     * @param ship The ship to be added.
     */
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    /**
     * Returns the name of the faction.
     *
     * @return The name of the faction.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the list of ships in the faction.
     *
     * @return The list of ships in the faction.
     */
    public List<Ship> getShips() {
        return this.ships;
    }
}
