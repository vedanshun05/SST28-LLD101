public interface SpotAllocationStrategy {
    ParkingSpot allocate(EntryGate gate, SpotType type);
}