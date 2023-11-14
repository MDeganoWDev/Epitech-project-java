package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.view.component.SelectDifficultyComponent;
import main.java.mvc.view.component.SelectFactionComponent;
import main.java.mvc.view.component.SelectGridSizeComponent;

import javax.swing.*;

public class SelectFactionPanel extends JPanel {
    private JPanel faction1Selection;
    private JPanel faction2Selection;
    private JPanel gridSelection;
    private JPanel difficultySelection;


    public SelectFactionPanel() {
        createExistingComponent();
        createValidateButton();
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
