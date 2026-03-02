public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));

        DistanceService dist = new DistanceCalculator();
        AllocationService alloc = new DriverAllocator();
        PaymentService pay = new PaymentGateway();
        FareCalculator fareCalc = new FareCalculator();

        TransportBookingService svc = new TransportBookingService(dist, alloc, pay, fareCalc);
        svc.book(req);
    }
}
