
public class GameLogic
{
    Player[] players;
    Player currPlayer;
    Board board;

    bool takeGemes(COLOR[2] gems){
      if (gems[0] == gems[2]
        && board.getNumOfTokens(gem[0]) > 3){
          for (gem : gems) {
            giveToken(gem);
            return true;
          }
        }
        return false;
    }

    bool takeGemes(COLOR[3] gems){
      if (gems[0] != gems[1] != gems[2]){
        for (gem : gems) {
          giveToken(gem);
        }
        return true;
      }
        return false;
      }

    bool reserveDevelopmentCard(DevelopmentCard card){
      if (players.getNumOfTokens(GOLD) < 3 && board.getNumOfTokens(GOLD) > 0){
        giveToken(GOLD);
        currPlayer.getReservedCards().add(card);
        board.getDevelopmentCard().remove(card);
        return true;
      }
      return false;
    }

    bool purchaseDevelopmentCard(DevelopmentCard card){
      if (canBuyCard(card)){
        currPlayer.getCards().add(card);
        card.addNoblepoints(card.getNoblePoint());
        currPlayer.addDiscount(card.getCollor());
      }
    }

    bool canBuyCard(DevelopmentCard card){
      int miss
      for (coust : card.getCoust()) {
        int numOfGems = currPlayer.getDiscount(coust.getColor()) + currPlayer.getGemNum(coust.getColor());
        if (numOfGems < )
      }
    }

    void giveToken(COLOR color){
      currPlayer.addToken(color);
      board.removeToken(color);
    }
}
