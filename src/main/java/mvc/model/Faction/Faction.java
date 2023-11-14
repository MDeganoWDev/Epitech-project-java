package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    private final String name;
    private final List<Ship> ships;
    public Faction(String name) {

        this.name = name;
        this.ships = new ArrayList<>();

    }
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }
    public String getName() {
        return this.name;
    }
    public List<Ship> getShips() {
        return this.ships;
    }
}
