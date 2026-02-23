import java.util.Map;

public class MapRoomPricer implements RoomPricer {
    private final Map<Integer, Double> prices;
    private final double defaultPrice;

    public MapRoomPricer(Map<Integer, Double> prices, double defaultPrice) {
        this.prices = prices;
        this.defaultPrice = defaultPrice;
    }

    @Override
    public double getPrice(int roomType) {
        return prices.getOrDefault(roomType, defaultPrice);
    }
}
