package main.java.mvc.view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SelectGridSizeComponent extends JPanel {
    private JTextField numberField;
    private JLabel titleLabel;

    public SelectGridSizeComponent() {
        // Setting the layout
        setLayout(new BorderLayout());

        // Initialize the JLabel with the title
        titleLabel = new JLabel("Select the grid size");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Initialize the JTextField with the initial number 10
        numberField = new JTextField("10", 10);
        numberField.setHorizontalAlignment(JTextField.RIGHT);

        // Add KeyListener for numeric validation and range checking
        numberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore this key event
                }
            }
            public void keyReleased(KeyEvent e) {
                if (!numberField.getText().isEmpty()) {
                    int value = Integer.parseInt(numberField.getText());
                    if (value < 10) {
                        numberField.setText("10");
                    } else if (value > 20) {
                        numberField.setText("20");
                    }
                }
            }
        });

        // Adding components to the JPanel
        add(titleLabel, BorderLayout.NORTH);
        add(numberField, BorderLayout.CENTER);
    }

    public int getNumber() {
        String text = numberField.getText();
        return text.isEmpty() ? 10 : Integer.parseInt(text);
    }
}
