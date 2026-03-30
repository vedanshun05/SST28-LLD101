# 🐍 Snake and Ladder Implementation

## 🚀 Implementation
The game engine handles board generation, turn cycles, and multiple winners. Below is the file structure:

- `Board.java`: Randomized $n \times n$ grid with $n$ snakes and $n$ ladders. Difficulty affects snake/ladder distribution.
- `GameEngine.java`: Orchestrator for game flow, turns, and leaderboard.
- `Player.java`: State for position and win status.
- `Dice.java`: 6-sided random dice.
- `DifficultyLevel.java`: Enum for EASY/HARD modes (EASY: shorter snakes, longer ladders; HARD: longer snakes, shorter ladders).
- `Main.java`: Interactive entry point (Scanner input for $n$, $x$, and difficulty).

## 📊 Class Diagram
*I will manually draw the class diagram and attach it here.*
