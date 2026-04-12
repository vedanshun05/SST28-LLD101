

public class HolidayPricing implements PricingStrategy{
   @Override
    public double calculatePrice(Seat seat, Show show) {
        double basePrice = getBasePrice(seat.seatType);
        return basePrice;
    }

    private double getBasePrice(SeatType type) {
        switch (type) {
            case SILVER: return 100;
            case GOLD: return 150;
            case PLATINUM: return 200;
            default: return 100;
        }
    }
}
