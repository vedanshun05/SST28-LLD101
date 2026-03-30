import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo showing the benefits of immutability and the Builder pattern.
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Safe creation via service (internally uses Builder)
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // 2. "Updates" now return NEW instances, leaving original t1 untouched
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nOriginal t1 (still untouched): " + t1);
        System.out.println("Assigned t2: " + t2);
        System.out.println("Escalated t3: " + t3);

        // 3. Immutability protection against list leaks
        try {
            List<String> tags = t3.getTags();
            tags.add("HACKED_FROM_OUTSIDE"); // This should throw an exception if using Collections.unmodifiableList
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccess: Internal tags list is protected from external modification.");
        }

        // 4. Validation in action
        try {
            System.out.println("\nTrying to create invalid ticket...");
            IncidentTicket.builder()
                .id("INVALID ID!") // space not allowed
                .reporterEmail("not-an-email")
                .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught error: " + e.getMessage());
        }
    }
}
