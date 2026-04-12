public class Main {
  public static void main(String[] args){
    LoadRegisteredPen.registerPens();

    Pen pen = PenFactory.createPen("Gel", "Red", true);
    pen.start();
    pen.write("This is my first line");
    pen.close();
    pen.refill();
  }  
}
