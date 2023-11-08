package main.java.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Faction faction;
    public Player(String name, Faction faction) {
        this.name = name;
        this.faction = faction;
    }
    public String getName() {
        return this.name;
    }
    public Faction getFaction() {
        return this.faction;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}