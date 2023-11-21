package main.java.mvc.view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SelectGridSizeComponent extends JPanel {
    private final JTextField numberField;

    /**
     * Constructor for the SelectGridSizeComponent class
     */
    public SelectGridSizeComponent() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select the grid size");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        numberField = new JTextField("10", 10);
        numberField.setHorizontalAlignment(JTextField.RIGHT);

        numberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
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
        add(titleLabel, BorderLayout.NORTH);
        add(numberField, BorderLayout.CENTER);
    }

    /**
     * Gets the number
     * @return int
     */
    public int getNumber() {
        String text = numberField.getText();
        return text.isEmpty() ? 10 : Integer.parseInt(text);
    }
}
