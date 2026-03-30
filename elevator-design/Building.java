import java.util.ArrayList;
import java.util.List;

public class Building {
    private final String name;
    private final int totalFloors;
    private final List<Floor> floors;

    public Building(String name, int totalFloors) {
        this.name = name;
        this.totalFloors = totalFloors;
        this.floors = new ArrayList<>();
        initializeFloors();
    }

    private void initializeFloors() {
        for (int i = 0; i < totalFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public Floor getFloor(int floorNumber) {
        if (floorNumber >= 0 && floorNumber < totalFloors) {
            return floors.get(floorNumber);
        }
        throw new IllegalArgumentException("Invalid floor number: " + floorNumber);
    }

    public String getName() {
        return name;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public List<Floor> getFloors() {
        return new ArrayList<>(floors);
    }
}
