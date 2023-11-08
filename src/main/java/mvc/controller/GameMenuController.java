package main.java.mvc.controller;
import java.awt.event.*;
import main.java.mvc.view.GameMenuView;

public class GameMenuController {
    private GameMenuView view;

    public GameMenuController(GameMenuView view) {
        this.view = view;

        // Ajoutez des action listeners pour les boutons
        view.addStartGameListener(new StartGameListener());
        view.addOptionsListener(new OptionsListener());
        view.addCreditsListener(new CreditsListener());
    }

    // ActionListener pour le bouton "Start Game"
    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Reagir au clic sur "Start Game"
            // Lancer le jeu principal à partir d'ici
            // Par exemple, créez et affichez une nouvelle vue de jeu
        }
    }

    // ActionListener pour le bouton "Options"
    private class OptionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Afficher une autre vue pour les paramètres du jeu
        }
    }

    // ActionListener pour le bouton "Credits"
    private class CreditsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Afficher des informations sur les crédits du jeu
        }
    }
}
