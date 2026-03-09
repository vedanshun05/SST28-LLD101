package com.example.metrics;

import java.io.*;

public class SerializationCheck {

    public static void main(String[] args) throws Exception {
        MetricsRegistry original = MetricsRegistry.getInstance();
        original.increment("requests");
        System.out.println("Original instance: " + System.identityHashCode(original));

        // Serialize to bytes
        byte[] bytes;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
            bytes = bos.toByteArray();
        }

        // Deserialize from bytes
        MetricsRegistry deserialized;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (MetricsRegistry) ois.readObject();
        }

        System.out.println("Deserialized instance: " + System.identityHashCode(deserialized));
        System.out.println("Same instance? " + (original == deserialized)); // must be true
        System.out.println("requests counter preserved: " + deserialized.getCount("requests")); // 1
    }
}
