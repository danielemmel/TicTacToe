package com.tictactoe.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void ConstructorTestValid() {
        assertDoesNotThrow(() -> new Board(3));
        assertDoesNotThrow(() -> new Board(10));
    }

    @Test
    void ConstructorTestInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Board(2));
        assertThrows(IllegalArgumentException.class, () -> new Board(0));
        assertThrows(IllegalArgumentException.class, () -> new Board(-42));
        assertThrows(IllegalArgumentException.class, () -> new Board(11));
        assertThrows(IllegalArgumentException.class, () -> new Board(4200));
    }

    @Test
    void BoardLengthTest() {
        var B = new Board(3);
        assertEquals(B.getSize(), 3);
        B = new Board(7);
        assertEquals(B.getSize(), 7);
    }

    @Test
    void IsFreeTest() {
        var B = new Board(3);
        assertFalse(B.isFree(new Coordinate(-1, 0)));
        assertFalse(B.isFree(new Coordinate(-1, -1)));
        assertFalse(B.isFree(new Coordinate(0, -1)));

        assertFalse(B.isFree(new Coordinate(0, 3)));
        assertFalse(B.isFree(new Coordinate(3, 0)));
        assertFalse(B.isFree(new Coordinate(3, 3)));
        assertFalse(B.isFree(new Coordinate(4, 4)));

        assertTrue(B.isFree(new Coordinate(0, 0)));
        assertTrue(B.isFree(new Coordinate(2, 2)));

        B.setSymbol(new Coordinate(0, 0), Symbol.X);
        assertFalse(B.isFree(new Coordinate(0, 0)));
    }

    @Test
    void SetIllegalSymbolTest() {
        var B = new Board(3);
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(-1, 0), Symbol.getDefault()));
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(0, -1), Symbol.getDefault()));
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(-1, -1), Symbol.getDefault()));

        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(0, 3), Symbol.getDefault()));
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(3, 0), Symbol.getDefault()));
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(3, 3), Symbol.getDefault()));
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(4, 4), Symbol.getDefault()));

        assertDoesNotThrow(() -> B.setSymbol(new Coordinate(0, 0), Symbol.X));
        assertThrows(IllegalArgumentException.class, () -> B.setSymbol(new Coordinate(0,0), Symbol.getDefault()));
    }

    @Test
    void SetWorkingSymbolTest() {
        var B = new Board(3);

        assertDoesNotThrow(() -> B.setSymbol(new Coordinate(0, 0), Symbol.X));
        assertDoesNotThrow(() -> B.setSymbol(new Coordinate(0, 1), Symbol.X));
        assertDoesNotThrow(() -> B.setSymbol(new Coordinate(2, 2), Symbol.X));
        assertDoesNotThrow(() -> B.setSymbol(new Coordinate(0, 2), Symbol.X));
        assertDoesNotThrow(() -> B.setSymbol(new Coordinate(2, 0), Symbol.X));
    }

    @Test
    void TestPotentialWinnerRow() {
        var B = new Board(3);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());

        // row win
        B.setSymbol(new Coordinate(0, 0), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(1, 0), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(2, 0), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.X);
    }

    @Test
    void TestPotentialWinnerColumn() {
        // column win
        var B = new Board(3);

        B.setSymbol(new Coordinate(0, 0), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(0, 1), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(0, 2), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.X);
    }

    @Test
    void TestPotentialWinnerDiagonal() {
        // diagonal win
        var B = new Board(3);

        B.setSymbol(new Coordinate(0, 0), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(1, 1), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(2, 2), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.O);
    }

    @Test
    void TestPotentialWinnerAntidiagonal() {
        // antidiagonal win
        var B = new Board(3);

        B.setSymbol(new Coordinate(2, 0), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(1, 1), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(0, 2), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.O);
    }

    @Test
    void TestPotentialWinnerTie() {
        // tie
        var B = new Board(3);

        B.setSymbol(new Coordinate(0, 0), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(1, 0), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(2, 0), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(0, 1), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(1, 1), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(2, 1), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(0, 2), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(1, 2), Symbol.O);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());
        B.setSymbol(new Coordinate(2, 2), Symbol.X);
        assertEquals(B.getPotentialWinner(), Symbol.getDefault());

    }
}