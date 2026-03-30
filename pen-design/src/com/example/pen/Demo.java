package com.example.pen;

public class Demo {
    public static void main(String[] args) {
        // Create a ballpoint pen
        Pen ballpoint = PenFactory.createPen(PenType.BALLPOINT, Color.BLUE, true);
        ballpoint.start();
        ballpoint.write("Hello, World!");
        ballpoint.close();

        System.out.println("---");

        // Create a fountain pen
        Pen fountain = PenFactory.createPen(PenType.FOUNTAIN, Color.BLACK, true);
        fountain.start();
        fountain.write("Writing with a fountain pen.");
        fountain.refill(Color.RED);
        fountain.write("Now writing in red.");
        fountain.close();

        System.out.println("---");

        // Create a gel pen
        Pen gel = PenFactory.createPen(PenType.GEL, Color.GREEN, false);
        gel.start();
        gel.write("Gel pen writing...");
        gel.close();

        System.out.println("---");

        // Test exception (writing without starting)
        try {
            Pen testPen = PenFactory.createPen(PenType.BALLPOINT, Color.BLUE, true);
            testPen.write("This should fail.");
        } catch (RuntimeException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Test ink exhaustion (simulated)
        Pen thirstyPen = PenFactory.createPen(PenType.FOUNTAIN, Color.BLUE, true);
        thirstyPen.start();
        try {
            // Write a lot to consume ink
            thirstyPen.write("a".repeat(1000));
            thirstyPen.write("Trying to write more...");
        } catch (RuntimeException e) {
            System.out.println("Caught expected ink exception: " + e.getMessage());
        }
    }
}
