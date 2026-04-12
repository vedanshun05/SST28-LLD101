public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public boolean isValid(Notification n) {
        return n.phone != null && n.phone.startsWith("+");
    }

    @Override
    public void send(Notification n) {
        if (!isValid(n)) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
            return;
        }

        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
