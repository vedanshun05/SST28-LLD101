import java.time.DayOfWeek;

public class WeekendPricing implements PricingStrategy {

  @Override
  public double calculatePrice(Seat seat, Show show) {
    double basePrice = getBasePrice(seat.seatType);

    DayOfWeek day = show.time.getDayOfWeek();

    if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
      return basePrice * 1.5; // 50% surge
    }

    return basePrice;
  }

  private double getBasePrice(SeatType type) {
    switch (type) {
      case SILVER:
        return 100;
      case GOLD:
        return 150;
      case PLATINUM:
        return 200;
      default:
        return 100;
    }
  }
}
