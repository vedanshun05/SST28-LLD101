package com.example.map;

/**
 * Immutable Flyweight for map marker styles.
 */
public final class MarkerStyle {

    private final String shape;   // e.g., PIN, CIRCLE, SQUARE
    private final String color;   // e.g., RED, BLUE, GREEN
    private final int size;       // e.g., 10..20
    private final boolean filled; // filled vs outline

    public MarkerStyle(String shape, String color, int size, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.filled = filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize() { return size; }
    public boolean isFilled() { return filled; }

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
    }

    // Since we use this in a HashMap, we should ideally have hashCode/equals if we use the object itself as a key,
    // but the factory uses a String key. Still, it's good practice.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkerStyle that = (MarkerStyle) o;
        return size == that.size &&
                filled == that.filled &&
                shape.equals(that.shape) &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = shape.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + size;
        result = 31 * result + (filled ? 1 : 0);
        return result;
    }
}
