package main.java.mvc.view.component;

import main.java.mvc.model.Faction.*;

import javax.swing.*;
import java.awt.*;

public class SelectFactionComponent extends JPanel{
    private final JComboBox<String> comboBox;

    /**
     * Constructor for the SelectFactionComponent class
     * @param title String
     */
    public SelectFactionComponent(String title) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        comboBox = new JComboBox<>();
        comboBox.addItem("France");
        comboBox.addItem("Angleterre");
        comboBox.addItem("Espagne");
        comboBox.addItem("Portugal");
        comboBox.addItem("Barbe Noire");
        comboBox.addItem("Barbe Rousse");

        add(titleLabel, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
    }

    /**
     * Gets the faction
     * @return Faction object
     */
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
}
