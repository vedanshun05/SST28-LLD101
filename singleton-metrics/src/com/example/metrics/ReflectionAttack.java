package com.example.metrics;

import java.lang.reflect.Constructor;

public class ReflectionAttack {

    public static void main(String[] args) throws Exception {
        MetricsRegistry first = MetricsRegistry.getInstance();
        System.out.println("First instance: " + System.identityHashCode(first));

        Constructor<MetricsRegistry> ctor = MetricsRegistry.class.getDeclaredConstructor();
        ctor.setAccessible(true);

        try {
            MetricsRegistry second = ctor.newInstance();
            System.out.println("FAIL: A second instance was created: " + System.identityHashCode(second));
        } catch (Exception e) {
            System.out.println("PASS: Reflection attack blocked — " + e.getCause().getMessage());
        }
    }
}
