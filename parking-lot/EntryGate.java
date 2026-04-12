import java.util.*;

public class EntryGate {
    int id;
    int floor;
    TreeMap<ParkingSpot, Integer> spotDists;

    public EntryGate(int id, int floor){
        this.id = id;
        this.floor = floor;
        this.spotDists = new TreeMap<>();
    }

    public void addSpotDistance(ParkingSpot spot, int dist){
        spotDists.put(spot, dist);
    }
}