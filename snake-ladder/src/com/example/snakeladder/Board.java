package com.example.snakeladder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Board {
    private final int size;
    private final int totalCells;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public Board(int size, DifficultyLevel difficulty) {
        this.size = size;
        this.totalCells = size * size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        initializeBoard(size, difficulty);
    }

    private void initializeBoard(int n, DifficultyLevel difficulty) {
        Random random = new Random();
        Set<Integer> occupiedCells = new HashSet<>();
        
        // Difficulty affects the distribution and length of snakes/ladders
        // EASY: Shorter snakes, longer ladders (more helpful)
        // HARD: Longer snakes, shorter ladders (more challenging)
        
        // Add n snakes
        int snakeCount = 0;
        while (snakeCount < n) {
            int head = random.nextInt(totalCells - 2) + 2; // Head can't be at 1
            int tail;
            
            if (difficulty == DifficultyLevel.EASY) {
                // EASY: Snakes slide down less (max 30% of head position)
                int maxSlide = Math.max(1, (int)(head * 0.3));
                tail = head - random.nextInt(maxSlide) - 1;
                if (tail < 1) tail = 1;
            } else {
                // HARD: Snakes can slide down more (up to 70% of head position)
                int maxSlide = Math.max(1, (int)(head * 0.7));
                tail = head - random.nextInt(maxSlide) - 1;
                if (tail < 1) tail = 1;
            }
            
            // Ensure no cell is used twice to strictly prevent cycles and overlap
            if (!occupiedCells.contains(head) && !occupiedCells.contains(tail)) {
                snakes.put(head, tail);
                occupiedCells.add(head);
                occupiedCells.add(tail);
                snakeCount++;
            }
        }

        // Add n ladders
        int ladderCount = 0;
        while (ladderCount < n) {
            int start = random.nextInt(totalCells - 2) + 2; // Start can't be at 1
            int end;
            
            if (difficulty == DifficultyLevel.EASY) {
                // EASY: Ladders climb higher (50-80% of remaining distance)
                int remainingCells = totalCells - start;
                int minClimb = (int)(remainingCells * 0.5);
                int maxClimb = (int)(remainingCells * 0.8);
                if (minClimb < 1) minClimb = 1;
                if (maxClimb < minClimb) maxClimb = minClimb;
                end = start + minClimb + random.nextInt(maxClimb - minClimb + 1);
            } else {
                // HARD: Ladders climb less (20-40% of remaining distance)
                int remainingCells = totalCells - start;
                int minClimb = (int)(remainingCells * 0.2);
                int maxClimb = (int)(remainingCells * 0.4);
                if (minClimb < 1) minClimb = 1;
                if (maxClimb < minClimb) maxClimb = minClimb;
                end = start + minClimb + random.nextInt(maxClimb - minClimb + 1);
            }
            
            if (end >= totalCells) continue;

            if (!occupiedCells.contains(start) && !occupiedCells.contains(end)) {
                ladders.put(start, end);
                occupiedCells.add(start);
                occupiedCells.add(end);
                ladderCount++;
            }
        }
    }

    public int getFinalPosition(int currentPos) {
        if (currentPos > totalCells) return currentPos;
        
        if (snakes.containsKey(currentPos)) {
            int nextPos = snakes.get(currentPos);
            System.out.println("Oops! Bitten by a snake at " + currentPos + ", sliding down to " + nextPos);
            return nextPos;
        }
        if (ladders.containsKey(currentPos)) {
            int nextPos = ladders.get(currentPos);
            System.out.println("Yay! Found a ladder at " + currentPos + ", climbing up to " + nextPos);
            return nextPos;
        }
        return currentPos;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void printBoardInfo() {
        System.out.println("Board Size: " + size + "x" + size + " (" + totalCells + " cells)");
        System.out.println("Snakes: " + snakes);
        System.out.println("Ladders: " + ladders);
    }
}
