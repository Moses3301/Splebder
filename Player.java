package gameLogic;

public class Player{
  int[eCOLOR.values().length] gemBank;
  int[eCOLOR.values().length] discounts;
  ArrayList<Card> reservedCards;
  ArrayList<Card> cards;
  int points;

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
}
