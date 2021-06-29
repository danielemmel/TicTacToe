package com.tictactoe.player;

import com.tictactoe.game.Board;
import com.tictactoe.game.Coordinate;
import com.tictactoe.game.Symbol;

import java.util.Random;

public class RandomAI extends Player {

    public RandomAI(Board board, Symbol symbol) {
        super(board, symbol);
    }

    @Override
    public Coordinate getNextMove() {
        int x;
        int y;
        boolean validChoice;
        Random r = new Random();
        int boardSize = board.getSize();

        do {
            x = r.nextInt(boardSize);
            y = r.nextInt(boardSize);
            // check whether random Coordinate is free
            validChoice = board.isFree(new Coordinate(x, y));
        } while (!validChoice);

        return new Coordinate(x, y);
    }
}
