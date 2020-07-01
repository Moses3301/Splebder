package gameLogic;

public class GameLogic
{
//TODO: make eCOLOR class
    enum eCOLOR{
      EMERALD,
      SAPPHIRE,
      RUBY,
      DIAMOND,
      ONYX,
      GOLD
    }

    bool isActionDone = false;
    ArrayList<Player> players;
    Player currPlayer;
    Board board;
    int currPlayerIndex;

    bool takeGemes(COLOR[2] gems){
      if (isActionDone = (gems[0] == gems[2]
        && board.getNumOfTokens(gem[0]) > 3 && !isActionDone)){
          for (gem : gems) {
            giveToken(gem);
          }
        }
        return isActionDone;
    }

    bool takeGemes(COLOR[3] gems){
      if (isActionDone = ((gems[0] != gems[1] != gems[2]) && !isActionDone){
        for (gem : gems)) {
          giveToken(gem);
        }
      }
        return isActionDone;
      }

    bool reserveDevelopmentCard(DevelopmentCard card){
      if (isActionDone = (currPlayer.getNumOfTokens(eCOLOR.GOLD) < 3 && board.getNumOfTokens(eCOLOR.GOLD) > 0 && !isActionDone)){
        giveToken(eCOLOR.GOLD);
        currPlayer.getReservedCards().add(card);
        board.getDevelopmentCard().remove(card);
      }
      return isActionDone;
    }

    bool purchaseDevelopmentCard(DevelopmentCard card){
      if (isActionDone = (canBuyCard(card) && !isActionDone)){
        buyCard(card)
        currPlayer.getCards().add(card);
        card.addNoblepoints(card.getNoblePoint());
        currPlayer.addDiscount(card.getCollor());
        isActionDone = true;
      }
      return isActionDone;
    }

    void buyCard(DevelopmentCard card){
      for (coust : card.getCoust()) {
        int newVal = (currPlayer.getNumOfTokens(coust.getColor()) + currPlayer.getDiscount(coust.getColor())) - coust.getPrice();
        if (newVal < 0){
          currPlayer.setNumOfTokens(coust.getColor() , 0);
          currPlayer.setNumOfTokens(eCOLOR.GOLD , currPlayer.getNumOfTokens(eCOLOR.GOLD) + newVal)
        }
        else{
          currPlayer.setNumOfTokens(coust.getColor(), newVal);
        }
      }
    }

    bool canBuyCard(DevelopmentCard card){
      int miss = 0;
      for (coust : card.getCoust()) {
        int fixedPrice = coust.getPrice() - currPlayer.getDiscount(coust.getColor());
        int aferPay = currPlayer.getNumOfTokens(coust.getColor()) - fixedPrice;
        if (aferPay < 0){
          miss += (-1)*aferPay
        }
      }
      return miss < currPlayer.getNumOfTokens(eCOLOR.GOLD);
    }

    void giveToken(COLOR color){
      currPlayer.addToken(color);
      board.removeToken(color);
    }

    bool endTurn(){
      if (currPlayer.getNumOfToken() > 10 || !isActionDone){
        return false;
      }
      else{
        currPlayer = players.get((currPlayerIndex++) % players.size());
        isActionDone = false;
        return true;
      }
    }
}
