package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.game.Symbol;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static int queryBoardSize() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please choose a board size of at least 3:");
                int val = scanner.nextInt();
                if (val < 3) {
                    System.out.println("Please input a size of at least 3!");
                    continue;
                }
                return val;
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid Number!");
                // advance scanner to prevent garbage reading
                scanner.nextLine();
            }
        }
    }

    public static Symbol queryPlayerSymbol() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose a Player Symbol: X or O");
            String input = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (input.equals("x") || input.equals("o")) {
                return switch (input) {
                    case "x" -> Symbol.X;
                    case "o" -> Symbol.O;
                    // unreachable but needs to be there for compilation
                    default -> Symbol.getDefault();
                };
            } else {
                System.out.println("Invalid Player Symbol");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe!");
        int size = Main.queryBoardSize();
        Symbol player = Main.queryPlayerSymbol();

        // run whole game
        Symbol winner = (new Game(player, size)).run();
        if (winner != Symbol.getDefault()) {
            System.out.printf("Winner of the game is: %s%n", winner);
        } else {
            System.out.println("Game is a draw!");
        }
    }
}
