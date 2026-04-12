import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter implements RateLimiter {

    private final RateLimitConfig config;

    // key -> deque of timestamps of accepted requests
    private final ConcurrentHashMap<String, Deque<Long>> requestLog = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean isAllowed(String key) {
        long now = System.currentTimeMillis();
        long windowMillis = config.getWindowDurationMillis();
        long windowStart = now - windowMillis;

        requestLog.putIfAbsent(key, new ConcurrentLinkedDeque<>());
        Deque<Long> timestamps = requestLog.get(key);

        synchronized (timestamps) {
            // Evict timestamps outside the current window
            while (!timestamps.isEmpty() && timestamps.peekFirst() <= windowStart) {
                timestamps.pollFirst();
            }

            if (timestamps.size() < config.getMaxRequests()) {
                timestamps.addLast(now);
                return true;
            }
            return false;
        }
    }
}