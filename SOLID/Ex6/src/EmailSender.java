public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public boolean isValid(Notification n) {
        return n.email != null && n.email.contains("@");
    }

    @Override
    public void send(Notification n) {
        if (!isValid(n)) {
            System.out.println("EMAIL ERROR: invalid email address");
            audit.add("email failed");
            return;
        }

        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}
