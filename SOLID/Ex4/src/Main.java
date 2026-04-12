import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, RoomPricing> rooms = new HashMap<>();
        rooms.put(LegacyRoomTypes.SINGLE, new SingleRoom());
        rooms.put(LegacyRoomTypes.DOUBLE, new DoubleRoom());
        rooms.put(LegacyRoomTypes.TRIPLE, new TripleRoom());

        Map<AddOn, AddOnPricing> addOns = new HashMap<>();
        addOns.put(AddOn.MESS, new MessAddOnPricing());
        addOns.put(AddOn.LAUNDRY, new LaundryAddOnPricing());
        addOns.put(AddOn.GYM, new GymAddOnPricing());

        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), rooms, addOns);
        calc.process(req);
    }
}
