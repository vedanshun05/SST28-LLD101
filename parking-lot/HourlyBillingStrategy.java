import java.util.*;

public class HourlyBillingStrategy implements BillingStrategy {

    Map<SpotType, Double> rates = new HashMap<>();

    public HourlyBillingStrategy(){
        rates.put(SpotType.SMALL, 20.0);
        rates.put(SpotType.MEDIUM, 40.0);
        rates.put(SpotType.LARGE, 80.0);
    }

    public Bill calculate(SpotType type, int duration){
        int hours = (int)Math.ceil(duration / 60.0);
        double amount = hours * rates.get(type);
        return new Bill(amount, duration, type);
    }
}