import java.util.Queue;

public class GameFactory {
  static Game createGame(int n, int x, String level) {
    Board b = BoardFactory.createBoard(n);
    Queue<Player> players = PlayerFactory.createPlayers(x);

    
    Game game = new Game(players, b, null);
    if (level.equals("easy")) {
      Rule makeMove = new AllowContdSixes();
      game = new Game(players, b, makeMove);
    } else if(level.equals("hard")){
      Rule makeMove = new SkipOn3Sixes();
      game = new Game(players, b, makeMove);
    }
    

    return game;
  }
}
