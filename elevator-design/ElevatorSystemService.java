public class ElevatorSystemService {
    private static ElevatorSystemService instance;
    private final Building building;
    private final ElevatorController controller;

    private ElevatorSystemService(String buildingName, int floors, int elevators, int elevatorCapacity) {
        this.building = new Building(buildingName, floors);
        this.controller = new ElevatorController(elevators, elevatorCapacity);
        System.out.println(String.format("\n╔══════════════════════════════════════════╗"));
        System.out.println(String.format("║  Elevator System Initialized"));
        System.out.println(String.format("║  Building: %s", buildingName));
        System.out.println(String.format("║  Floors: %d", floors));
        System.out.println(String.format("║  Elevators: %d (Capacity: %d each)", elevators, elevatorCapacity));
        System.out.println(String.format("╚══════════════════════════════════════════╝\n"));
    }

    public static synchronized ElevatorSystemService getInstance(String buildingName, int floors, 
                                                                   int elevators, int elevatorCapacity) {
        if (instance == null) {
            instance = new ElevatorSystemService(buildingName, floors, elevators, elevatorCapacity);
        }
        return instance;
    }

    public static ElevatorSystemService getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ElevatorSystemService not initialized. Call getInstance with parameters first.");
        }
        return instance;
    }

    public void requestElevator(int sourceFloor, int destinationFloor, int passengers) {
        validateFloors(sourceFloor, destinationFloor);
        ElevatorRequest request = new ElevatorRequest(sourceFloor, destinationFloor, passengers);
        controller.submitRequest(request);
    }

    public void requestElevator(int sourceFloor, int destinationFloor, int passengers, RequestPriority priority) {
        validateFloors(sourceFloor, destinationFloor);
        ElevatorRequest request = new ElevatorRequest(sourceFloor, destinationFloor, passengers, priority);
        controller.submitRequest(request);
    }

    private void validateFloors(int sourceFloor, int destinationFloor) {
        if (sourceFloor < 0 || sourceFloor >= building.getTotalFloors()) {
            throw new IllegalArgumentException("Invalid source floor: " + sourceFloor);
        }
        if (destinationFloor < 0 || destinationFloor >= building.getTotalFloors()) {
            throw new IllegalArgumentException("Invalid destination floor: " + destinationFloor);
        }
        if (sourceFloor == destinationFloor) {
            throw new IllegalArgumentException("Source and destination floors cannot be the same");
        }
    }

    public void displaySystemStatus() {
        controller.displayStatus();
    }

    public Building getBuilding() {
        return building;
    }

    public ElevatorController getController() {
        return controller;
    }
}
