import java.util.HashMap;
import java.util.Random;

public class ItemFactory {
  static HashMap<Integer, Items> createItems(int n) {
    HashMap<Integer, Items> map = new HashMap<Integer, Items>();
    Random random = new Random();
    while (map.size() != n) {
      int start = random.nextInt(n * n) + 1;
      int end = random.nextInt(n * n) + 1;
      if (!map.containsKey(start) && start > end && start > 10) {
        map.put(start, new Snake(start, end));
      }
    }
    while (map.size() != n + n) {
      int start = random.nextInt(n * n) + 1;
      int end = random.nextInt(n * n) + 1;
      if (!map.containsKey(start) && start < end && start < (n*n - 10)) {
        map.put(start, new Ladder(start, end));
      }
    }

    return map;
  }
}
