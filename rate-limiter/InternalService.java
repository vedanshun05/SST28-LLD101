public class InternalService {

    private final RateLimiter rateLimiter;
    private final ExternalResourceClient externalClient;

    public InternalService(RateLimiter rateLimiter, ExternalResourceClient externalClient) {
        this.rateLimiter = rateLimiter;
        this.externalClient = externalClient;
    }

    public String handleRequest(String tenantKey, String payload, boolean needsExternalCall) {
        if (!needsExternalCall) {
            System.out.println("[Service] No external call needed. Serving from internal logic.");
            return "internal result";
        }

        if (!rateLimiter.isAllowed(tenantKey)) {
            System.out.println("[Service] Rate limit exceeded for: " + tenantKey);
            return "RATE_LIMITED";
        }

        return externalClient.call(payload);
    }
}