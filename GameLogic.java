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
    bool isNobleTriggered = false;
    ArrayList<NobleTile> intrestedNobles;

    bool takeGemes(eCOLOR[2] gems){
      if (isActionDone = (gems[0] == gems[2]
        && board.getNumOfTokens(gem[0]) > 3 && !isActionDone)){
          for (gem : gems) {
            giveToken(gem);
          }
        }
        return isActionDone;
    }

    bool takeGemes(eCOLOR[3] gems){
      if (isActionDone = ((gems[0] != gems[1] != gems[2]) && !isActionDone){
        for (gem : gems)) {
          giveToken(gem);
        }
      }
        return isActionDone;
      }

    bool reserveDevelopmentCard(DevelopmentCard card){
      if (isActionDone = canReserveDevelopmentCard());
        currPlayer.getReservedCards().add(card);
        board.getDevelopmentCardRow(card.getLevel()).remove(card);
        board.getDevelopmentCardRow((card.getLevel()).add(getCardfromDeck(card.getLevel()));
        giveToken(eCOLOR.GOLD);
      }
      return isActionDone;
    }

    bool reserveDevelopmentCard(int lvl){
      if (isActionDone = canReserveDevelopmentCard());
        currPlayer.getReservedCards().add(board.getCardfromDeck(lvl));
        giveToken(eCOLOR.GOLD);
      }
      return isActionDone;
    }

    bool canReserveDevelopmentCard(){
      return (currPlayer.getReservedCards().lengh() < 3 && !isActionDone));
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

    ArrayList<DevelopmentCard> getDevelopmentCardRow(int lvl){
      board.getDevelopmentCardRow(lvl-1);
    }

    void buyCard(DevelopmentCard card){
      for (coust : card.getCoust()) {
        int newVal = (currPlayer.getNumOfTokens(coust.getColor()) + currPlayer.getDiscount(coust.getColor())) - coust.getPrice();
        if (newVal < 0){
          currPlayer.removeToken(coust.getColor() , currPlayer.getNumOfTokens(coust.getColor()));
          currPlayer.removeToken(eCOLOR.GOLD , (-1)*newVal);
        }
        else{
          currPlayer.setNumOfTokens(coust.getColor(), newVal);
        }
      }
      updateTriggeredNobles();
      if (intrestedNobles.lengh() == 1){
        visitNoble(intrestedNobles.get(0));
      }
    }

    bool canBuyCard(Card card){
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
      if (currPlayer.getNumOfToken() > 10 || !isActionDone || (intrestedNobles.length() == 0)){
        return false;
      }
      else{
        currPlayer = players.get((currPlayerIndex++) % players.size());
        isActionDone = false;
        intrestedNobles.clear();
        return true;
      }
    }

    void visitNoble(NobleTile noble){
      currPlayer.addNoble(noble);
      board.getNobles().remove(noble);
      board.getNobles().add(getNodlefromDeck());
    }

    void updateTriggeredNobles(){
      for (noble : board.getNobles()) {
        if (isNobleTriggered(noble)){
          intrestedNobles.add(noble);
        }
      }
    }

    bool isNobleTriggered(NobleTile noble){
      for (requestDevelopmentCard : noble.getRequestDevelopmentCards()) {
        if (requestDevelopmentCard.getAmount() <= currPlayer.getDiscount(requestDevelopmentCard.getColor())){
          return false;
        }
      }
      intrestedNobles.add(noble);
      return true;
    }
}
