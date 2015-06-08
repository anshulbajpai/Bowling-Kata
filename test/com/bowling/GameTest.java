package com.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void itShouldTellScoreForBlankThrow() {
        assertEquals(0, new Game().pins(0).score());
    }

    @Test
    public void itShouldTellScoreForOneThrow() {
        assertEquals(1, new Game().pins(1).score());
    }

    @Test
    public void itShouldTellScoreForAFrame() {
        assertEquals(5, new Game().pins(1).pins(4).pins(4).pins(5).scoreTillFrame(1));
        assertEquals(14, new Game().pins(1).pins(4).pins(4).pins(5).scoreTillFrame(2));
        assertEquals(14, new Game().pins(1).pins(4).pins(4).pins(5).score());
    }

    @Test
    public void itShouldTellScoreForAStrike() {
        assertEquals(29, new Game().pins(1).pins(4).pins(4).pins(5).pins(6).pins(4).pins(5).scoreTillFrame(3));
    }

    @Test
    public void itShouldTellScoreForASpare() {
        Game game = new Game()
                .pins(1).pins(4)
                .pins(4).pins(5)
                .pins(6).pins(4)
                .pins(5).pins(5)
                .pins(10)
                .pins(0).pins(1);
        assertEquals(49, game.scoreTillFrame(4));
        assertEquals(60, game.scoreTillFrame(5));
        assertEquals(61, game.scoreTillFrame(6));
        assertEquals(61, game.score());
    }

    @Test
    public void itShouldTellScoreForTheCompleteGame() {
        Game game = new Game()
                .pins(1).pins(4)
                .pins(4).pins(5)
                .pins(6).pins(4)
                .pins(5).pins(5)
                .pins(10)
                .pins(0).pins(1)
                .pins(7).pins(3)
                .pins(6).pins(4)
                .pins(10)
                .pins(2).pins(8).pins(6);
        assertEquals(133, game.score());
    }
}
