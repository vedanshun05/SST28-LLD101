import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private final LinkedHashMap<K, Boolean> accessOrder;

    public LRUEvictionPolicy() {
        this.accessOrder = new LinkedHashMap<>(16, 0.75f, true);
    }

    @Override
    public void keyAccessed(K key) {
        accessOrder.put(key, true);
    }

    @Override
    public K evict() {
        K lruKey = accessOrder.entrySet().iterator().next().getKey();
        accessOrder.remove(lruKey);
        return lruKey;
    }
}