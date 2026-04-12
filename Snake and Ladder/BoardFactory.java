import java.util.HashMap;

public class BoardFactory {
  static Board createBoard(int n){
    Board b = new Board(n);
    HashMap<Integer, Items> itemsMapping= ItemFactory.createItems(n);

    b.map = itemsMapping;
    return b;
  }
}
