public class PenWithClicker implements StartingClosingOfPen{
  public void start(){
    System.out.println("Pen clicked open. You can start writing");
  }

  public void close(){
    System.out.println("Pen closed. Open again to start writing");
  }
}
