package com.example.parking;

import java.util.HashMap;
import java.util.Map;

public class ParkingSlot {
    private final String id;
    private final int floor;
    private final SlotType type;
    private boolean isOccupied;
    private final Map<String, Integer> gateDistances;

    public ParkingSlot(String id, int floor, SlotType type) {
        this.id = id;
        this.floor = floor;
        this.type = type;
        this.isOccupied = false;
        this.gateDistances = new HashMap<>();
    }

    public void addDistance(String gateId, int distance) {
        gateDistances.put(gateId, distance);
    }

    public int getDistance(String gateId) {
        return gateDistances.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    public String getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
