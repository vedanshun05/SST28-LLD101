import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookMyShow {
  TheatreCityRepo theatreRepo;
  ShowRepo showRepo;

  public BookMyShow(TheatreCityRepo theatreRepo, ShowRepo showRepo) {
    this.theatreRepo = theatreRepo;
    this.showRepo = showRepo;
  }

  public List<Theatre> getTheatresByCity(City city) {
    return theatreRepo.getTheatresByCity(city);
  }

  public List<Movie> getMoviesByCity(City city) {
    List<Show> shows = showRepo.getShowByCity(city);
    Set<Movie> movies = new HashSet<>();

    for (Show show : shows) {
      movies.add(show.movie);
    }

    return new ArrayList<>(movies);
  }
}
