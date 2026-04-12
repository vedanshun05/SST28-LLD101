public class Main {
    public static void main(String[] args){
      Game game = GameFactory.createGame(10, 4, "hard");
      game.makeMove();
    }
}
