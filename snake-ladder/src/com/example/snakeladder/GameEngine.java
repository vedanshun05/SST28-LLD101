package com.example.snakeladder;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final List<Player> winners;
    private int currentPlayerIndex;

    public GameEngine(int boardSize, List<String> playerNames, DifficultyLevel difficulty) {
        this.board = new Board(boardSize, difficulty);
        this.players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(String.valueOf(i + 1), playerNames.get(i)));
        }
        this.dice = new Dice(6);
        this.winners = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

    public void start() {
        System.out.println("Starting Snake and Ladder Game!");
        board.printBoardInfo();
        System.out.println("Players: ");
        for (Player p : players) {
            System.out.println("- " + p.getName());
        }
        System.out.println("---");

        // The game should continue till there are at least 2 players still playing to win.
        // If total players < 2, the game can't really "continue till 2 players are still playing".
        // If x=2, it continues until one wins, leaving 1 still playing.
        // The rule "continue till there are at least 2 players still playing" might mean:
        // Keep playing as long as (total players - winners) >= 2.
        // Wait, "at least 2 players still playing to win" means we stop when only 1 is left.
        
        while ((players.size() - winners.size()) >= 2) {
            Player currentPlayer = players.get(currentPlayerIndex);
            
            if (currentPlayer.isHasWon()) {
                moveToNextPlayer();
                continue;
            }

            playTurn(currentPlayer);
            
            if (currentPlayer.isHasWon()) {
                System.out.println("🏆 " + currentPlayer.getName() + " finished the game!");
                winners.add(currentPlayer);
            }

            moveToNextPlayer();
        }
        
        System.out.println("--- Game Over ---");
        System.out.println("Leaderboard: ");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println((i + 1) + ". " + winners.get(i).getName());
        }
        
        // List the player who didn't finish
        for (Player p : players) {
            if (!p.isHasWon()) {
                System.out.println((winners.size() + 1) + ". " + p.getName() + " (Still on board at position " + p.getPosition() + ")");
            }
        }
    }

    private void playTurn(Player player) {
        int diceValue = dice.roll();
        int currentPosition = player.getPosition();
        int nextPosition = currentPosition + diceValue;

        System.out.print(player.getName() + " rolled a " + diceValue + " and moved from " + currentPosition + " to ");

        if (nextPosition > board.getTotalCells()) {
            System.out.println(currentPosition + " (Roll too high, stay put)");
        } else {
            // Apply snakes/ladders
            nextPosition = board.getFinalPosition(nextPosition);
            player.setPosition(nextPosition);
            System.out.println(nextPosition);

            if (nextPosition == board.getTotalCells()) {
                player.setHasWon(true);
            }
        }
    }

    private void moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
