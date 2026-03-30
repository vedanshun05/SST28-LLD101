import java.util.List;

public class InvoiceFormatter {
    public static String format(String invId, List<OrderLine> lines, CafeteriaSystem sys,
            OrderCalculator.OrderTotals totals) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = sys.getMenuItem(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", totals.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", totals.taxPct, totals.tax));
        out.append(String.format("Discount: -%.2f\n", totals.discount));
        out.append(String.format("TOTAL: %.2f\n", totals.total));

        return out.toString();
    }
}
