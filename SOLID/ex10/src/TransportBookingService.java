public class TransportBookingService {
    private final DistanceService distanceService;
    private final AllocationService allocationService;
    private final PaymentService paymentService;
    private final FareCalculator fareCalculator;

    public TransportBookingService(DistanceService distanceService, AllocationService allocationService,
            PaymentService paymentService, FareCalculator fareCalculator) {
        this.distanceService = distanceService;
        this.allocationService = allocationService;
        this.paymentService = paymentService;
        this.fareCalculator = fareCalculator;
    }

    public void book(TripRequest req) {
        double km = distanceService.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = allocationService.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = fareCalculator.calculate(km);

        String txn = paymentService.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
