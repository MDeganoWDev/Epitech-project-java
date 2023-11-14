package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;

public class BarbeNoire extends Faction{
    public BarbeNoire() {
        super("Barbe Noire");
        super.addShip(new Ship3("Le Requin"));
        super.addShip(new Ship3("Le Fantôme"));
        super.addShip(new Ship2("Le Rapide"));
        super.addShip(new Ship2("Le Vif"));
        super.addShip(new Ship2("Le Fougueux"));
        super.addShip(new Ship1("La Vengeance"));
        super.addShip(new Ship1("Le Mirage"));
        super.addShip(new Ship1("L'Éclair"));
    }
}
