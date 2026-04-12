import java.util.List;
import java.util.Map;

public interface InvoiceFormatterInterface {
    String formatInvoice(String invId, List<OrderLine> lines, Map<String, MenuItem> menu,
                         double subtotal, double taxPct, double tax, double discount, double total);
    }