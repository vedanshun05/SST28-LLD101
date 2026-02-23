import java.util.*;

public class Demo02 {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        InvoiceRepository store = new FileStore();
        CafeteriaSystem sys = new CafeteriaSystem(store);
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1));

        sys.checkout("student", order);

        // Validation: second invoice, staff customer
        System.out.println();
        System.out.println("=== Validation ===");
        sys.checkout("staff", List.of(new OrderLine("S1", 1), new OrderLine("C1", 2)));
    }
}
