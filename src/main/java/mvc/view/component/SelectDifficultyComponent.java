package main.java.mvc.view.component;

import main.java.mvc.model.AI.*;

import javax.swing.*;
import java.awt.*;

public class SelectDifficultyComponent extends JPanel{
    private final JComboBox<String> comboBox;

    public SelectDifficultyComponent(String title) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        comboBox = new JComboBox<>();
        comboBox.addItem("Very Easy");
        comboBox.addItem("Easy");
        comboBox.addItem("Medium");
        comboBox.addItem("Hard");
        comboBox.addItem("Very Hard");

        add(titleLabel, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
    }
    public AiStrategy getDifficulty() {
        String aiDifficulty = (String) comboBox.getSelectedItem();
        assert aiDifficulty != null;
        return switch (aiDifficulty) {
            case "Very Easy" -> new VeryEasyAi();
            case "Easy" -> new EasyAi();
            case "Medium" -> new MediumAi();
            case "Hard" -> new HardAi();
            case "Very Hard" -> new VeryHardAi();
            default -> null;
        };
    }
}
