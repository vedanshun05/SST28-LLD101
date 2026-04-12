public class ModuloDistributionStrategy<K> implements DistributionStrategy<K> {

    @Override
    public int getNodeIndex(K key, int numberOfNodes) {
        return Math.abs(key.hashCode()) % numberOfNodes;
    }
}