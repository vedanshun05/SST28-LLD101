public interface BillingStrategy {
    Bill calculate(SpotType type, int duration);
}