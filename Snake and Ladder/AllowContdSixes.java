public class AllowContdSixes implements MakeMove {
  public void apply(Player p, Board b) {
    int diceValue = Dice.roll();
    System.out.println("Player P" + p.name + " has rolled a " + diceValue);
    int newPosition;
    if (diceValue != 6) {
      newPosition = p.position + diceValue;
    } else {
      System.out.println("Player P" + p.name + " has rolled a 6");
      newPosition = sixRule(diceValue, p);
    }
    if (newPosition > b.size) {
      return;
    }

    p.position = CommonRule.check(newPosition, b);
    System.out.println("Player P" + p.name + " has moved towrds " + p.position);
    return;
  }

  int sixRule(int diceValue, Player p) {
    int value = diceValue;
    while (value == 6) {
      value = Dice.roll();
      System.out.println("Player P" + p.name + " has rolled a six again. He has another chance");
    }

    return value;
  }
}
