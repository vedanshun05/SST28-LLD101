public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    /**
     * Checks if this sender can validly send the given notification.
     * Prevents unexpected runtime exceptions from violating LSP.
     */
    public abstract boolean supports(Notification n);

    /**
     * Sends the notification.
     * Precondition: supports(n) must be true.
     */
    public abstract void send(Notification n);
}
