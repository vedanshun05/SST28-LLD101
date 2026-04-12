import java.util.*;

public class Movie {
  String movieId;
  String name;
  int duration;
  String genre;
  ArrayList<String> languages;
  ArrayList<String> cast;

  public Movie(String movieId, String name, int duration, String genre) {
    this.movieId = movieId;
    this.name = name;
    this.duration = duration;
    this.genre = genre;
  }
}
