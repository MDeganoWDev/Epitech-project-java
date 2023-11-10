package main.java.mvc.view;

import main.java.mvc.model.Board;

import javax.swing.*;
import java.awt.*;

public class GridBoard extends JPanel {
    public GridBoard(Board board){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < board.getRows(); i++) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            for (int j = 0; j < board.getColumns(); j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createEtchedBorder());
                cell.setPreferredSize(new Dimension(30, 30));
                row.add(cell);
            }
            add(row);
        }
    }
}
