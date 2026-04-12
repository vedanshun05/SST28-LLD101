import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {

    private final RateLimitConfig config;

    // key -> [count, windowStartTime]
    private final ConcurrentHashMap<String, long[]> windowState = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean isAllowed(String key) {
        long now = System.currentTimeMillis();
        long windowMillis = config.getWindowDurationMillis();

        windowState.compute(key, (k, state) -> {
            if (state == null || now - state[1] >= windowMillis) {
                return new long[]{1, now};
            }
            state[0]++;
            return state;
        });

        long[] state = windowState.get(key);
        return state[0] <= config.getMaxRequests();
    }
}