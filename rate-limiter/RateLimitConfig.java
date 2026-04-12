import java.util.concurrent.TimeUnit;

public class RateLimitConfig {
    private final int maxRequests;
    private final long windowDuration;
    private final TimeUnit timeUnit;

    public RateLimitConfig(int maxRequests, long windowDuration, TimeUnit timeUnit) {
        this.maxRequests = maxRequests;
        this.windowDuration = windowDuration;
        this.timeUnit = timeUnit;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowDurationMillis() {
        return timeUnit.toMillis(windowDuration);
    }
}