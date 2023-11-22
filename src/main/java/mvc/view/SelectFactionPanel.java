package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.view.component.SelectDifficultyComponent;
import main.java.mvc.view.component.SelectFactionComponent;
import main.java.mvc.view.component.SelectGridSizeComponent;

import javax.swing.*;
import java.awt.*;

public class SelectFactionPanel extends JPanel {
    private JPanel faction1Selection;
    private JPanel faction2Selection;
    private JPanel gridSelection;
    private JPanel difficultySelection;
    private Image backgroundImage;

    public SelectFactionPanel() {
        createExistingComponent();
        createValidateButton();
        try {
            backgroundImage = new ImageIcon("src/main/resources/SelectFaction.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (this.getWidth() - backgroundImage.getWidth(null)) / 2;
            int y = (this.getHeight() - backgroundImage.getHeight(null)) / 2;
            g.drawImage(backgroundImage, x, y, this);
        }
    }

    private void createExistingComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Grid Selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(100, 0, 0, 0);
        this.gridSelection = new SelectGridSizeComponent();
        customizeComponent(this.gridSelection);
        add(gridSelection, gbc);

        // Difficulty Selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(100, 0, 120, 0); // Ajout d'un espace en haut (ajusté ici)
        this.difficultySelection = new SelectDifficultyComponent("Select Difficulty");
        customizeComponent(this.difficultySelection);
        add(difficultySelection, gbc);

        // Faction 1
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 200, 200); // Ajout d'un espace à droite
        gbc.anchor = GridBagConstraints.LINE_END;
        this.faction1Selection = new SelectFactionComponent("Select Faction 1");
        customizeComponent(this.faction1Selection);
        add(faction1Selection, gbc);

        // Faction 2
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 200, 200, 0); // Ajout d'un espace à gauche
        gbc.anchor = GridBagConstraints.LINE_START;
        this.faction2Selection = new SelectFactionComponent("Select Faction 2");
        customizeComponent(this.faction2Selection);
        add(faction2Selection, gbc);
    }

    private void createValidateButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        JButton validateButton = new JButton("Start Game");

        validateButton.setBackground(new Color(52, 152, 219)); // Couleur de fond
        validateButton.setForeground(Color.white); // Couleur du texte
        validateButton.setFont(new Font("Arial", Font.BOLD, 14)); // Police

        validateButton.addActionListener(e -> {
            Faction faction1 = ((SelectFactionComponent) faction1Selection).getFaction();
            Faction faction2 = ((SelectFactionComponent) faction2Selection).getFaction();
            int gridSize = ((SelectGridSizeComponent) gridSelection).getNumber();

            GameController.selectFaction(faction1, faction2, gridSize);
            GameController.setAiStrategy(((SelectDifficultyComponent) difficultySelection).getDifficulty());
            GameController.shipPlacementView();
        });

        // Start Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(40, 0, 0, 0); // Ajout d'un espace en haut
        gbc.anchor = GridBagConstraints.CENTER;
        add(validateButton, gbc);
    }

    private void customizeComponent(JPanel panel) {
        // Personnalisation générale pour tous les JPanels
        panel.setBackground(new Color(52, 73, 94)); // Couleur de fond
        panel.setForeground(Color.white); // Couleur du texte
        panel.setFont(new Font("Arial", Font.BOLD, 14)); // Police
    }
}
