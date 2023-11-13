package main.java.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    private String name;
    private List<Ship> ships;
    public Faction(String name) {
        this.ships = new ArrayList<>();
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
    public void setName(String name) {
        this.name = name;
    }
    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
