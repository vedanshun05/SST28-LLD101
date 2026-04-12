public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.96, 77.57));

        IDistanceCalculator distanceCalculator = new DistanceCalculator();
        IDriverAllocator driverAllocator = new DriverAllocator();
        IPaymentGateway paymentGateway = new PaymentGateway();
        IPricingService pricingService = new DefaultPricingService();

        TransportBookingService svc = new TransportBookingService(
            distanceCalculator,
            driverAllocator,
            paymentGateway,
            pricingService
        );
        svc.book(req);
    }
}
