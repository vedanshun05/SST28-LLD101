package com.example.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private final Set<String> gates;
    private int ticketCounter = 0;
    private int billCounter = 0;

    public ParkingLot() {
        this.slots = new ArrayList<>();
        this.gates = new HashSet<>();
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public void addGate(String gateId) {
        gates.add(gateId);
    }

    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime, String entryGateId) {
        if (!gates.contains(entryGateId)) {
            throw new IllegalArgumentException("Invalid Entry Gate ID: " + entryGateId);
        }

        ParkingSlot nearestSlot = findNearestCompatibleSlot(vehicle.getType(), entryGateId);
        if (nearestSlot == null) {
            System.out.println("No compatible parking slot available for " + vehicle.getType());
            return null;
        }

        nearestSlot.setOccupied(true);
        String ticketId = "TKT-" + (++ticketCounter);
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, nearestSlot, entryTime);
        System.out.println("Parked " + vehicle.getRegNumber() + " at slot " + nearestSlot.getId() + " (" + nearestSlot.getType() + ")");
        return ticket;
    }

    public double exit(ParkingTicket ticket, LocalDateTime exitTime) {
        if (ticket == null) return 0.0;
        
        ParkingSlot slot = ticket.getSlot();
        slot.setOccupied(false);

        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        if (hours == 0) hours = 1; // Minimum 1 hour charge

        double amount = hours * slot.getType().getHourlyRate();
        String billId = "BILL-" + (++billCounter);
        Bill bill = new Bill(billId, ticket, exitTime, amount);
        bill.printBill();
        return amount;
    }

    public void status() {
        Map<SlotType, Integer> availability = new EnumMap<>(SlotType.class);
        for (SlotType type : SlotType.values()) {
            availability.put(type, 0);
        }

        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                availability.put(slot.getType(), availability.get(slot.getType()) + 1);
            }
        }

        System.out.println("--- Current Availability ---");
        for (Map.Entry<SlotType, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " slots free");
        }
        System.out.println("----------------------------");
    }

    private ParkingSlot findNearestCompatibleSlot(VehicleType vType, String gateId) {
        ParkingSlot bestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && isCompatible(vType, slot.getType())) {
                int distance = slot.getDistance(gateId);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestSlot = slot;
                }
            }
        }
        return bestSlot;
    }

    private boolean isCompatible(VehicleType vType, SlotType sType) {
        switch (vType) {
            case TWO_WHEELER:
                return true; // Can park in Small, Medium, Large
            case CAR:
                return sType == SlotType.MEDIUM || sType == SlotType.LARGE;
            case BUS:
                return sType == SlotType.LARGE;
            default:
                return false;
        }
    }
}
