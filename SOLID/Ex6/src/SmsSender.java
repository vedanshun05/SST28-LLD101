public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public boolean isValid(Notification n) {
        return n.phone != null && !n.phone.isEmpty();
    }

    @Override
    public void send(Notification n) {
        if (!isValid(n)) {
            System.out.println("SMS ERROR: invalid phone number");
            audit.add("sms failed");
            return;
        }

        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
