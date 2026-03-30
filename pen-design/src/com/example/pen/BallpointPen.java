package com.example.pen;

public class BallpointPen extends AbstractPen {
    public BallpointPen(Color color, boolean hasCap) {
        super(PenType.BALLPOINT, color, hasCap);
    }

    @Override
    public void write(String text) {
        if (!isStarted) {
            throw new RuntimeException("Pen is not started. Open the cap or click it first.");
        }
        if (inkCartridge.isEmpty()) {
            throw new RuntimeException("Ink cartridge is empty. Refill it first.");
        }
        System.out.println("Writing with ballpoint pen: " + text + " in " + inkCartridge.getColor() + " color.");
        inkCartridge.reduceInk(text.length());
    }
}
