public abstract class Pen {
  String color;
  StartingClosingOfPen startingClosingOfPen;
  boolean isOpen = false;
  Refill refill;
  int maxInk = 200;
  int remainingInk = 200;

  public Pen(String c) {
    this.color = c;
  }

  void write(String message) {

    for (int i = 0; i < message.length(); i++) {

      char ch = message.charAt(i);

      if (ch != ' ') {

        if (this.remainingInk == 0) {
          System.out.println("\nError: Ink empty. Please refill.");
          break;
        }

        this.remainingInk--;
      }

      System.out.print(ch);
    }

    System.out.println();
  }

  public void start() {
    if (!this.isOpen) {
      startingClosingOfPen.start();
      this.isOpen = true;
      return;
    }

    throw new RuntimeException("Pen is already open");
  }

  public void close() {
    if (this.isOpen) {
      startingClosingOfPen.close();
      this.isOpen = false;
      return;
    }

    throw new RuntimeException("Pen is already closed");
  }

  public void refill() {
    refill.refill();
    this.remainingInk = this.maxInk;
    System.out.println("Pen refilled.");
  }
}
