package com.example.snakeladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to Snake and Ladder ---");
        
        System.out.print("Enter board size (n for nxn): ");
        int n = scanner.nextInt();
        
        System.out.print("Enter number of players: ");
        int x = scanner.nextInt();
        
        System.out.print("Enter difficulty level (EASY/HARD): ");
        String difficultyStr = scanner.next().toUpperCase();
        DifficultyLevel difficulty = DifficultyLevel.valueOf(difficultyStr);
        
        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            playerNames.add(scanner.next());
        }

        GameEngine game = new GameEngine(n, playerNames, difficulty);
        game.start();
        
        scanner.close();
    }
}
