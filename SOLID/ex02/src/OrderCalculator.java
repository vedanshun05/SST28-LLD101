import java.util.List;

public class OrderCalculator {
    public static class OrderTotals {
        public final double subtotal;
        public final double taxPct;
        public final double tax;
        public final double discount;
        public final double total;

        public OrderTotals(double subtotal, double taxPct, double tax, double discount, double total) {
            this.subtotal = subtotal;
            this.taxPct = taxPct;
            this.tax = tax;
            this.discount = discount;
            this.total = total;
        }
    }

    public static OrderTotals calculate(List<OrderLine> lines, CafeteriaSystem sys, String customerType) {
        double subtotal = 0;
        for (OrderLine l : lines) {
            MenuItem item = sys.getMenuItem(l.itemId);
            subtotal += item.price * l.qty;
        }

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;
        return new OrderTotals(subtotal, taxPct, tax, discount, total);
    }
}
