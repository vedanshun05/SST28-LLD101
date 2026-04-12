public class ParkingSpot implements Comparable<ParkingSpot>{
    int floor;
    int spotIndex;
    SpotType spotType;
    boolean isOccupied;

    public ParkingSpot(int f, int i, SpotType t){
        this.floor = f;
        this.spotIndex = i;
        this.spotType = t;
        this.isOccupied = false;
    }

    public void occupy(){
        isOccupied = true;
    }

    public void release(){
        isOccupied = false;
    }

    public int compareTo(ParkingSpot other){
        if(this.floor != other.floor) return this.floor - other.floor;
        return this.spotIndex - other.spotIndex;
    }
}