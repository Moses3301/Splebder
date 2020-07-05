import java.util.ArrayList;
import java.util.Stack;

class Board{
  int[] gemBank = new int[COLOR.values().length];
  Stack<DevelopmentCard>[] cardDecks = new Stack[3];
  ArrayList<DevelopmentCard>[] cardsRow = new ArrayList[3];
  ArrayList<NobleTile> nobleTiles;
  Stack<NobleTile> nobleTilesDeck;

  int getNumOfTokens(COLOR c){
    return gemBank[c.ordinal()];
  }

  ArrayList<DevelopmentCard> getDevelopmentCardRow(int lvl){
    return cardsRow[lvl - 1];
  }

  DevelopmentCard getCardfromDeck(int lvl){
    return cardDecks[lvl].pop();
  }

  ArrayList<NobleTile> getNobles(){
    return nobleTiles;
  }

  NobleTile getNodlefromDeck(){
    return nobleTilesDeck.pop();
  }

  boolean removeToken(COLOR c){
    if (gemBank[c.ordinal()] > 0){
      gemBank[c.ordinal()]--;
      return true;
    }
    return false;
  }
}
