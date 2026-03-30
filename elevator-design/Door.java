public class Door {
    private DoorState state;
    private static final int DOOR_OPERATION_TIME_MS = 2000;

    public Door() {
        this.state = DoorState.CLOSED;
    }

    public void open() {
        if (state == DoorState.CLOSED || state == DoorState.CLOSING) {
            state = DoorState.OPENING;
            System.out.println("  [DOOR] Opening...");
            simulateOperation();
            state = DoorState.OPEN;
            System.out.println("  [DOOR] Opened");
        }
    }

    public void close() {
        if (state == DoorState.OPEN || state == DoorState.OPENING) {
            state = DoorState.CLOSING;
            System.out.println("  [DOOR] Closing...");
            simulateOperation();
            state = DoorState.CLOSED;
            System.out.println("  [DOOR] Closed");
        }
    }

    public boolean isOpen() {
        return state == DoorState.OPEN;
    }

    public boolean isClosed() {
        return state == DoorState.CLOSED;
    }

    public DoorState getState() {
        return state;
    }

    private void simulateOperation() {
        try {
            Thread.sleep(DOOR_OPERATION_TIME_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
