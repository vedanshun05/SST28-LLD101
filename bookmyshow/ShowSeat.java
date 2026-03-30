public class ShowSeat {
    private final Seat seat;
    private boolean isBooked;
    private boolean isLocked;
    private long lockExpiryTime;
    private double price;

    public ShowSeat(Seat seat, double price) {
        this.seat = seat;
        this.price = price;
        this.isBooked = false;
        this.isLocked = false;
        this.lockExpiryTime = 0;
    }

    public boolean lockSeat(long lockDurationMs) {
        if (isBooked || (isLocked && System.currentTimeMillis() < lockExpiryTime)) {
            return false;
        }
        isLocked = true;
        lockExpiryTime = System.currentTimeMillis() + lockDurationMs;
        return true;
    }

    public void unlockSeat() {
        isLocked = false;
        lockExpiryTime = 0;
    }

    public boolean bookSeat() {
        if (isBooked) {
            return false;
        }
        if (isLocked && System.currentTimeMillis() >= lockExpiryTime) {
            unlockSeat();
        }
        isBooked = true;
        isLocked = false;
        return true;
    }

    public void cancelBooking() {
        isBooked = false;
        isLocked = false;
    }

    public boolean isAvailable() {
        if (isBooked) {
            return false;
        }
        if (isLocked && System.currentTimeMillis() < lockExpiryTime) {
            return false;
        }
        if (isLocked && System.currentTimeMillis() >= lockExpiryTime) {
            unlockSeat();
        }
        return true;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }
}
