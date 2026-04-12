import java.util.Random;

public class Dice {
  static int roll(){
    Random random = new Random();

    return random.nextInt(6)+ 1;
  }
}
