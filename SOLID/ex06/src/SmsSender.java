public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean supports(Notification n) {
        return n != null && n.phone != null;
    }

    @Override
    public void send(Notification n) {
        if (!supports(n))
            throw new IllegalArgumentException("Invalid phone for SMS");

        // Ignores subject; base type doesn't clarify expectations (smell)
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
