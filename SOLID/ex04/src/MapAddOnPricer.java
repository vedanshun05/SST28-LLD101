import java.util.Map;

public class MapAddOnPricer implements AddOnPricer {
    private final Map<AddOn, Double> prices;

    public MapAddOnPricer(Map<AddOn, Double> prices) {
        this.prices = prices;
    }

    @Override
    public double getPrice(AddOn addOn) {
        return prices.getOrDefault(addOn, 0.0);
    }
}
