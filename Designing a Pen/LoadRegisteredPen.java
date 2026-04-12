public class LoadRegisteredPen {
  public static void registerPens() {

    PenRegistry.register("BallPoint", (color, withCap) -> {
      Pen pen = new BallPoint(color);
      pen.startingClosingOfPen = withCap ? new PenWithCap() : new PenWithClicker();
      pen.refill = new RefillByRemoving();
      return pen;
    });

    PenRegistry.register("Gel", (color, withCap) -> {
      Pen pen = new Gel(color);
      pen.startingClosingOfPen = withCap ? new PenWithCap() : new PenWithClicker();
      pen.refill = new RefillByRemoving();
      return pen;
    });

    PenRegistry.register("Fountain", (color, withCap) -> {
      Pen pen = new Fountain(color);
      pen.startingClosingOfPen = withCap ? new PenWithCap() : new PenWithClicker();
      pen.refill = new RefillByFilling();
      return pen;
    });

  }
}
