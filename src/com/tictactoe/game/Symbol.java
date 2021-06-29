package com.tictactoe.game;

public enum Symbol {
    NONE,
    X,
    O;

    @Override
    public String toString() {
        return switch (this) {
            case NONE -> " ";
            case X -> "X";
            case O -> "O";
        };
    }

    /**
     *
     * @return The symbol that represents an empty field
     */
    public static Symbol getDefault() {
        return NONE;
    }
}
