public class WeekendPricingStrategy implements PricingStrategy {
    
    @Override
    public double calculatePrice(double basePrice, ShowTiming timing) {
        double multiplier = 1.2;
        
        switch (timing) {
            case MORNING:
                multiplier = 1.1;
                break;
            case AFTERNOON:
                multiplier = 1.2;
                break;
            case EVENING:
                multiplier = 1.3;
                break;
            case NIGHT:
                multiplier = 1.4;
                break;
        }
        
        return basePrice * multiplier;
    }
}
