package main.java.mvc.view.component;

import javax.swing.*;
import java.awt.*;

public class BackgroundGamePanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructor for the BackgroundGamePanel class
     * @param imagePath String
     */
    public BackgroundGamePanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the background image
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
