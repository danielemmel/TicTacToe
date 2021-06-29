package com.tictactoe.player;

import com.tictactoe.game.Board;
import com.tictactoe.game.Coordinate;
import com.tictactoe.game.Symbol;

public abstract class Player {
    protected Board board;
    protected final Symbol symbol;

    public Player(Board board, Symbol symbol) {
        this.board = board;
        this.symbol = symbol;
    }

    // always makes sure move is valid
    public abstract Coordinate getNextMove();

    public Symbol getSymbol() {
        return this.symbol;
    }
}
