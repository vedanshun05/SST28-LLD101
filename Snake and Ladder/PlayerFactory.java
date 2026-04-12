import java.util.*;

public class PlayerFactory {
  static Queue<Player> createPlayers(int x) {

    Queue<Player> players = new LinkedList<Player>();
    for(int i = 1; i<=x; i++){
      Player p = new Player("P"+ i);
      players.add(p);
    }

    return players;
 }
}
