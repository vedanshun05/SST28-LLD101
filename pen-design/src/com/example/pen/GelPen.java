package com.example.pen;

public class GelPen extends AbstractPen {
    public GelPen(Color color, boolean hasCap) {
        super(PenType.GEL, color, hasCap);
    }

    @Override
    public void write(String text) {
        if (!isStarted) {
            throw new RuntimeException("Pen is not started. Open the cap or click it first.");
        }
        if (inkCartridge.isEmpty()) {
            throw new RuntimeException("Ink cartridge is empty. Refill it first.");
        }
        System.out.println("Writing with gel pen: " + text + " in " + inkCartridge.getColor() + " color.");
        // Gel pens consume ink moderately
        inkCartridge.reduceInk((int)(text.length() * 1.5));
    }
}
