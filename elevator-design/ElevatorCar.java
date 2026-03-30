import java.util.TreeSet;

public class ElevatorCar {
    private final int id;
    private int currentFloor;
    private ElevatorStatus status;
    private Direction currentDirection;
    private final Door door;
    private final int maxCapacity;
    private int currentPassengers;
    private final TreeSet<Integer> upDestinations;
    private final TreeSet<Integer> downDestinations;
    private static final int FLOOR_TRAVEL_TIME_MS = 1000;

    public ElevatorCar(int id, int maxCapacity) {
        this.id = id;
        this.currentFloor = 0;
        this.status = ElevatorStatus.IDLE;
        this.currentDirection = Direction.IDLE;
        this.door = new Door();
        this.maxCapacity = maxCapacity;
        this.currentPassengers = 0;
        this.upDestinations = new TreeSet<>();
        this.downDestinations = new TreeSet<>();
    }

    public void addDestination(int floor, Direction direction) {
        if (direction == Direction.UP) {
            upDestinations.add(floor);
        } else {
            downDestinations.add(floor);
        }
    }

    public void moveToFloor(int targetFloor) {
        if (targetFloor == currentFloor) {
            return;
        }

        Direction moveDirection = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;
        status = moveDirection == Direction.UP ? ElevatorStatus.MOVING_UP : ElevatorStatus.MOVING_DOWN;
        currentDirection = moveDirection;

        System.out.println(String.format("Elevator-%d: Moving %s from floor %d to floor %d", 
            id, moveDirection, currentFloor, targetFloor));

        while (currentFloor != targetFloor) {
            if (moveDirection == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            
            try {
                Thread.sleep(FLOOR_TRAVEL_TIME_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(String.format("Elevator-%d: Currently at floor %d", id, currentFloor));
        }
    }

    public void stop() {
        status = ElevatorStatus.STOPPED;
        currentDirection = Direction.IDLE;
        System.out.println(String.format("Elevator-%d: Stopped at floor %d", id, currentFloor));
    }

    public void openDoor() {
        status = ElevatorStatus.DOOR_OPENING;
        door.open();
    }

    public void closeDoor() {
        status = ElevatorStatus.DOOR_CLOSING;
        door.close();
    }

    public boolean canAccommodate(int passengers) {
        return (currentPassengers + passengers) <= maxCapacity;
    }

    public void addPassengers(int count) {
        if (canAccommodate(count)) {
            currentPassengers += count;
            System.out.println(String.format("Elevator-%d: %d passengers boarded (Total: %d/%d)", 
                id, count, currentPassengers, maxCapacity));
        }
    }

    public void removePassengers(int count) {
        currentPassengers = Math.max(0, currentPassengers - count);
        System.out.println(String.format("Elevator-%d: %d passengers exited (Remaining: %d/%d)", 
            id, count, currentPassengers, maxCapacity));
    }

    public Integer getNextDestination() {
        if (currentDirection == Direction.UP && !upDestinations.isEmpty()) {
            Integer next = upDestinations.first();
            if (next != null && next >= currentFloor) {
                return upDestinations.pollFirst();
            }
        } else if (currentDirection == Direction.DOWN && !downDestinations.isEmpty()) {
            Integer next = downDestinations.last();
            if (next != null && next <= currentFloor) {
                return downDestinations.pollLast();
            }
        }

        if (!upDestinations.isEmpty()) {
            currentDirection = Direction.UP;
            return upDestinations.pollFirst();
        } else if (!downDestinations.isEmpty()) {
            currentDirection = Direction.DOWN;
            return downDestinations.pollLast();
        }

        status = ElevatorStatus.IDLE;
        currentDirection = Direction.IDLE;
        return null;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public int getAvailableCapacity() {
        return maxCapacity - currentPassengers;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Elevator-%d [Floor: %d, Status: %s, Direction: %s, Passengers: %d/%d]",
            id, currentFloor, status, currentDirection, currentPassengers, maxCapacity);
    }
}
