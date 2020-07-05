
public class GameLogic
{
    boolean  isActionDone = false;
    ArrayList<Player> players;
    Player currPlayer;
    Board board;
    int currPlayerIndex;
    boolean  isNobleTriggered = false;
    ArrayList<NobleTile> intrestedNobles;

    boolean  takeGemes(COLOR[] gems){
      if (isActionDone) return false;
      switch(gems.lengh){
          case 2:
              if (gems[0]==gems[1] && board.getNumOfTokens(gems[0]) > 2){
                giveToken(gems[0],2);
                return true;
              }
              break;
          case 3:
              boolean isValidInput = gems[0]!=gems[1] && gems[0]!=gems[2] && gems[1]!=gems[2];
              boolean isGemesInBank = true;
              for (int i=0;i<3;i++){
                if (board.getNumOfTokens(gems[i])<1){
                  isGemesInBank =  false;
                  break;
                }
              }
              if (isValidInput && isGemesInBank){
                for (int i=0;i<3;i++){
                  giveToken(gem[i]);
                  return true;
                  }
                }
          default:
            break;
            }
            return false;
      }
    

    boolean  reserveDevelopmentCard(DevelopmentCard card){
      if (isActionDone = canReserveDevelopmentCard()){
        currPlayer.getReservedCards().add(card);
        board.getDevelopmentCardRow(card.getLevel()).remove(card);
        board.getDevelopmentCardRow(card.getLevel()).add(getCardfromDeck(card.getLevel()));
        giveToken(eCOLOR.GOLD);
      }
      return isActionDone;
    }

    boolean  reserveDevelopmentCard(int lvl){
      if (isActionDone = canReserveDevelopmentCard()){
        currPlayer.getReservedCards().add(board.getCardfromDeck(lvl));
        giveToken(eCOLOR.GOLD);
      }
      return isActionDone;
    }

    boolean  canReserveDevelopmentCard(){
      return (currPlayer.getReservedCards().lengh() < 3 && !isActionDone);
    }

    boolean  purchaseDevelopmentCard(DevelopmentCard card){
      if (isActionDone = (canBuyCard(card) && !isActionDone)){
        buyCard(card);
        currPlayer.getCards().add(card);
        card.addNoblepoints(card.getNoblePoint());
        currPlayer.addDiscount(card.getColor());
        isActionDone = true;
      }
      return isActionDone;
    }

    ArrayList<DevelopmentCard> getDevelopmentCardRow(int lvl){
      board.getDevelopmentCardRow(lvl-1);
    }

    void buyCard(DevelopmentCard card){
      for (gemStack coust : card.getCoust()) {
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

    boolean  canBuyCard(DevelopmentCard card){
      int miss = 0;
      for (gemStack coust : card.getCoust()) {
        int fixedPrice = coust.getAmount() - currPlayer.getDiscount(coust.getColor());
        int aferPay = currPlayer.getNumOfTokens(coust.getColor()) - fixedPrice;
        if (aferPay < 0){
          miss += (-1)*aferPay;
        }
      }
      return miss < currPlayer.getNumOfTokens(eCOLOR.GOLD);
    }

    void giveToken(COLOR color){
      giveToken(color,1);
    }

    void giveToken(COLOR color, int n){
      for (int i=0;i<n;i++){
        currPlayer.addToken(color);
        board.removeToken(color);
      }
    }

    boolean  endTurn(){
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
    
    boolean askNobletoVisit(NobleTile noble){
        if (!isActionDone) return false;
        if (intrestedNobles.remove(noble)){
            visitNoble(noble);
            return true;
        }
        return false;
    }

    void updateTriggeredNobles(){
      for (NobleTile noble : board.getNobles()) {
        if (isNobleTriggered(noble)){
          intrestedNobles.add(noble);
        }
      }
    }

    boolean  isNobleTriggered(NobleTile noble){
      for (gemStack requestDevelopmentCard : noble.getRequestDevelopments()) {
        if (requestDevelopmentCard.getAmount() <= currPlayer.getDiscount(requestDevelopmentCard.getColor())){
          return false;
        }
      }
      intrestedNobles.add(noble);
      return true;
    }
}
