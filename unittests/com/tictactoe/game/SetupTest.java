package com.tictactoe.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SetupTest {
    private final InputStream oldIn = System.in;
    private final ByteArrayOutputStream s = new ByteArrayOutputStream();
    private final PrintStream oldOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(s));
    }

    @AfterEach
    void tearDown() {
        System.setOut(oldOut);
        s.reset();
        System.setIn(oldIn);
    }

    private void overwriteStream(String val) {
        InputStream stream = new ByteArrayInputStream(val.getBytes());
        System.setIn(stream);
    }

    @Test
    void queryBoardSizeTest() {
        overwriteStream("abc\n-2\n4");
        Setup.queryBoardSize();
        assertTrue(s.toString().contains("board size of at least 3"));
        assertTrue(s.toString().contains("input a valid Number"));
    }

    @Test
    void queryPlayerSymbolTest() {
        overwriteStream("42\nX");
        Setup.queryPlayerSymbol();
        assertTrue(s.toString().contains("Invalid Player Symbol"));
    }

}