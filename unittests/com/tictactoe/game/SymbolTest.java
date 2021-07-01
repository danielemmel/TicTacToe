package com.tictactoe.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolTest {

    @Test
    void testString() {
        assertTrue(Symbol.getDefault().toString().isBlank());
        assertEquals(Symbol.X.toString(), "X");
        assertEquals(Symbol.O.toString(), "O");
    }
}