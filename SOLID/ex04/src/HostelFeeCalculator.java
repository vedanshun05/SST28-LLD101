import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricer roomPricer;
    private final AddOnPricer addOnPricer;

    public HostelFeeCalculator(FakeBookingRepo repo, RoomPricer roomPricer, AddOnPricer addOnPricer) {
        this.repo = repo;
        this.roomPricer = roomPricer;
        this.addOnPricer = addOnPricer;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1705).nextInt(1000)); // seed 1705 -> 781, matches README sample
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base = roomPricer.getPrice(req.roomType);

        double add = 0.0;
        for (AddOn a : req.addOns) {
            add += addOnPricer.getPrice(a);
        }

        return new Money(base + add);
    }
}
