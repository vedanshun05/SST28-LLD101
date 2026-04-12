public class PenFactory {

  public static Pen createPen(String type, String color, boolean withCap) {

    PenCreator creator = PenRegistry.getCreator(type);

    if (creator == null) {
      throw new IllegalArgumentException("Unknown pen type: " + type);
    }

    return creator.create(color, withCap);
  }
}
