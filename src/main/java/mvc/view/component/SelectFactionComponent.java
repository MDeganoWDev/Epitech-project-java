package main.java.mvc.view.component;

import main.java.mvc.model.BigMom;
import main.java.mvc.model.Faction;
import main.java.mvc.model.Strawhat;

import javax.swing.*;
import java.awt.*;

public class SelectFactionComponent extends JPanel{
    private JComboBox<String> comboBox;
    private JLabel titleLabel;
    public SelectFactionComponent(String title) {
        setLayout(new BorderLayout());

        // Initialize the JLabel with the title
        titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Initialize the JComboBox and add items
        comboBox = new JComboBox<>();
        comboBox.addItem("Strawhat");
        comboBox.addItem("Big mom");

        // Adding components to the JPanel
        add(titleLabel, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
    }
    public Faction getFaction() {
        String factionName = (String) comboBox.getSelectedItem();
        return switch (factionName) {
            case "Strawhat" -> new Strawhat();
            case "Big mom" -> new BigMom();
            default -> null;
        };
    }
}
