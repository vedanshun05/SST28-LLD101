package com.example.parking;

public enum SlotType {
    SMALL(10.0), MEDIUM(20.0), LARGE(50.0); // Hourly rates

    private final double hourlyRate;

    SlotType(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
