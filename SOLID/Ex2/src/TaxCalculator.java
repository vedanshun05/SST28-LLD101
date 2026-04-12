public interface TaxCalculator {
    double calculateTax(double subtotal, String customerType);
    double getTaxRate(String customerType);
}
