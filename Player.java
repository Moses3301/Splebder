package gameLogic;

public class Player{
  int[eCOLOR.values().length] gemBank;
  int[eCOLOR.values().length] discounts;
  ArrayList<Card> reservedCards;
  ArrayList<Card> cards;
  ArrayList<NobleTile> nobles;
  int points;

  public Player(){
    for (int i = 0; i < eCOLOR.values().length; i++){
      gemBank[i] = discounts[i] = 0;
    }
    reservedCards = new ArrayList();
    cards = new ArrayList();
    int points = 0;
  }

  int getNumOfTokens(eCOLOR c){
    return gemBank[c.ordinal()];
  }

  ArrayList<Card> getReservedCards(){
    return reservedCards;
  }

  ArrayList<Card> getCards(){
    return cards;
  }

  void addDiscount(eCOLOR c){
    discounts[c.ordinal()]++;
  }

  void getDiscount(eCOLOR c){
    return discounts[c.ordinal()];
  }

  void addToken(eCOLOR c){
    gemBank[c.ordinal()]++;
  }

  void removeToken(eCOLOR c, int count){
    gemBank[c.ordinal()] -= count;
  }

  void addNoble(NobleTile noble){
    nobles.add(noble);
  }
}
