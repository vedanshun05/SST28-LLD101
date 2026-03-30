public class ElevatorRequest implements Comparable<ElevatorRequest> {
    private final int sourceFloor;
    private final int destinationFloor;
    private final Direction direction;
    private final RequestPriority priority;
    private final long timestamp;
    private final int passengersCount;

    public ElevatorRequest(int sourceFloor, int destinationFloor, int passengersCount) {
        this(sourceFloor, destinationFloor, passengersCount, RequestPriority.NORMAL);
    }

    public ElevatorRequest(int sourceFloor, int destinationFloor, int passengersCount, RequestPriority priority) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.passengersCount = passengersCount;
        this.priority = priority;
        this.timestamp = System.currentTimeMillis();
        this.direction = destinationFloor > sourceFloor ? Direction.UP : Direction.DOWN;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestPriority getPriority() {
        return priority;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    @Override
    public int compareTo(ElevatorRequest other) {
        int priorityCompare = Integer.compare(other.priority.getValue(), this.priority.getValue());
        if (priorityCompare != 0) {
            return priorityCompare;
        }
        return Long.compare(this.timestamp, other.timestamp);
    }

    @Override
    public String toString() {
        return String.format("Request[%d→%d, %s, Priority: %s, Passengers: %d]", 
            sourceFloor, destinationFloor, direction, priority, passengersCount);
    }
}
