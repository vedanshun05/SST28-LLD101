import java.util.*;

public class Game {
  Queue<Player> players;
  Board board;
  Rule makeMoveRule;

  Game(Queue<Player> p, Board b, Rule m){
    this.players = p;
    this.board = b;
    this.makeMoveRule = m;
  }
  void makeMove(){
    while(players.size() >= 2){
      Player p = players.poll();
      if(p.skipTurn){
        p.skipTurn = false;
        players.add(p);
        continue;
      }
      makeMoveRule.apply(p, board);
      if(p.position == board.size){
        System.out.println("Player P" + p.name + " has won");
        continue;
      }else{
        players.add(p);
      }
    }
  }


}
