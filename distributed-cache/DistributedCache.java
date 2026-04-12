import java.util.ArrayList;
import java.util.List;

public class DistributedCache<K, V> {

    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K> distributionStrategy;
    private final Database<K, V> database;
    private final int numberOfNodes;

    public DistributedCache(int numberOfNodes, int capacityPerNode,
                            DistributionStrategy<K> distributionStrategy,
                            Database<K, V> database) {
        this.numberOfNodes = numberOfNodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        this.nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new CacheNode<>(capacityPerNode, new LRUEvictionPolicy<>()));
        }
    }

    public V get(K key) {
        CacheNode<K, V> node = getNode(key);
        V value = node.get(key);
        if (value == null) {
            // Cache miss: fetch from DB, populate cache
            value = database.fetch(key);
            if (value != null) {
                node.put(key, value);
            }
        }
        return value;
    }

    public void put(K key, V value) {
        database.save(key, value);
        getNode(key).put(key, value);
    }

    private CacheNode<K, V> getNode(K key) {
        int index = distributionStrategy.getNodeIndex(key, numberOfNodes);
        return nodes.get(index);
    }
}