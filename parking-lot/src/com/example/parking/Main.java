package com.example.parking;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();
        
        // Define Gates
        lot.addGate("Gate-1");
        lot.addGate("Gate-2");

        // Define Slots (10 small, 5 medium, 2 large across 2 floors)
        for (int i = 1; i <= 5; i++) {
            ParkingSlot s = new ParkingSlot("S-" + i, 1, SlotType.SMALL);
            s.addDistance("Gate-1", i * 10);
            s.addDistance("Gate-2", 100 - i * 10);
            lot.addSlot(s);
        }
        for (int i = 1; i <= 2; i++) {
            ParkingSlot m = new ParkingSlot("M-" + i, 1, SlotType.MEDIUM);
            m.addDistance("Gate-1", 50 + i * 10);
            m.addDistance("Gate-2", 50 - i * 10);
            lot.addSlot(m);
        }
        ParkingSlot l = new ParkingSlot("L-1", 2, SlotType.LARGE);
        l.addDistance("Gate-1", 100);
        l.addDistance("Gate-2", 100);
        lot.addSlot(l);

        lot.status();

        // 1. Bike enters Gate-1 (gets small slot)
        Vehicle bike1 = new Vehicle("BK-001", VehicleType.TWO_WHEELER);
        ParkingTicket t1 = lot.park(bike1, LocalDateTime.now().minusHours(3), "Gate-1");

        // 2. Car enters Gate-2 (gets medium slot)
        Vehicle car1 = new Vehicle("CR-123", VehicleType.CAR);
        ParkingTicket t2 = lot.park(car1, LocalDateTime.now().minusHours(2), "Gate-2");

        // 3. Bus enters Gate-1 (gets large slot)
        Vehicle bus1 = new Vehicle("BS-999", VehicleType.BUS);
        ParkingTicket t3 = lot.park(bus1, LocalDateTime.now().minusHours(5), "Gate-1");

        // 4. Another Bike enters Gate-2 (Small slots further, but available)
        Vehicle bike2 = new Vehicle("BK-002", VehicleType.TWO_WHEELER);
        ParkingTicket t4 = lot.park(bike2, LocalDateTime.now().minusHours(1), "Gate-2");

        lot.status();

        // 5. Exits
        lot.exit(t1, LocalDateTime.now());
        lot.exit(t2, LocalDateTime.now());
        lot.exit(t3, LocalDateTime.now());
        lot.exit(t4, LocalDateTime.now());

        lot.status();
    }
}
