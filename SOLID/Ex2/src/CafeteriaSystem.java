import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();

    private final StorageInterface store;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatterInterface formatter;
    private final TaxCalculator taxCalculator;

    public CafeteriaSystem(StorageInterface store, DiscountPolicy discountPolicy, InvoiceFormatterInterface formatter, TaxCalculator taxCalculator) {
        this.store = store;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
        this.taxCalculator = taxCalculator;
    }

    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        double taxPct = taxCalculator.getTaxRate(customerType);
        double tax = taxCalculator.calculateTax(subtotal, customerType);
        double discount = discountPolicy.calculateDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = formatter.formatInvoice(invId,lines, menu, subtotal,
                                                    taxPct, tax, discount, total);

        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
