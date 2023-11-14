package main.java.mvc.view.component;

import main.java.mvc.model.Faction.BigMom;
import main.java.mvc.model.Faction.Faction;
import main.java.mvc.model.Faction.Strawhat;

import javax.swing.*;
import java.awt.*;

public class SelectFactionComponent extends JPanel{
    private final JComboBox<String> comboBox;

    public SelectFactionComponent(String title) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        comboBox = new JComboBox<>();
        comboBox.addItem("Strawhat");
        comboBox.addItem("Big mom");

        add(titleLabel, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
    }
    public Faction getFaction() {
        String factionName = (String) comboBox.getSelectedItem();
        assert factionName != null;
        return switch (factionName) {
            case "Strawhat" -> new Strawhat();
            case "Big mom" -> new BigMom();
            default -> null;
        };
    }
}
