package com.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    // Store ongoing matches in memory
    private final List<Match> matches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(
                homeTeam,
                awayTeam,
                0,
                0,
                System.nanoTime()
        );
        matches.add(match);
    }
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(homeTeam) &&
                    match.getAwayTeam().equals(awayTeam)) {

                match.updateScore(homeScore, awayScore);
                return;
            }
        }
    }

    public List<Match> getMatches() {
        // Return a copy to protect internal state
        return new ArrayList<>(matches);
    }
}