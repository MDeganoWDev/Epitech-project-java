package main.java.mvc.view;
import main.java.mvc.model.*;

import javax.swing.*;
import java.util.List;
import java.awt.*;


public class GamePanel extends JPanel {
    private Board offensiveBoard1;
    private Board offensiveBoard2;
    private Board defensiveBoard1;
    private Board defensiveBoard2;
    private Player player1;
    private Player player2;
    private List<Ship> shipsPlayer1;
    private List<Ship> shipsPlayer2;

    public GamePanel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.shipsPlayer1 = player1.getFaction().getShips();
        this.shipsPlayer2 = player2.getFaction().getShips();
        this.offensiveBoard1 = player1.getOwnBoard();
        this.offensiveBoard2 = player2.getOwnBoard();
        this.defensiveBoard1.placeAllShips(shipsPlayer1);
        this.defensiveBoard2.placeAllShips(shipsPlayer2);
        this.defensiveBoard1 = player1.getTrackingBoard();
        this.defensiveBoard2 = player2.getTrackingBoard();
        setPreferredSize(new Dimension(1200, 800));
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());
        GridBoard offensiveGrid1 = new GridBoard(offensiveBoard1);
        GridBoard offensiveGrid2 = new GridBoard(offensiveBoard2);
        GridBoard defensiveGrid1 = new GridBoard(defensiveBoard1);
        GridBoard defensiveGrid2 = new GridBoard(defensiveBoard2);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Strawhat strawhat = new Strawhat();
                BigMom bigMom = new BigMom();
                Player player1 = new Player("Player 1", strawhat, 10);
                Player player2 = new Player("Player 2", bigMom, 10);

                MainFrame mainFrame = new MainFrame(player1, player2);
                mainFrame.setVisible(true);
            }
        });
    }
}
