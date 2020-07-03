package gameLogic;

int NUM_OF_LVL = 3;

class Board{
  int[eCOLOR.values().length] gemBank;
  Stack<DevelopmentCard>[NUM_OF_LVL] cardDecks;
  ArrayList<DevelopmentCard>[NUM_OF_LVL] cardsRow;
  ArrayList<NobleTile> nobleTiles;
  Stack<DevelopmentCard> nobleTilesDeck;

  int getNumOfTokens(eCOLOR c){
    return gemBank[c.ordinal()];
  }

  ArrayList<DevelopmentCard> getDevelopmentCardRow(int lvl){
    return cardsRow[lvl - 1];
  }

  DevelopmentCard getCardfromDeck(int lvl){
    return cardDecks.pop();
  }

  ArrayList<NobleTile> getNobles(){
    return nobleTiles;
  }

  void getNodlefromDeck(){
    return nobleTilesDeck.pop();
  }
}
