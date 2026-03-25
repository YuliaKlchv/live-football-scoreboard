package com.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    // Store ongoing matches in memory
    private final List<Match> matches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {

        // prevent duplicate matches
        for (Match match : matches) {
            if (match.getHomeTeam().equals(homeTeam) &&
                    match.getAwayTeam().equals(awayTeam)) {
                return;
            }
        }

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

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match ->
                match.getHomeTeam().equals(homeTeam) &&
                        match.getAwayTeam().equals(awayTeam)
        );
    }

    public List<Match> getSummary() {
        return matches.stream()
                .sorted((match1, match2) -> {
                    int scoreComparison = Integer.compare(match2.getTotalScore(), match1.getTotalScore());

                    if (scoreComparison != 0) {
                        return scoreComparison;
                    }

                    return Long.compare(match2.getStartedAt(), match1.getStartedAt());
                })
                .toList();
    }

    public List<Match> getMatches() {
        // Return a copy to protect internal state
        return new ArrayList<>(matches);
    }
}