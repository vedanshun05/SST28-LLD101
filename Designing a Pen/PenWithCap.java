public class PenWithCap implements StartingClosingOfPen{
  public void start(){
    System.out.println("Cap opened. You can start writing");
  }

  public void close(){
    System.out.println("Cap closed. Open again to write");
  }
}
