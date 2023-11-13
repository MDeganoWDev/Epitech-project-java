package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;
import main.java.mvc.model.Ship.Ship4;

public class BigMom extends Faction {
    public BigMom(){
        super("Big Mom");
        super.addShip(new Ship1("Big mom child 43"));
        super.addShip(new Ship1("Big mom child 27"));
        super.addShip(new Ship2("Big mom child 49"));
        super.addShip(new Ship2("Big mom child 61"));
        super.addShip(new Ship3("Big mom child 6"));
        super.addShip(new Ship4("Big mom admiral battleShip"));
    }
}
