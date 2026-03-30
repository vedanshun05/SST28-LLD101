package com.example.parking;

public class Vehicle {
    private final String regNumber;
    private final VehicleType type;

    public Vehicle(String regNumber, VehicleType type) {
        this.regNumber = regNumber;
        this.type = type;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public VehicleType getType() {
        return type;
    }
}
