package com.tictactoe.game;

import java.util.Arrays;

public class Board {
    private final Symbol[][] board;

    /**
     * Generate a new n times n Board
     *
     * @param n Base size of new Board
     * @throws IllegalArgumentException: If the Size is less than three
     */
    public Board(int n) throws IllegalArgumentException {
        if (n < 3) {
            throw new IllegalArgumentException("Board must be at least 3x3");
        }
        if(n > 10) {
            throw new IllegalArgumentException("Board must be 10x10 or smaller");
        }
        this.board = new Symbol[n][n];
        // Fill whole board with NONE
        var boardBase = new Symbol[n];
        Arrays.fill(boardBase, Symbol.getDefault());
        // use clone of boardBase, otherwise all rows would share reference to same column
        for (int i = 0; i < board.length; i++) {
            board[i] = boardBase.clone();
        }
    }

    public int getSize() {
        return this.board.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            sb.append('[');
            for (int x = 0; x < board.length; x++) {
                sb.append(board[x][y]);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(']');
            sb.append('\n');
        }
        return sb.toString();
    }

    public boolean isFree(Coordinate Coor) {
        int x = Coor.x();
        int y = Coor.y();
        return x >= 0 && y >= 0 && x < board.length && y < board.length && board[x][y] == Symbol.getDefault();
    }

    /**
     * Sets the Symbol at a given place of the board
     *
     * @param c      The coordinate on the board
     * @param symbol The new symbol to be set
     * @throws IllegalArgumentException if the given coordinate not within board bounds or the field is already occupied
     */
    public void setSymbol(Coordinate c, Symbol symbol) throws IllegalArgumentException {
        if (!this.isFree(c)) {
            throw new IllegalArgumentException("Given coordinates out of board bounds or coordinate already filled");
        }
        board[c.x()][c.y()] = symbol;
    }

    public Symbol getPotentialWinner() {
        // check rows
        outer:
        for (int y = 0; y < board.length; y++) {
            if (board[0][y] == Symbol.getDefault()) {
                continue;
            }
            for (int x = 1; x < board.length; x++) {
                if (board[x][y] != board[0][y]) {
                    continue outer;
                }
            }
            // all symbols in row are equal player symbol
            return board[0][y];
        }
        // check columns
        for (Symbol[] column : this.board) {
            // all values in column equal player symbol
            if (column[0] != Symbol.getDefault() && Arrays.stream(column).distinct().count() == 1) {
                return column[0];
            }
        }
        // check main diagonal
        if (board[0][0] != Symbol.getDefault()) {
            boolean allEqual = true;

            for (int i = 1; i < board.length; i++) {
                // diagonal cannot be a win for either party anymore
                if (board[i][i] != board[0][0]) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                // all values across diagonal are equal
                return board[0][0];
            }
        }
        // check other diagonal
        if (board[board.length - 1][0] != Symbol.getDefault()) {
            int i = board.length - 2;
            int j = 1;
            boolean allEqual = true;

            do {
                if (board[i][j] != board[board.length - 1][0]) {
                    // diagonal cannot be a win for either party anymore
                    allEqual = false;
                    break;
                }
                i--;
                j++;
            } while (i >= 0 && j < board.length);

            if (allEqual) {
                return board[board.length - 1][0];
            }
        }
        // no winner found
        return Symbol.getDefault();
    }
}
