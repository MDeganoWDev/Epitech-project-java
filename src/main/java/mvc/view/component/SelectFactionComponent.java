package main.java.mvc.view.component;

import main.java.mvc.model.BigMom;
import main.java.mvc.model.Faction;
import main.java.mvc.model.Strawhat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFactionComponent extends JPanel {
    private JComboBox<String> comboBox;
    private JLabel titleLabel;

    public SelectFactionComponent(String title) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 144, 255)); // Définir la couleur du fond du panneau

        // Initialize the JLabel with the title
        titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE); // Définir la couleur du texte

        // Initialize the JComboBox and add items
        comboBox = new JComboBox<>();
        comboBox.addItem("Strawhat");
        comboBox.addItem("Big mom");

        // Définir une couleur de fond marron clair pour le JComboBox
        comboBox.setBackground(new Color(222, 184, 135));

        // Styler le JComboBox
        stylizeComboBox(comboBox);

        // Adding components to the JPanel
        add(titleLabel, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
    }

    private void stylizeComboBox(JComboBox<String> comboBox) {
        // Définir une couleur de texte
        comboBox.setForeground(Color.WHITE);

        // Définir une couleur de fond pour le JComboBox
        comboBox.setBackground(new Color(139, 69, 19));

        // Définir une bordure arrondie
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(160, 82, 45), 2));

        // Ajouter un effet de survol
        comboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comboBox.setBackground(new Color(160, 82, 45));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                comboBox.setBackground(new Color(139, 69, 19));
            }
        });
    }

    public Faction getFaction() {
        String factionName = (String) comboBox.getSelectedItem();

        switch (factionName) {
            case "Strawhat":
                return new Strawhat();
            case "Big mom":
                return new BigMom();
            default:
                return null;
        }
    }
}



