public class Main {
    public static void main(String[] args) {
        Database<String, String> db = new InMemoryDatabase<>();
        db.save("user:1", "Alice");
        db.save("user:2", "Bob");

        DistributedCache<String, String> cache = new DistributedCache<>(
            3,                                  // 3 nodes
            5,                                  // capacity 5 per node
            new ModuloDistributionStrategy<>(),
            db
        );

        // Cache miss — fetches from DB
        System.out.println(cache.get("user:1")); // Alice

        // Cache hit
        System.out.println(cache.get("user:1")); // Alice (from cache)

        // Put directly
        cache.put("user:3", "Charlie");
        System.out.println(cache.get("user:3")); // Charlie
    }
}