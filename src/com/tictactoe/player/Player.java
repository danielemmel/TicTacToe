package com.tictactoe.player;

import com.tictactoe.game.Board;
import com.tictactoe.game.Coordinate;
import com.tictactoe.game.Symbol;

import java.util.Objects;

public abstract class Player {
    protected Board board;
    protected final Symbol symbol;

    public Player(Board board, Symbol symbol) throws IllegalArgumentException {
        if(symbol == Symbol.getDefault()) {
            throw new IllegalArgumentException("Cannot choose default Symbol as own Symbol");
        }
        this.board = board;
        this.symbol = symbol;
    }

    // always makes sure move is valid
    public abstract Coordinate getNextMove();

    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(board, player.board) && symbol == player.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, symbol);
    }
}
