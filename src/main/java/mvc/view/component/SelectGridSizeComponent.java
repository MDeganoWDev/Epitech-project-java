package main.java.mvc.view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SelectGridSizeComponent extends JPanel {
    private final JTextField numberField;

    public SelectGridSizeComponent() {
        setLayout(new BorderLayout());
        setBackground(new Color(52, 73, 94));
        setForeground(Color.white);

        JLabel titleLabel = new JLabel("Select the grid size");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);

        numberField = new JTextField("10", 10);
        numberField.setHorizontalAlignment(JTextField.RIGHT);
        customizeTextField(numberField);

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

    public int getNumber() {
        String text = numberField.getText();
        return text.isEmpty() ? 10 : Integer.parseInt(text);
    }


    private void customizeTextField(JTextField textField) {
        textField.setBackground(new Color(44, 62, 80));
        textField.setForeground(Color.white);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));
    }
}
