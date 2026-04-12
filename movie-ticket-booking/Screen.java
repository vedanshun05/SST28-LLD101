import java.util.ArrayList;

public class Screen {
  String screenId;
  int number;
  ArrayList<Show> shows;
  ArrayList<Seat> seats;
  
  public Screen(String screenId, int number) {
    this.screenId = screenId;
    this.number = number;
    this.shows = new ArrayList<>();
    this.seats = new ArrayList<>();
  }
}
