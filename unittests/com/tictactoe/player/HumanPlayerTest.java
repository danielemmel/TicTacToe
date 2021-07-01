package com.tictactoe.player;

import com.tictactoe.game.Board;
import com.tictactoe.game.Coordinate;
import com.tictactoe.game.Symbol;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {
    private final Board B = new Board(3);
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

    private void executeFunctionWithTimeout(Object Obj, String methodName, long seconds) {
        final Runnable stuffToDo = new Thread(() -> {
            try {
                Obj.getClass().getMethod(methodName).invoke(Obj);
            } catch (Exception ignored) {

            }
        });

        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<?> future = executor.submit(stuffToDo);
        try {
            future.get(seconds, TimeUnit.SECONDS);
        } catch (Exception ignored) {

        }
        if (!executor.isTerminated()) {
            executor.shutdownNow();
        }
    }


    @Test
    void GetSymbolTest() {
        var P = new HumanPlayer(B, Symbol.X);
        assertEquals(P.getSymbol(), Symbol.X);
        P = new HumanPlayer(B, Symbol.O);
        assertEquals(P.getSymbol(), Symbol.O);
        assertThrows(IllegalArgumentException.class, () -> new HumanPlayer(B, Symbol.getDefault()));
    }

    @Test
    void EqualsTest() {
        var P = new HumanPlayer(B, Symbol.X);
        assertEquals(P, P);
        assertNotEquals(P, new RandomAI(B, Symbol.X));
        var P2 = new HumanPlayer(B, Symbol.O);
        assertNotEquals(P, P2);
        var B2 = new Board(4);
        assertNotEquals(P, new HumanPlayer(B2, Symbol.X));
    }

    @Test
    void GetNextMoveInvalidFormatTest() {
        overwriteStream("abc");
        var P = new HumanPlayer(B, Symbol.X);
        executeFunctionWithTimeout(P, "getNextMove", 1);
        assertTrue(s.toString().contains("Coordinate given must be of format x,y"));

        overwriteStream("1,");
        executeFunctionWithTimeout(P, "getNextMove", 1);
        assertTrue(s.toString().contains("Coordinate given must be of format x,y"));
    }

    @Test
    void GetNextMoveInvalidCoordinateTest() {
        overwriteStream("0,0");
        var P = new HumanPlayer(B, Symbol.X);
        executeFunctionWithTimeout(P, "getNextMove", 1);
        assertTrue(s.toString().contains("Illegal Coordinate") && s.toString().contains("(0,0)"));

        overwriteStream("4,4");
        executeFunctionWithTimeout(P, "getNextMove", 1);
        assertTrue(s.toString().contains("Illegal Coordinate") && s.toString().contains("(4,4)"));
    }

    @Test
    void GetNextMoveValidTest() {
        // Input 1,1 means 0,0 internally
        overwriteStream("1,1");
        var P = new HumanPlayer(B, Symbol.X);
        Coordinate c = P.getNextMove();
        assertEquals(c.x(), 0);
        assertEquals(c.y(), 0);
    }
}