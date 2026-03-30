public class Seat {
    private final String seatId;
    private final String rowNumber;
    private final int seatNumber;
    private final SeatType seatType;

    public Seat(String seatId, String rowNumber, int seatNumber, SeatType seatType) {
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    @Override
    public String toString() {
        return rowNumber + seatNumber + " (" + seatType + ")";
    }
}
