public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public abstract boolean isValid(Notification n);
    public abstract void send(Notification n);
}
