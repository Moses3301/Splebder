import java.util.ArrayList;

 public class Player{
  int[] gemBank = new int[COLOR.values().length];
  int[] discounts = new int[COLOR.values().length];
  ArrayList<DevelopmentCard> reservedCards;
  ArrayList<DevelopmentCard> cards;
  ArrayList<NobleTile> nobles;
  int points;

  public Player(){
    for (int i = 0; i < COLOR.values().length; i++){
      gemBank[i] = discounts[i] = 0;
    }
    reservedCards = new ArrayList();
    cards = new ArrayList();
    int points = 0;
  }

  int getNumOfTokens(COLOR c){
    return gemBank[c.ordinal()];
  }

  int getNumOfTokens(){
    int sum = 0;
    for (int n : gemBank) {
      sum += n;
    }
    return sum;
  }

  void addPoints(int n){
      points+=n;
  }

  int getPoint(){
    return points;
   }

  ArrayList<DevelopmentCard> getReservedCards(){
    return reservedCards;
  }

  ArrayList<DevelopmentCard> getCards(){
    return cards;
  }

  void addDiscount(COLOR c){
    discounts[c.ordinal()]++;
  }

  int getDiscount(COLOR c){
    return discounts[c.ordinal()];
  }

  void addToken(COLOR c){
    gemBank[c.ordinal()]++;
  }

  boolean removeToken(COLOR c){
      if (gemBank[c.ordinal()]<0) { return false; }
         gemBank[c.ordinal()] --;
         return true;
  }

  boolean removeToken(COLOR c, int count){
      if (gemBank[c.ordinal()]<0) { return false; }
      gemBank[c.ordinal()] -= count;
      return true;
  }

  void addNoble(NobleTile noble){
    nobles.add(noble);
  }

}
