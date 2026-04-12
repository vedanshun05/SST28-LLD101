import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final Map<Integer,RoomPricing> roomRegistry;
    private final Map<AddOn, AddOnPricing> addOnRegistry;

    public HostelFeeCalculator(FakeBookingRepo repo, Map<Integer,RoomPricing> rooms, Map<AddOn, AddOnPricing> addOns) {
        this.repo = repo;
        this.roomRegistry = rooms;
        this.addOnRegistry = addOns;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); 
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base = roomRegistry.getOrDefault(req.roomType, () -> 16000.0).getBasePrice();

        double add = 0.0;
        for (AddOn a : req.addOns) {
            if (addOnRegistry.containsKey(a)) add += addOnRegistry.get(a).getPrice();
        }

        return new Money(base + add);
    }
}
