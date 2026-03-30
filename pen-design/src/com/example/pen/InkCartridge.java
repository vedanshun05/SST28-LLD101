package com.example.pen;

public class InkCartridge {
    private Color color;
    private double inkLevel;

    public InkCartridge(Color color) {
        this.color = color;
        this.inkLevel = 100.0; // Initial ink level
    }

    public void reduceInk(int characters) {
        // Reducing ink by 0.1% per character
        inkLevel -= characters * 0.1;
        if (inkLevel < 0) {
            inkLevel = 0;
        }
    }

    public boolean isEmpty() {
        return inkLevel <= 0;
    }

    public void refill(Color color) {
        this.color = color;
        this.inkLevel = 100.0;
    }

    public Color getColor() {
        return color;
    }

    public double getInkLevel() {
        return inkLevel;
    }
}
