package com.tictactoe.game;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public final class Setup {

    public static int queryBoardSize() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please choose a board size of at least 3 but not higher than 10:");
                int val = scanner.nextInt();
                if (val < 3 || val > 10) {
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
}
