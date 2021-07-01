package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.game.Setup;
import com.tictactoe.game.Symbol;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe!");
        int size = Setup.queryBoardSize();
        Symbol player = Setup.queryPlayerSymbol();

        // run whole game
        Symbol winner = (new Game(player, size)).run();
        if (winner != Symbol.getDefault()) {
            System.out.printf("Winner of the game is: %s%n", winner);
        } else {
            System.out.println("Game is a draw!");
        }
    }
}
