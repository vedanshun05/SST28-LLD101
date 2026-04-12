public class CommonRule {
  public static int check(int pos, Board b) {
    int value = pos;

    if (b.map.containsKey(pos)) {
      value = b.map.get(value).end;
    }

    return value;
  }
}
