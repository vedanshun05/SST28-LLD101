package com.example.metrics;

public class App {
    public static void main(String[] args) {
        MetricsRegistry r1 = MetricsRegistry.getInstance();
        r1.increment("requests");
        r1.increment("requests");
        r1.increment("db_errors");

        MetricsRegistry r2 = MetricsRegistry.getInstance();
        System.out.println("r1 == r2? " + (r1 == r2)); // true
        System.out.println("requests: " + r2.getCount("requests")); // 2
        System.out.println("db_errors: " + r2.getCount("db_errors")); // 1
        System.out.println("All counters: " + r2.getAll());
    }
}
