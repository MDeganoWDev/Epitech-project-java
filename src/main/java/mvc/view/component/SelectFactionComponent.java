package main.java.mvc.view.component;

import main.java.mvc.model.Faction.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class SelectFactionComponent extends JPanel{
    private final JComboBox<String> comboBox;

    public SelectFactionComponent(String title) {
        setLayout(new BorderLayout());
        setBackground(new Color(52, 73, 94));
        setForeground(Color.white);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);

        comboBox = new JComboBox<>();
        comboBox.addItem("France");
        comboBox.addItem("Angleterre");
        comboBox.addItem("Espagne");
        comboBox.addItem("Portugal");
        comboBox.addItem("Barbe Noire");
        comboBox.addItem("Barbe Rousse");

        // Personnalisation du JComboBox
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

    public Faction getFaction() {
        String factionName = (String) comboBox.getSelectedItem();
        assert factionName != null;
        return switch (factionName) {
            case "France" -> new France();
            case "Angleterre" -> new Angleterre();
            case "Espagne" -> new Espagne();
            case "Portugal" -> new Portugal();
            case "Barbe Noire" -> new BarbeNoire();
            case "Barbe Rousse" -> new BarbeRousse();
            default -> throw new IllegalStateException("Unexpected value: " + factionName);
        };
    }

    private void customizeComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(44, 62, 80));
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));
    }
}
