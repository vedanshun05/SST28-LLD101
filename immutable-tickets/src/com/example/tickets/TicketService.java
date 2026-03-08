package com.example.tickets;

/**
 * Service layer that creates and "updates" tickets by creating new instances.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // Return a NEW instance with updated fields
        return IncidentTicket.from(t)
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // Return a NEW instance with updated fields
        return IncidentTicket.from(t)
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
