package com.example.pen;

public class FountainPen extends AbstractPen {
    public FountainPen(Color color, boolean hasCap) {
        super(PenType.FOUNTAIN, color, hasCap);
    }

    @Override
    public void write(String text) {
        if (!isStarted) {
            throw new RuntimeException("Pen is not started. Open the cap or click it first.");
        }
        if (inkCartridge.isEmpty()) {
            throw new RuntimeException("Ink cartridge is empty. Refill it first.");
        }
        System.out.println("Writing with fountain pen: " + text + " in " + inkCartridge.getColor() + " color.");
        // Fountain pens might consume ink faster
        inkCartridge.reduceInk(text.length() * 2);
    }
}
