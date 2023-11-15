package main.java.mvc.view.component;

import main.java.mvc.model.AI.AiStrategy;
import main.java.mvc.model.AI.EasyAi;
import main.java.mvc.model.AI.HardAi;
import main.java.mvc.model.AI.MediumAi;

import javax.swing.*;
import java.awt.*;

public class SelectDifficultyComponent extends JPanel{
    private final JComboBox<String> comboBox;

    public SelectDifficultyComponent(String title) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        comboBox = new JComboBox<>();
        comboBox.addItem("Easy");
        comboBox.addItem("Medium");
        comboBox.addItem("Hard");

        add(titleLabel, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
    }
    public AiStrategy getDifficulty() {
        String aiDifficulty = (String) comboBox.getSelectedItem();
        assert aiDifficulty != null;
        return switch (aiDifficulty) {
            case "Easy" -> new EasyAi();
            case "Medium" -> new MediumAi();
            case "Hard" -> new HardAi();
            default -> null;
        };
    }
}
