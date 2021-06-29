package com.tictactoe.game;

import com.tictactoe.player.HumanPlayer;
import com.tictactoe.player.Player;
import com.tictactoe.player.RandomAI;

import java.util.Random;
import java.util.function.IntPredicate;

import static com.tictactoe.game.Symbol.O;
import static com.tictactoe.game.Symbol.X;

public class Game {
    private final Player Player;
    private final Player Opponent;
    private final Board board;
    private final int maxMoves;

    public Game(Symbol Player, int n) throws IllegalArgumentException {
        this.board = new Board(n);
        this.Player = new HumanPlayer(board, Player);
        this.Opponent = new RandomAI(board, switch (Player) {
            case X -> O;
            case O -> X;
            default -> throw new IllegalArgumentException("Player Symbol is not valid!");
        });
        this.maxMoves = n * n;
    }

    private void executeNextPlayerMove() {
        while(true) {
            try {
                board.setSymbol(this.Player.getNextMove(), Player.getSymbol());
                return;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Symbol run() {
        boolean playerStarts = (new Random()).nextBoolean();
        Symbol winner;
        IntPredicate playersTurn = (turnNum) -> playerStarts ? (turnNum % 2 == 0) : (turnNum % 2 == 1);
        for (int i = 0; i < maxMoves; i++) {
            if (playersTurn.test(i)) {
                this.executeNextPlayerMove();
            } else {
                // getNextMove always returns valid move in this case
                board.setSymbol(this.Opponent.getNextMove(), this.Opponent.getSymbol());
            }
            if(i >= board.getSize()) {
                winner = board.getPotentialWinner();
                if (winner != Symbol.getDefault()) {
                    System.out.println(board);
                    return winner;
                }
            }
        }
        // Game is a draw
        System.out.println(board);
        return Symbol.getDefault();
    }
}
