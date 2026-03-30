import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String screenId;
    private final String screenName;
    private final List<Seat> seats;
    private final int totalCapacity;

    public Screen(String screenId, String screenName, int totalCapacity) {
        this.screenId = screenId;
        this.screenName = screenName;
        this.totalCapacity = totalCapacity;
        this.seats = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        String[] rows = {"A", "B", "C", "D", "E"};
        int seatsPerRow = totalCapacity / rows.length;
        
        for (int r = 0; r < rows.length; r++) {
            for (int s = 1; s <= seatsPerRow; s++) {
                SeatType type;
                if (r < 2) {
                    type = SeatType.VIP;
                } else if (r < 4) {
                    type = SeatType.PREMIUM;
                } else {
                    type = SeatType.REGULAR;
                }
                
                String seatId = screenId + "-" + rows[r] + s;
                seats.add(new Seat(seatId, rows[r], s, type));
            }
        }
    }

    public String getScreenId() {
        return screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
