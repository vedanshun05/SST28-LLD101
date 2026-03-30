package com.example.pen;

public class PenFactory {
    public static Pen createPen(PenType type, Color color, boolean hasCap) {
        switch (type) {
            case BALLPOINT:
                return new BallpointPen(color, hasCap);
            case FOUNTAIN:
                return new FountainPen(color, hasCap);
            case GEL:
                return new GelPen(color, hasCap);
            default:
                throw new IllegalArgumentException("Unknown pen type: " + type);
        }
    }
}
