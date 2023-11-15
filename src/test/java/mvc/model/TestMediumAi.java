package model;
import main.java.mvc.model.AI.MediumAi;
import main.java.mvc.model.Board;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TestMediumAi {

    @Test
    public void testMakeMove() {
        // Créer un plateau de jeu pour le test
        Board board = new Board(10);

        // Créer une instance de MediumAi
        MediumAi mediumAi = new MediumAi();

        // Effectuer un mouvement avec l'IA
        Point move = mediumAi.makeMove(board);

        // Le mouvement ne doit pas être null
        assertNotNull(move);

        // Le mouvement doit être dans les limites du plateau de jeu
        assertTrue(move.x >= 0 && move.x < board.getRows());
        assertTrue(move.y >= 0 && move.y < board.getColumns());
    }
}
