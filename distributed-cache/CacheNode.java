import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {

    private final int capacity;
    private final Map<K, V> store;
    private final EvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.store = new HashMap<>();
    }

    public V get(K key) {
        if (!store.containsKey(key)) return null;
        evictionPolicy.keyAccessed(key);
        return store.get(key);
    }

    public void put(K key, V value) {
        if (store.containsKey(key)) {
            store.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }
        if (store.size() >= capacity) {
            K evicted = evictionPolicy.evict();
            store.remove(evicted);
        }
        store.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}