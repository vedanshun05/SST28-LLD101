import java.util.*;

public class ParkingLot {

    Map<Integer, List<ParkingSpot>> floors;
    Map<Integer, EntryGate> entryGates;
    Map<SpotType, Integer> slotCounts;

    SpotAllocationStrategy allocationStrategy;
    BillingStrategy billingStrategy;

    public ParkingLot(Map<Integer, List<ParkingSpot>> floors,
                      Map<Integer, EntryGate> gates,
                      Map<SpotType, Integer> counts,
                      SpotAllocationStrategy alloc,
                      BillingStrategy bill){
        this.floors = floors;
        this.entryGates = gates;
        this.slotCounts = counts;
        this.allocationStrategy = alloc;
        this.billingStrategy = bill;
    }

    public ParkingTicket generateTicket(Vehicle v, int inTime, SpotType type, int gateId){
        EntryGate gate = entryGates.get(gateId);
        ParkingSpot spot = allocationStrategy.allocate(gate, type);


        spot.occupy();
        slotCounts.put(type, slotCounts.get(type) - 1);

        return new ParkingTicket(UUID.randomUUID().toString(), v, spot, inTime, gateId);
    }

    public Bill generateBill(ParkingTicket ticket, int outTime){
        int duration = outTime - ticket.inTime;

        ticket.assignedSpot.release();
        slotCounts.put(ticket.assignedSpot.spotType,
            slotCounts.get(ticket.assignedSpot.spotType) + 1);

        return billingStrategy.calculate(ticket.assignedSpot.spotType, duration);
    }
}