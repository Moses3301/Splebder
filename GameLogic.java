import java.util.ArrayList;

public class GameLogic
{
    boolean  isActionDone = false;
    ArrayList<Player> players;
    Player currPlayer;
    Board board;
    int currPlayerIndex;
    boolean  isNobleTriggered = false;
    ArrayList<NobleTile> intrestedNobles;

    boolean  takeTwoGemes(COLOR gem){
      if ( isActionDone = !isActionDone && (board.getNumOfTokens(gem) > 3)){
        giveToken(gem,1);
      }
      return isActionDone;
    }

    boolean  takeTwoGemes(COLOR gem1, COLOR gem2, COLOR gem3){
      boolean isGemesDifferent = (gem1!=gem2) && (gem1!=gem3) && (gem2!=gem3);
      boolean isGemesAvailable = (board.getNumOfTokens(gem1) > 0) && (board.getNumOfTokens(gem2) > 0) && (board.getNumOfTokens(gem3) > 0);
      if ( isActionDone = !isActionDone && isGemesDifferent && isGemesAvailable ) ){
        giveToken(gem,1);
      }
      return isActionDone;
    }

    boolean  reserveDevelopmentCard(DevelopmentCard card){
      if (isActionDone = canReserveDevelopmentCard() && board.getDevelopmentCardRow(card.getLevel()).remove(card)){
        currPlayer.getReservedCards().add(card);
        throwCard(card.getLevel());
        giveToken(COLOR.GOLD);
      }
      return isActionDone;
    }

    void throwCard(int level){
      board.getDevelopmentCardRow(level).add(board.getCardfromDeck(level));
    }

    boolean  reserveDevelopmentCard(int level){
      if (isActionDone = canReserveDevelopmentCard()){
        currPlayer.getReservedCards().add(board.getCardfromDeck(level));
        giveToken(COLOR.GOLD);
      }
      return isActionDone;
    }

    boolean  canReserveDevelopmentCard(){
      return (currPlayer.getReservedCards().size() < 3 && !isActionDone);
    }

    boolean  purchaseDevelopmentCard(DevelopmentCard card){
      if (canBuyCard(card) && !isActionDone){
        if (board.getDevelopmentCardRow(card.getLevel()).remove(card) || currPlayer.getReservedCards().remove(card)]){
          buyCard(card);
          currPlayer.getCards().add(card);
          currPlayer.addPoints(card.getPrestigePoints());
          currPlayer.addDiscount(card.getColor());
          isActionDone = true;
        }
      }
      return false;
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
      if (currPlayer.getNumOfToken() > 10 || !isActionDone || (intrestedNobles.length() != 0)){
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
            intrestedNobles.clear();
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

    boolean removeGem(COLOR color){
      if (isActionDone && currPlayer.getNumOfTokens()>10){
        currPlayer.removeToken(color,1);
      }
    }
}
