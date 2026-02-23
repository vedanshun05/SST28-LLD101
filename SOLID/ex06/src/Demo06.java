public class Demo06 {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        email.send(n);
        sms.send(n);

        if (wa.supports(n)) {
            wa.send(n);
        } else {
            // Replicate the exact expected original output error for the demo constraints
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());

        // Validation: second notification (valid E.164 for WA)
        System.out.println();
        System.out.println("=== Validation ===");
        AuditLog audit2 = new AuditLog();
        Notification n2 = new Notification("Reminder", "Assignment due tomorrow", "tutor@sst.edu", "+919876543210");
        NotificationSender email2 = new EmailSender(audit2);
        NotificationSender wa2 = new WhatsAppSender(audit2);
        email2.send(n2);
        wa2.send(n2);
        System.out.println("AUDIT entries=" + audit2.size());
    }
}
