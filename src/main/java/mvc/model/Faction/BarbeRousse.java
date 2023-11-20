package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;

public class BarbeRousse extends Faction{
    public BarbeRousse() {
        super("Barbe Rousse");
        super.addShip(new Ship3("La Tempête"));
        super.addShip(new Ship3("Le Destructeur"));
        super.addShip(new Ship3("L'Ardent"));
        super.addShip(new Ship2("Le Faucon"));
        super.addShip(new Ship2("L'Insaisissable"));
        super.addShip(new Ship1("Le Féroce"));
        super.addShip(new Ship1("Le Rapace"));
    }
}
