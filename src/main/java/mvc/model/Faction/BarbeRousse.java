package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;

public class BarbeRousse extends Faction{

    /**
     * Constructor for BarbeRousse.
     * Adds the ships of the faction to the list of ships.
     * The ships are added in the order of size, from largest to smallest.
     * The names of the ships are based on the names of pirates ships.
     */
    public BarbeRousse() {
        super("Barbe Rousse");
        super.addShip(new Ship3("La Tempête"));
        super.addShip(new Ship3("Le Destructeur "));
        super.addShip(new Ship3("L'Ardent"));
        super.addShip(new Ship2("Le Faucon"));
        super.addShip(new Ship2("L'Insaisissable"));
        super.addShip(new Ship1("Le Féroce"));
        super.addShip(new Ship1("Le Rapace "));
    }
}
