import java.util.*;

public class NearestSpotAllocation implements SpotAllocationStrategy {

    public ParkingSpot allocate(EntryGate gate, SpotType type){
        for(ParkingSpot spot : gate.spotDists.keySet()){
            if(!spot.isOccupied && spot.spotType == type){
                return spot;
            }
        }
        throw new RuntimeException("No spot available");
    }
}