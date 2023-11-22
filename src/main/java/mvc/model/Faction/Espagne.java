package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;
import main.java.mvc.model.Ship.Ship4;

public class Espagne extends Faction{

    /**
     * Constructor for Espagne.
     * Adds the ships of the faction to the list of ships.
     * The ships are added in the order of size, from largest to smallest.
     * The names of the ships are based on the names of the ships of the Spanish Navy.
     */
    public Espagne() {
        super("Espagne");
        super.addShip(new Ship4("Santa María"));
        super.addShip(new Ship4("San Sebastián"));
        super.addShip(new Ship3("El Cazador"));
        super.addShip(new Ship2("La Rápida"));
        super.addShip(new Ship1("El Pequeño"));
        super.addShip(new Ship1("El Ágil "));
    }
}
