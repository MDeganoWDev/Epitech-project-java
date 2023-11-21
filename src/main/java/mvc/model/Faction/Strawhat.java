package main.java.mvc.model.Faction;

import main.java.mvc.model.Ship.Ship1;
import main.java.mvc.model.Ship.Ship2;
import main.java.mvc.model.Ship.Ship3;
import main.java.mvc.model.Ship.Ship4;

public class Strawhat extends Faction {
    public Strawhat(){
        super("Strawhat");
        super.addShip(new Ship1("Franky battle 1"));
        super.addShip(new Ship1("Franky battle 2"));
        super.addShip(new Ship2("Franky battle 4"));
        super.addShip(new Ship2("Franky battle 6"));
        super.addShip(new Ship3("Franky battle 8"));
        super.addShip(new Ship4("Sunny go"));
    }
}