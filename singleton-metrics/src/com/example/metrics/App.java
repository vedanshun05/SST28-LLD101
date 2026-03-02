package com.example.metrics;

public class App {
    public static void main(String[] args) {
        MetricsRegistry r1 = MetricsRegistry.getInstance();
        r1.setCount("requests", 100);

        MetricsRegistry r2 = MetricsRegistry.getInstance();
        System.out.println("r1 == r2? " + (r1 == r2)); // true
        System.out.println("r2 requests: " + r2.getCount("requests")); // 100

        // FIX: Cannot use new MetricsRegistry() anymore — constructor is private
        // Use getInstance() which always returns the same singleton
        MetricsRegistry r3 = MetricsRegistry.getInstance();
        System.out.println("r1 == r3? " + (r1 == r3)); // true
    }
}
