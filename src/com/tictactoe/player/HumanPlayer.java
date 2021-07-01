package com.tictactoe.player;

import com.tictactoe.game.Board;
import com.tictactoe.game.Coordinate;
import com.tictactoe.game.Symbol;

import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board, Symbol symbol) {
        super(board, symbol);
    }

    @Override
    public Coordinate getNextMove() {
        System.out.printf("Current board: %n%s%n", this.board);
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter the Coordinate x,y at which to make the next move:");
                String input = scanner.nextLine();
                String[] coordinates = input.split(",");
                int x = Integer.parseInt(coordinates[0]) - 1;
                int y = Integer.parseInt(coordinates[1]) - 1;

                if (x < 0 || y < 0 || x > board.getSize() - 1 || y > board.getSize() - 1) {
                    System.out.printf("Illegal Coordinate: (%d,%d) is not part of Board of size(%d,%d)%n",
                            x + 1, y + 1, board.getSize(), board.getSize());
                    continue;
                }
                return new Coordinate(x, y);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException Exc) {
                System.out.println("Coordinate given must be of format x,y where x and y are both numbers!");
            }
        }
    }


}
