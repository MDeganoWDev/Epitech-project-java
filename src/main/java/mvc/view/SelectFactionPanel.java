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
        this.faction1Selection = new SelectFactionComponent("Select Faction 1");
        this.faction2Selection = new SelectFactionComponent("Select Faction 2");
        this.gridSelection = new SelectGridSizeComponent();
        this.difficultySelection = new SelectDifficultyComponent("Select Difficulty");
        add(faction1Selection);
        add(faction2Selection);
        add(gridSelection);
        add(difficultySelection);
    }
    private void createValidateButton() {
        JButton validateButton = new JButton("Start Game");
        validateButton.addActionListener(e -> {
            Faction faction1 = ((SelectFactionComponent) faction1Selection).getFaction();
            Faction faction2 = ((SelectFactionComponent) faction2Selection).getFaction();
            int gridSize = ((SelectGridSizeComponent) gridSelection).getNumber();

            GameController.selectFaction(faction1, faction2, gridSize);
            GameController.setAiStrategy(((SelectDifficultyComponent) difficultySelection).getDifficulty());
            GameController.shipPlacementView();
        });
        add(validateButton);
    }
}
