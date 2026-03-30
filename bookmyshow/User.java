import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final List<Booking> bookingHistory;

    public User(String userId, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookingHistory = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Booking> getBookingHistory() {
        return new ArrayList<>(bookingHistory);
    }

    @Override
    public String toString() {
        return String.format("User: %s (%s)", name, email);
    }
}
