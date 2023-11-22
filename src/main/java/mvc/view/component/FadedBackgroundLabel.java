package main.java.mvc.view.component;

import javax.swing.*;
import java.awt.*;

public class FadedBackgroundLabel extends JLabel {

    /**
     * Constructor for the FadedBackgroundLabel class
     * @param text String
     */
    public FadedBackgroundLabel(String text) {
        super(text);
    }

    /**
     * Paints the background
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 128)); // Set color to black with 50% transparency
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}