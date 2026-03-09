package com.example.metrics;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetricsRegistry implements Serializable {
    private static final long serialVersionUID = 1L;

    // Use volatile to ensure visibility across threads
    private static volatile MetricsRegistry instance;

    private final Map<String, Long> counters = new ConcurrentHashMap<>();

    // FIX 1: Private constructor prevents external instantiation
    private MetricsRegistry() {
        // FIX 2: Guard against reflection-based attacks
        if (instance != null) {
            throw new IllegalStateException(
                    "Singleton instance already exists! Use getInstance() instead.");
        }
        System.out.println("Creating new MetricsRegistry...");
    }

    // FIX 3: Thread-safe double-checked locking with volatile
    public static MetricsRegistry getInstance() {
        if (instance == null) {
            synchronized (MetricsRegistry.class) {
                if (instance == null) {
                    instance = new MetricsRegistry();
                }
            }
        }
        return instance;
    }

    public void increment(String key) {
        counters.merge(key, 1L, Long::sum);
    }

    public void setCount(String key, long value) {
        counters.put(key, value);
    }

    public long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public Map<String, Long> getAll() {
        return Map.copyOf(counters);
    }

    // FIX 4: Protect against deserialization creating a new instance
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    public String toString() {
        return "MetricsRegistry" + counters;
    }
}
