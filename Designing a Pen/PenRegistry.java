import java.util.HashMap;
import java.util.Map;

public class PenRegistry {
    private static final Map<String, PenCreator> registry = new HashMap<>();

    public static void register(String type, PenCreator creator) {
        registry.put(type.toLowerCase(), creator);
    }

    public static PenCreator getCreator(String type) {
        return registry.get(type.toLowerCase());
    }
}   
