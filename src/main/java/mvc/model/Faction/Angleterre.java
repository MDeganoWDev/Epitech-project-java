package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;
import main.java.mvc.model.Ship.Ship4;

public class Angleterre extends Faction{
    public Angleterre() {
        super("Angleterre");
        super.addShip(new Ship4("HMS Victory"));
        super.addShip(new Ship3("HMS Endeavour"));
        super.addShip(new Ship2("HMS Defiant"));
        super.addShip(new Ship2("HMS Swift"));
        super.addShip(new Ship1("HMS Brave"));
        super.addShip(new Ship1("HMS Arrow"));
        super.addShip(new Ship1("HMS Dart"));
        super.addShip(new Ship1("HMS Sparrow"));
    }
}
