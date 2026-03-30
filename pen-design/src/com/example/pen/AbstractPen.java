package com.example.pen;

public abstract class AbstractPen implements Pen {
    protected InkCartridge inkCartridge;
    protected boolean isStarted;
    protected PenType type;
    protected boolean hasCap;

    public AbstractPen(PenType type, Color color, boolean hasCap) {
        this.type = type;
        this.inkCartridge = new InkCartridge(color);
        this.hasCap = hasCap;
        this.isStarted = false;
    }

    @Override
    public void start() {
        if (!isStarted) {
            System.out.println("Opening the " + (hasCap ? "cap" : "clicker") + " of the " + type + " pen.");
            isStarted = true;
        }
    }

    @Override
    public void close() {
        if (isStarted) {
            System.out.println("Closing the " + (hasCap ? "cap" : "clicker") + " of the " + type + " pen.");
            isStarted = false;
        }
    }

    @Override
    public void refill(Color color) {
        System.out.println("Refilling the " + type + " pen with " + color + " ink.");
        inkCartridge.refill(color);
    }

    @Override
    public abstract void write(String text);

    public PenType getType() {
        return type;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public InkCartridge getInkCartridge() {
        return inkCartridge;
    }
}
