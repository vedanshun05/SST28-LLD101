import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket assigned = service.assign(t, "agent@example.com");
        System.out.println("\nAfter assign: " + assigned);
        System.out.println("Original still same: " + t);

        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter escalate: " + escalated);

        try {
            List<String> tags = escalated.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccessfully blocked external mutation to tags list.");
        }

        IncidentTicket custom = IncidentTicket.builder()
                .id("TCK-2002")
                .reporterEmail("user@test.com")
                .title("Custom ticket")
                .priority("HIGH")
                .slaMinutes(60)
                .build();
        System.out.println("\nCustom via Builder: " + custom);

        try {
            IncidentTicket.builder().id("invalid id").build();
        } catch (IllegalArgumentException e) {
            System.out.println("\nCaught expected validation error: " + e.getMessage());
        }
    }
}
