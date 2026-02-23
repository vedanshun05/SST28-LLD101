import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceRepository store) {
        this.store = store;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public MenuItem getMenuItem(String id) {
        return menu.get(id);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        OrderCalculator.OrderTotals totals = OrderCalculator.calculate(lines, this, customerType);
        String printable = InvoiceFormatter.format(invId, lines, this, totals);

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
