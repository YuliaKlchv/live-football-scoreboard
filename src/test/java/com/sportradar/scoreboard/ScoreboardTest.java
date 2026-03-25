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

    @Test
    void shouldRemoveMatchWhenFinished() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.finishMatch("Mexico", "Canada");

        List<Match> matches = scoreboard.getMatches();

        assertEquals(0, matches.size());
    }

    @Test
    void shouldReturnMatchesOrderedByTotalScoreDescending() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);

        List<Match> summary = scoreboard.getSummary();

        assertEquals(2, summary.size());
        assertEquals("Spain", summary.get(0).getHomeTeam());
        assertEquals("Brazil", summary.get(0).getAwayTeam());
        assertEquals("Mexico", summary.get(1).getHomeTeam());
        assertEquals("Canada", summary.get(1).getAwayTeam());
    }

    @Test
    void shouldOrderByMostRecentWhenScoresAreEqual() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");

        scoreboard.updateScore("Mexico", "Canada", 2, 2);
        scoreboard.updateScore("Spain", "Brazil", 2, 2);

        List<Match> summary = scoreboard.getSummary();

        assertEquals("Spain", summary.get(0).getHomeTeam());
        assertEquals("Brazil", summary.get(0).getAwayTeam());
    }

    @Test
    void shouldReturnSummaryAsInExample() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<Match> summary = scoreboard.getSummary();

        assertEquals("Uruguay", summary.get(0).getHomeTeam());
        assertEquals("Spain", summary.get(1).getHomeTeam());
        assertEquals("Mexico", summary.get(2).getHomeTeam());
        assertEquals("Argentina", summary.get(3).getHomeTeam());
        assertEquals("Germany", summary.get(4).getHomeTeam());
    }

    @Test
    void shouldNotIncludeFinishedMatchInSummary() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 3, 2);

        scoreboard.finishMatch("Mexico", "Canada");

        List<Match> summary = scoreboard.getSummary();

        assertEquals(1, summary.size());
        assertEquals("Spain", summary.get(0).getHomeTeam());
    }

    @Test
    void shouldNotAllowDuplicateMatches() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Mexico", "Canada");

        List<Match> matches = scoreboard.getMatches();

        assertEquals(1, matches.size());
    }
}