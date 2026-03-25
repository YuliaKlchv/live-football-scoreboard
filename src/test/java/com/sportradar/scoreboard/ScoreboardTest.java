package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreboardTest {

    @Test
    void shouldStartNewMatchWithInitialScoreZeroZero() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");

        List<Match> matches = scoreboard.getMatches();

        assertEquals(1, matches.size());
        assertEquals("Mexico", matches.get(0).getHomeTeam());
        assertEquals("Canada", matches.get(0).getAwayTeam());
        assertEquals(0, matches.get(0).getHomeScore());
        assertEquals(0, matches.get(0).getAwayScore());
    }

    @Test
    void shouldUpdateMatchScore() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 2, 1);

        List<Match> matches = scoreboard.getMatches();

        assertEquals(1, matches.size());
        assertEquals(2, matches.get(0).getHomeScore());
        assertEquals(1, matches.get(0).getAwayScore());
    }
}