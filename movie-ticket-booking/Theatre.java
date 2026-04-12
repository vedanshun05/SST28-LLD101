import java.util.*;

public class Theatre {
  String Id;
  String name;
  ArrayList<Screen> screens;

  public Theatre(String Id, String name) {
    this.Id = Id;
    this.name = name;
    this.screens = new ArrayList<Screen>();
  }
}
