package com.tictactoe.game;

import com.tictactoe.player.HumanPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    @Test
    void ConstructorTest() {
        Game g = new Game(Symbol.X, 4);
        assertEquals(g.getMaxMoves(), 16);
        assertEquals(g.getPlayer().getSymbol(), Symbol.X);
        assertEquals(g.getOpponent().getSymbol(), Symbol.O);

        g = new Game(Symbol.O, 5);
        assertEquals(g.getMaxMoves(), 25);
        assertEquals(g.getPlayer().getSymbol(), Symbol.O);
        assertEquals(g.getOpponent().getSymbol(), Symbol.X);
        assertThrows(IllegalArgumentException.class, () -> new HumanPlayer(new Board(4), Symbol.getDefault()));
    }

}