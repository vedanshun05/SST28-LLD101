package com.example.parking;

import java.time.LocalDateTime;

public class Bill {
    private final String id;
    private final ParkingTicket ticket;
    private final LocalDateTime exitTime;
    private final double amount;

    public Bill(String id, ParkingTicket ticket, LocalDateTime exitTime, double amount) {
        this.id = id;
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public void printBill() {
        System.out.println("--- Parking Bill ---");
        System.out.println("Bill ID: " + id);
        System.out.println("Vehicle: " + ticket.getVehicle().getRegNumber());
        System.out.println("Slot Type: " + ticket.getSlot().getType());
        System.out.println("Entry: " + ticket.getEntryTime());
        System.out.println("Exit: " + exitTime);
        System.out.println("Total Amount: $" + amount);
        System.out.println("--------------------");
    }

    public double getAmount() {
        return amount;
    }
}
