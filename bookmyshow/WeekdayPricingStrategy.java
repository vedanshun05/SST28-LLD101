public class WeekdayPricingStrategy implements PricingStrategy {
    
    @Override
    public double calculatePrice(double basePrice, ShowTiming timing) {
        double multiplier = 1.0;
        
        switch (timing) {
            case MORNING:
                multiplier = 0.8;
                break;
            case AFTERNOON:
                multiplier = 0.9;
                break;
            case EVENING:
                multiplier = 1.0;
                break;
            case NIGHT:
                multiplier = 1.1;
                break;
        }
        
        return basePrice * multiplier;
    }
}
