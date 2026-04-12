public class ParkingTicket {
    String ticketId;
    Vehicle vehicle;
    ParkingSpot assignedSpot;
    int inTime;
    int entryGateId;

    public ParkingTicket(String id, Vehicle v, ParkingSpot s, int t, int gateId){
        this.ticketId = id;
        this.vehicle = v;
        this.assignedSpot = s;
        this.inTime = t;
        this.entryGateId = gateId;
    }
}