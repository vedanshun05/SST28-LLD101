package com.example.snakeladder;

public class Player {
    private final String id;
    private final String name;
    private int position;
    private boolean hasWon;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0;
        this.hasWon = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
