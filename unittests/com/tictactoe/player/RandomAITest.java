package com.tictactoe.player;

import com.tictactoe.game.Board;
import com.tictactoe.game.Coordinate;
import com.tictactoe.game.Symbol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomAITest {
    private final Board B = new Board(3);

    @Test
    void GetSymbolTest() {
        var P = new RandomAI(B, Symbol.X);
        assertEquals(P.getSymbol(), Symbol.X);
        P = new RandomAI(B, Symbol.O);
        assertEquals(P.getSymbol(), Symbol.O);
        assertThrows(IllegalArgumentException.class, () -> new RandomAI(B, Symbol.getDefault()));
    }

    @Test
    void EqualsTest() {
        var P = new RandomAI(B, Symbol.X);
        assertEquals(P, P);
        assertNotEquals(P, new HumanPlayer(B, Symbol.X));
        var P2 = new RandomAI(B, Symbol.O);
        assertNotEquals(P, P2);
        var B2 = new Board(4);
        assertNotEquals(P, new RandomAI(B2, Symbol.X));
    }
    
    @Test
    void GetNextMoveTest() {
        var P = new RandomAI(B, Symbol.X);

        assertDoesNotThrow(P::getNextMove);
        Coordinate c = P.getNextMove();
        assertTrue(B.isFree(c));
        assertDoesNotThrow(() -> B.setSymbol(c, Symbol.X));
    }
}