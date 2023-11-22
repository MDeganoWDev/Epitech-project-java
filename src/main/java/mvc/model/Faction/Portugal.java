package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship3;
import main.java.mvc.model.Ship.Ship4;

public class Portugal extends Faction{

    /**
     * Constructor for Portugal.
     * Adds the ships of the faction to the list of ships.
     * The ships are added in the order of size, from largest to smallest.
     * The names of the ships are based on the names of the ships of the Portuguese Navy.
     */
    public Portugal() {
        super("Portugal");
        super.addShip(new Ship4("São Gabriel"));
        super.addShip(new Ship3("São Rafael"));
        super.addShip(new Ship3("Santa Maria da Graça"));
        super.addShip(new Ship3("Esperança"));
        super.addShip(new Ship1("Ousado"));
        super.addShip(new Ship1("Veloz"));
    }
}
