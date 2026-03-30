public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean supports(Notification n) {
        return n != null && n.email != null;
    }

    @Override
    public void send(Notification n) {
        // Technically still truncating, but to fulfill exactly the previous output
        // we keep the truncation but now guarded by supports
        String body = n.body;
        if (body != null && body.length() > 40)
            body = body.substring(0, 40);
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}
