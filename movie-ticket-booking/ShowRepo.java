import java.util.ArrayList;

public class ShowRepo {
  ArrayList<Show> shows = new ArrayList<>();
  
  public ArrayList<Show> getShowByCity(City city) {
    ArrayList<Show> showsByCity = new ArrayList<>();

    for(Show show: shows){
      if(show.screen != null){
        showsByCity.add(show);
      }
    }
    return showsByCity;
  }
}
