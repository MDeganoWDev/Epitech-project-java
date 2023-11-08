package main.java;

import main.java.mvc.controller.GameController;
import main.java.mvc.controller.GameMenuController;
import main.java.mvc.view.GameMenuView;

import javax.swing.SwingUtilities;

public class Application {
    public static void main(String[] args) {
        GameMenuView menuView = new GameMenuView();
        GameMenuController menuController = new GameMenuController(menuView);
        menuView.display();
    }

}
