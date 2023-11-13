package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Faction;
import main.java.mvc.view.component.SelectFactionComponent;
import main.java.mvc.view.component.SelectGridSizeComponent;

import javax.swing.*;
import java.awt.*;

public class SelectFactionPanel extends JPanel {
    private SelectFactionComponent faction1Selection;
    private SelectFactionComponent faction2Selection;
    private SelectGridSizeComponent gridSelection;
    private JButton startGameButton;

    public SelectFactionPanel() {
        createExistingComponent();
        createValidateButton();
        // Utiliser un GridLayout avec des marges pour disposer les composants
        setLayout(new GridLayout(4, 1, 100, 100));

        // Ajouter les composants à la grille
        add(new JLabel("Select Faction 1"));
        add(faction1Selection);
        add(new JLabel("Select Faction 2"));
        add(faction2Selection);

        // Ajouter le panneau de sélection de la taille de la grille
        add(new JLabel("Select the grid size"));
        add(gridSelection);

        // Ajouter le bouton "Start Game"
        add(startGameButton);
    }

    private void createExistingComponent() {
        faction1Selection = new SelectFactionComponent("");
        faction2Selection = new SelectFactionComponent("");
        gridSelection = new SelectGridSizeComponent();
    }

    private void createValidateButton() {
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            Faction faction1 = faction1Selection.getFaction();
            Faction faction2 = faction2Selection.getFaction();
            int gridSize = gridSelection.getNumber();
            GameController.selectFaction(faction1, faction2, gridSize);
        });
    }
}

