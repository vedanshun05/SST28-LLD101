package com.example.parking;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final LocalDateTime entryTime;

    public ParkingTicket(String id, Vehicle vehicle, ParkingSlot slot, LocalDateTime entryTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
