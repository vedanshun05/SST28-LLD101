package com.example.snakeladder;

import java.util.Random;

public class Dice {
    private final int numberOfSides;
    private final Random random;

    public Dice(int numberOfSides) {
        this.numberOfSides = numberOfSides;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(numberOfSides) + 1;
    }
}
