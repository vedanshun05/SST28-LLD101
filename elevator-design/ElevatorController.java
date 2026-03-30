import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorController {
    private final List<ElevatorCar> elevators;
    private final Queue<ElevatorRequest> requestQueue;
    private final ElevatorSelectionStrategy strategy;
    private final Lock lock;

    public ElevatorController(int numberOfElevators, int elevatorCapacity) {
        this.elevators = new ArrayList<>();
        this.requestQueue = new PriorityQueue<>();
        this.strategy = new OptimalLoadStrategy();
        this.lock = new ReentrantLock();
        
        initializeElevators(numberOfElevators, elevatorCapacity);
    }

    private void initializeElevators(int count, int capacity) {
        for (int i = 1; i <= count; i++) {
            elevators.add(new ElevatorCar(i, capacity));
        }
        System.out.println(String.format("Initialized %d elevators with capacity %d each", count, capacity));
    }

    public void submitRequest(ElevatorRequest request) {
        lock.lock();
        try {
            System.out.println("\n=== New Request Submitted ===");
            System.out.println(request);
            requestQueue.offer(request);
            processNextRequest();
        } finally {
            lock.unlock();
        }
    }

    private void processNextRequest() {
        if (requestQueue.isEmpty()) {
            return;
        }

        ElevatorRequest request = requestQueue.poll();
        ElevatorCar selectedElevator = strategy.selectElevator(elevators, request);

        if (selectedElevator != null) {
            assignRequestToElevator(selectedElevator, request);
        } else {
            System.out.println("No available elevator found. Request queued.");
            requestQueue.offer(request);
        }
    }

    private void assignRequestToElevator(ElevatorCar elevator, ElevatorRequest request) {
        System.out.println(String.format("\n>>> Assigned to %s", elevator));
        
        elevator.addDestination(request.getSourceFloor(), Direction.UP);
        elevator.moveToFloor(request.getSourceFloor());
        
        elevator.stop();
        elevator.openDoor();
        
        elevator.addPassengers(request.getPassengersCount());
        
        elevator.closeDoor();
        
        elevator.addDestination(request.getDestinationFloor(), request.getDirection());
        elevator.moveToFloor(request.getDestinationFloor());
        
        elevator.stop();
        elevator.openDoor();
        
        elevator.removePassengers(request.getPassengersCount());
        
        elevator.closeDoor();
        
        Integer nextDestination = elevator.getNextDestination();
        if (nextDestination != null) {
            elevator.moveToFloor(nextDestination);
        } else {
            elevator.setStatus(ElevatorStatus.IDLE);
            System.out.println(String.format("Elevator-%d: Now IDLE", elevator.getId()));
        }
    }

    public List<ElevatorCar> getElevators() {
        return new ArrayList<>(elevators);
    }

    public void displayStatus() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ELEVATOR SYSTEM STATUS");
        System.out.println("=".repeat(60));
        for (ElevatorCar elevator : elevators) {
            System.out.println(elevator);
        }
        System.out.println("Pending Requests: " + requestQueue.size());
        System.out.println("=".repeat(60));
    }
}
