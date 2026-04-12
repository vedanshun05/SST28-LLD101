import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimitConfig config = new RateLimitConfig(5, 1, TimeUnit.MINUTES);

        // Swap this line to switch algorithms — nothing else changes
        RateLimiter rateLimiter = new SlidingWindowRateLimiter(config);
        // RateLimiter rateLimiter = new FixedWindowRateLimiter(config);

        ExternalResourceClient client = new ExternalResourceClient();
        InternalService service = new InternalService(rateLimiter, client);

        String tenant = "T1";

        for (int i = 1; i <= 7; i++) {
            String result = service.handleRequest(tenant, "request-" + i, true);
            System.out.println("Request " + i + " -> " + result);
        }

        // Request not needing external call — no rate limiting applied
        service.handleRequest(tenant, "internal-only", false);
    }
}