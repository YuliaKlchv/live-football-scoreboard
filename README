# Live Football World Cup Scoreboard

Simple Java library to manage live football matches in memory.

## How to run

Requires Java 17 and Maven.

Run tests with:

mvn test

## Assumptions

- Matches are stored in memory
- Duplicate matches are not allowed
- A team cannot play against itself
- Team names cannot be null or blank
- Scores cannot be negative
- Invalid input results in IllegalArgumentException
- Matches are ordered by total score (descending), then by most recently started

## Notes

- TDD approach was followed while implementing the features
- updateScore uses absolute values (not increments)
- nanoTime() is used only to preserve match start order