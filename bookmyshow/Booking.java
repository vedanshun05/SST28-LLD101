import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<ShowSeat> seats;
    private BookingStatus status;
    private final LocalDateTime bookingTime;
    private double totalAmount;
    private double discountAmount;
    private double finalAmount;

    public Booking(User user, Show show, List<ShowSeat> seats) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.status = BookingStatus.PENDING;
        this.bookingTime = LocalDateTime.now();
        calculateAmount();
    }

    private void calculateAmount() {
        totalAmount = 0;
        for (ShowSeat seat : seats) {
            totalAmount += seat.getPrice();
        }
        finalAmount = totalAmount;
    }

    public void applyDiscount(double discount) {
        this.discountAmount = discount;
        this.finalAmount = totalAmount - discount;
    }

    public void confirm() {
        status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        status = BookingStatus.CANCELLED;
        for (ShowSeat seat : seats) {
            seat.cancelBooking();
        }
    }

    public void expire() {
        status = BookingStatus.EXPIRED;
        for (ShowSeat seat : seats) {
            seat.unlockSeat();
        }
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        return String.format("Booking #%s - %s - %d seats - ₹%.2f [%s]", 
            bookingId.substring(0, 8), show.getMovie().getTitle(), 
            seats.size(), finalAmount, status);
    }
}
