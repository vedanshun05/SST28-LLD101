import java.util.*;

public class ParkingLotFactory {

    public static ParkingLot create(){
        Map<Integer, List<ParkingSpot>> floors = new HashMap<>();
        Map<Integer, EntryGate> gates = new HashMap<>();
        Map<SpotType, Integer> counts = new HashMap<>();

        List<ParkingSpot> spots = new ArrayList<>();

        for(int i=0;i<5;i++) spots.add(new ParkingSpot(1,i,SpotType.SMALL));
        for(int i=5;i<10;i++) spots.add(new ParkingSpot(1,i,SpotType.MEDIUM));
        for(int i=10;i<15;i++) spots.add(new ParkingSpot(1,i,SpotType.LARGE));

        floors.put(1, spots);

        EntryGate gate = new EntryGate(1,1);
        for(int i=0;i<spots.size();i++){
            gate.addSpotDistance(spots.get(i), i);
        }
        gates.put(1, gate);

        counts.put(SpotType.SMALL,5);
        counts.put(SpotType.MEDIUM,5);
        counts.put(SpotType.LARGE,5);

        return new ParkingLot(
            floors,
            gates,
            counts,
            new NearestSpotAllocation(),
            new HourlyBillingStrategy()
        );
    }
}