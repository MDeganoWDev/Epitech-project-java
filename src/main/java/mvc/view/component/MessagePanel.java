package main.java.mvc.view.component;

import javax.swing.*;

public class MessagePanel extends JPanel {
    private JLabel titleLabel;
    private JLabel messageLabel;

    public MessagePanel(String title) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.titleLabel = new JLabel(title);
        add(titleLabel);

        this.messageLabel = new JLabel("");
        add(messageLabel);
    }

    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }

}
