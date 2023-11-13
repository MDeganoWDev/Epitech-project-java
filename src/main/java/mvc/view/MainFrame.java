package main.java.mvc.view;

import main.java.mvc.controller.GameController;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private JLabel backgroundLabel;

    public MainFrame() {
        initializeUI();
        playBackgroundMusic("src/main/resources/epicSound.mp3");
        setVisible(true);
    }

    private void initializeUI() {
        setTitle("Battleship Game");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Load the image
        ImageIcon imageIcon = new ImageIcon("src/main/resources/shipAC.jpg");

        // Add a label for the background image
        backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setLayout(new GridBagLayout());

        // Create a custom-styled button
        JButton startGameButton = createStyledButton("Start New Game");

        // Set layout constraints for the button
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.insets = new Insets(250, 50, 50, 50); // Top margin

        // Add the button to the background label
        backgroundLabel.add(startGameButton, gbcButton);

        // Set the background label as the content pane
        setContentPane(backgroundLabel);

        // Add a component listener to detect size changes on the contentPane
        addResizeListener(getContentPane());
    }

    private void addResizeListener(Component component) {
        component.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundImageSize();
            }
        });
    }

    private void updateBackgroundImageSize() {
        // Load the image and resize it to the current frame size
        ImageIcon imageIcon = new ImageIcon("src/main/resources/shipAC.jpg");
        Image image = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(image);

        // Set the resized image as the icon for the background label
        backgroundLabel.setIcon(resizedImageIcon);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 153, 255));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Rounded corners
        button.setOpaque(true);
        button.setBorder(new RoundedBorder(10, new Color(51, 153, 255)));

        // Add a hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(102, 178, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255));
            }
        });

        // Add action listener
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.selectFactionView();
            }
        });

        return button;
    }

    private static class RoundedBorder implements Border {
        private final int radius;
        private final Color color;

        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    private void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Démarre la lecture en boucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Optionnel : réglez le volume (de 0.0 à 1.0)
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Réglez le volume ici

            // Optionnel : jouez la piste sonore
            clip.start();

            // Attendez que la piste sonore soit lue (c'est une option)
            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
