import java.util.List;

public interface DistributionStrategy<K> {
    int getNodeIndex(K key, int numberOfNodes);
}