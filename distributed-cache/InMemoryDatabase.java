import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase<K, V> implements Database<K, V> {

    private final Map<K, V> db = new HashMap<>();

    @Override
    public V fetch(K key) {
        return db.get(key);
    }

    @Override
    public void save(K key, V value) {
        db.put(key, value);
    }
}