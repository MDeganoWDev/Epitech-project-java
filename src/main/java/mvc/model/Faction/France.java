package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;
import main.java.mvc.model.Ship.Ship4;

public class France extends Faction{
    
    /**
     * Constructor for France.
     * Adds the ships of the faction to the list of ships.
     * The ships are added in the order of size, from largest to smallest.
     * The names of the ships are based on the names of the ships of the French Navy.
     */
    public France() {
        super("France");
        super.addShip(new Ship4("La Gloire"));
        super.addShip(new Ship3("L'Espérance"));
        super.addShip(new Ship3("Le Triomphant"));
        super.addShip(new Ship2("Le Brave"));
        super.addShip(new Ship1("Le Fidèle"));
        super.addShip(new Ship1("L'Agile"));
        super.addShip(new Ship1("Le Vigilant"));
    }
}
