package main.java.mvc.model.AI;

import main.java.mvc.model.Board;
import main.java.mvc.model.Ship.Ship;
import main.java.mvc.controller.GameController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumAi implements AiStrategy {
    private final Random random = new Random();
    private final Board boardPlayer1;
    private Ship shipTouched;
    private  boolean isHit = false;
    private final List<Point> availableMoves;
    private final List<Point> potentialHits;

    public MediumAi() {
        this.boardPlayer1 = GameController.player1.getOwnBoard();
        int size = GameController.getBoardSize();
        availableMoves = new ArrayList<>();
        potentialHits = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                availableMoves.add(new Point(x, y));
            }
        }
    }
    public Point makeMove(Board board) {
        if (availableMoves.isEmpty()) {
            throw new IllegalStateException("No more moves available");
        }
        Point move;
        int index;
        isShipSunk();
        if (isHit) {
            index = random.nextInt(potentialHits.size());
            move = potentialHits.get(index);
            potentialHits.remove(index);
            availableMoves.remove(move);
        } else {
            index = random.nextInt(availableMoves.size());
            move = availableMoves.get(index);
            availableMoves.remove(index);
        }
        testCell(move);
        return move;
    }
    public void addPotentialHits(Point initialHit){
        checkPotentialHit(initialHit.x + 1, initialHit.y);
        checkPotentialHit(initialHit.x - 1, initialHit.y);
        checkPotentialHit(initialHit.x, initialHit.y + 1);
        checkPotentialHit(initialHit.x, initialHit.y - 1);
    }
    private void checkPotentialHit(int x, int y) {
        Point potentialHit = new Point(x, y);
        if (x >= 0 && y >= 0 && x < GameController.getBoardSize() && y < GameController.getBoardSize() && availableMoves.contains(potentialHit)) {
            potentialHits.add(potentialHit);
        }
    }
    public void testCell(Point cell){
        Board.Status status = boardPlayer1.getCellStatus(cell.x, cell.y);
        if (status == Board.Status.SHIP) {
            this.shipTouched = boardPlayer1.getShip(cell);
            this.isHit = true;
            addPotentialHits(cell);
        }
    }
    public void isShipSunk(){
        if (this.shipTouched != null && this.shipTouched.isSunk()) {
            this.isHit = false;
            this.shipTouched = null;
            this.potentialHits.clear();
        }
    }
}
