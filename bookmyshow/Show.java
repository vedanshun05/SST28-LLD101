import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private final String showId;
    private final Movie movie;
    private final Theater theater;
    private final Screen screen;
    private final LocalDateTime showTime;
    private final ShowTiming timing;
    private final List<ShowSeat> showSeats;

    public Show(String showId, Movie movie, Theater theater, Screen screen, 
                LocalDateTime showTime, ShowTiming timing, double basePrice) {
        this.showId = showId;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.showTime = showTime;
        this.timing = timing;
        this.showSeats = new ArrayList<>();
        initializeShowSeats(basePrice);
    }

    private void initializeShowSeats(double basePrice) {
        for (Seat seat : screen.getSeats()) {
            double price = calculateSeatPrice(seat.getSeatType(), basePrice);
            showSeats.add(new ShowSeat(seat, price));
        }
    }

    private double calculateSeatPrice(SeatType seatType, double basePrice) {
        double multiplier = 1.0;
        switch (seatType) {
            case VIP:
                multiplier = 2.0;
                break;
            case PREMIUM:
                multiplier = 1.5;
                break;
            case REGULAR:
                multiplier = 1.0;
                break;
        }
        return basePrice * multiplier;
    }

    public List<ShowSeat> getAvailableSeats() {
        List<ShowSeat> available = new ArrayList<>();
        for (ShowSeat showSeat : showSeats) {
            if (showSeat.isAvailable()) {
                available.add(showSeat);
            }
        }
        return available;
    }

    public ShowSeat getSeatById(String seatId) {
        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getSeat().getSeatId().equals(seatId)) {
                return showSeat;
            }
        }
        return null;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public ShowTiming getTiming() {
        return timing;
    }

    public List<ShowSeat> getShowSeats() {
        return new ArrayList<>(showSeats);
    }

    @Override
    public String toString() {
        return String.format("Show: %s at %s - %s, Screen: %s, Time: %s", 
            movie.getTitle(), theater.getName(), theater.getLocation(), 
            screen.getScreenName(), showTime);
    }
}
