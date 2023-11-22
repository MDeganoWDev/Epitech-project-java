package main.java.mvc.view.component;

import main.java.mvc.model.AI.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;


public class SelectDifficultyComponent extends JPanel{
    private final JComboBox<String> comboBox;

    public SelectDifficultyComponent(String title) {
        setLayout(new BorderLayout());
        setBackground(new Color(52, 73, 94));
        setForeground(Color.white);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);

        comboBox = new JComboBox<>();
        comboBox.addItem("Very Easy");
        comboBox.addItem("Easy");
        comboBox.addItem("Medium");
        comboBox.addItem("Hard");
        comboBox.addItem("Very Hard");

        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBackground(new Color(52, 152, 219));
                button.setForeground(Color.white);
                return button;
            }
        });

        customizeComboBox(comboBox);

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


    private void customizeComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(44, 62, 80));
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));
    }
}
