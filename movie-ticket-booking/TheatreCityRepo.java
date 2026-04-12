import java.util.ArrayList;
import java.util.HashMap;
public class TheatreCityRepo {
  HashMap<City, ArrayList<Theatre>> cityTheatreMap = new HashMap<>();
  
  public ArrayList<Theatre> getTheatresByCity(City city) {
    return cityTheatreMap.get(city);
  }
}
