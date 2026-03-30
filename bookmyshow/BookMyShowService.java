import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMyShowService {
    private final Map<String, Theater> theaters;
    private final Map<String, Show> shows;
    private final Map<String, User> users;
    private final Map<String, Booking> bookings;
    private static final long SEAT_LOCK_DURATION_MS = 10 * 60 * 1000;

    public BookMyShowService() {
        this.theaters = new HashMap<>();
        this.shows = new HashMap<>();
        this.users = new HashMap<>();
        this.bookings = new HashMap<>();
    }

    public void addTheater(Theater theater) {
        theaters.put(theater.getTheaterId(), theater);
    }

    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
    }

    public List<Show> searchShows(String movieTitle, String location) {
        List<Show> matchingShows = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getMovie().getTitle().equalsIgnoreCase(movieTitle) &&
                show.getTheater().getLocation().equalsIgnoreCase(location)) {
                matchingShows.add(show);
            }
        }
        return matchingShows;
    }

    public Booking createBooking(User user, Show show, List<String> seatIds, Coupon coupon) {
        List<ShowSeat> selectedSeats = new ArrayList<>();
        
        for (String seatId : seatIds) {
            ShowSeat showSeat = show.getSeatById(seatId);
            if (showSeat == null || !showSeat.lockSeat(SEAT_LOCK_DURATION_MS)) {
                System.out.println("Failed to lock seat: " + seatId);
                unlockSeats(selectedSeats);
                return null;
            }
            selectedSeats.add(showSeat);
        }

        Booking booking = new Booking(user, show, selectedSeats);
        
        if (coupon != null) {
            double discount = coupon.applyDiscount(booking.getTotalAmount());
            if (discount > 0) {
                booking.applyDiscount(discount);
                System.out.println(String.format("Coupon applied! Discount: ₹%.2f", discount));
            }
        }

        bookings.put(booking.getBookingId(), booking);
        return booking;
    }

    public boolean confirmBooking(String bookingId, PaymentMethod paymentMethod) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || booking.getStatus() != BookingStatus.PENDING) {
            return false;
        }

        if (!paymentMethod.processPayment(booking.getFinalAmount())) {
            booking.cancel();
            return false;
        }

        for (ShowSeat seat : booking.getSeats()) {
            if (!seat.bookSeat()) {
                booking.cancel();
                return false;
            }
        }

        booking.confirm();
        booking.getUser().addBooking(booking);
        System.out.println("\n✅ Booking confirmed successfully!");
        return true;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancel();
            System.out.println("Booking cancelled: " + bookingId);
        }
    }

    private void unlockSeats(List<ShowSeat> seats) {
        for (ShowSeat seat : seats) {
            seat.unlockSeat();
        }
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public List<Theater> getAllTheaters() {
        return new ArrayList<>(theaters.values());
    }

    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }
}
