import java.util.HashMap;

public class Board {
  HashMap<Integer, Items> map;
  int size;

  Board(int n){
    this.size = n*n;
    this.map = new HashMap<Integer, Items>();
  }
}
