package main.java.mvc.view;

import main.java.mvc.controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {

    public MainFrame() {
        initializeFrame();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Battleship Game");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
