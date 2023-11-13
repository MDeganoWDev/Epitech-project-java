package test.java.mvc.model;

import main.java.mvc.model.Faction;
import main.java.mvc.model.Player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPlayer {

    @Test
    public void testPlayerCreation() {
        // Création d'une faction pour le test
        Faction faction = new Faction("Test Faction");

        // Création d'un joueur
        Player player = new Player("Test Player", faction);

        // Vérification des attributs du joueur
        assertEquals("Test Player", player.getName());
        assertEquals(faction, player.getFaction());
    }

    @Test
    public void testSetName() {
        // Création d'une faction pour le test
        Faction faction = new Faction("Test Faction");

        // Création d'un joueur
        Player player = new Player("Test Player", faction);

        // Modification du nom du joueur
        player.setName("New Name");

        // Vérification du nouveau nom
        assertEquals("New Name", player.getName());
    }

    @Test
    public void testSetFaction() {
        // Création de deux factions pour le test
        Faction faction1 = new Faction("Test Faction 1");
        Faction faction2 = new Faction("Test Faction 2");

        // Création d'un joueur avec la première faction
        Player player = new Player("Test Player", faction1);

        // Changement de faction pour la deuxième faction
        player.setFaction(faction2);

        // Vérification de la nouvelle faction
        assertEquals(faction2, player.getFaction());
    }
}

