public class SkipOn3Sixes implements MakeMove {
  public void apply(Player p, Board b) {
    int diceValue = Dice.roll();
    System.out.println("Player P" + p.name + " has rolled a " + diceValue);

    int newPosition;
    if (diceValue != 6) {
      newPosition = p.position + diceValue;
    } else {
      System.out.println("Player P" + p.name + " has rolled a 6");
      int move = sixRule(p);
      newPosition = p.position + move;
    }
    if (newPosition > b.size) {
      return;
    }

    p.position = CommonRule.check(newPosition, b);
    System.out.println("Player P" + p.name + " has moved towrds " + p.position);

    return;
  }

  int sixRule(Player p) {
    int count = 1; // first 6 already rolled
    int total = 6;

    while (count < 3) {
      int dice = Dice.roll();
      System.out.println("Player P" + p.name + " has rolled a " + dice);

      if (dice == 6) {
        count++;
        total += 6;

        if (count == 3) {
          p.skipTurn = true;
          System.out.println("Player P" + p.name + " rolled 3 consecutive sixes. No movement, next turn skipped.");
          return 0;
        }
      } else {
        total += dice;
        break;
      }
    }

    return total;
  }
}
