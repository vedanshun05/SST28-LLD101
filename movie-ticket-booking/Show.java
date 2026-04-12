import java.time.LocalDateTime;
import java.util.ArrayList;

public class Show {
  String showId;
  Movie movie;
  Screen screen;
  LocalDateTime time;
  ArrayList<ShowSeat> showSeats;
  
  public Show(String showId, Movie movie, Screen screen, LocalDateTime time) {
    this.showId = showId;
    this.movie = movie;
    this.screen = screen;
    this.time = time;
    this.showSeats = new ArrayList<>();
  }
}
