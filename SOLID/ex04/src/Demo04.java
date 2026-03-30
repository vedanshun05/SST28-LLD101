import java.util.*;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        // Prices chosen so DOUBLE + LAUNDRY + MESS = 16000 (matches README sample output)
        RoomPricer roomPricer = new MapRoomPricer(Map.of(
                LegacyRoomTypes.SINGLE, 14000.0,
                LegacyRoomTypes.DOUBLE, 12000.0,
                LegacyRoomTypes.TRIPLE, 12000.0), 16000.0);

        AddOnPricer addOnPricer = new MapAddOnPricer(Map.of(
                AddOn.MESS, 2000.0,
                AddOn.LAUNDRY, 2000.0,
                AddOn.GYM, 300.0));

        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), roomPricer, addOnPricer);
        calc.process(req);

        // Validation: different room and add-ons
        System.out.println();
        System.out.println("=== Validation ===");
        BookingRequest req2 = new BookingRequest(LegacyRoomTypes.SINGLE, List.of(AddOn.GYM));
        calc.process(req2);
    }
}
