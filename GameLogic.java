import java.util.ArrayList;

public class GameLogic {
    boolean isActionDone = false;
    ArrayList<Player> players;
    Player currPlayer;
    Board board;
    int currPlayerIndex;
    ArrayList<NobleTile> intrestedNobles;
    ArrayList<COLOR> gemesTaken;


    boolean takeGem(COLOR gem) {
        if (isActionDone) {
            return false;
        }
        if (board.getNumOfTokens(gem) == 0) {
            return false;
        }
        int numOfGemTaken = gemesTaken.size();
        //the second gem
        if (numOfGemTaken == 1) {
            //take two same gems
            if (gem == gemesTaken.get(0)) {
                if (board.getNumOfTokens(gem) < 2) {
                    return false;
                }
                isActionDone = true;
            }
        }
        //the third gem
        if (numOfGemTaken == 2) {
            if (gemesTaken.get(0) == gem || gemesTaken.get(2) == gem) {
                return false;
            }
            isActionDone = true;
        }
        giveToken(gem);
        gemesTaken.add(gem);
        if (isActionDone) {
            gemesTaken.clear();
            endTurn();
        }
        return true;
    }

    boolean removeGem(COLOR color) {
        //after action done
        if (isActionDone && currPlayer.getNumOfTokens() > 10) {
            return removeToken(color);
        }
        //before action done
        else {
            if (gemesTaken.remove(color)) {
                return removeToken(color);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean bool = false;
        System.out.println(bool);
        System.out.println(bool = true);
        System.out.println(bool);
    }

    boolean reserveDevelopmentCard(DevelopmentCard card) {
        if (isActionDone) {
            return false;
        }
        //remove the card from the board if possible
        if (board.getDevelopmentCardRow(card.getLevel()).remove(card)) {
            currPlayer.getReservedCards().add(card);
            throwCard(card.getLevel());
            // give gold token if possible
            giveToken(COLOR.GOLD);
            isActionDone = true;
            endTurn();
            return true;
        }
        return false;
    }

    void throwCard(int level) {
        board.getDevelopmentCardRow(level).add(board.getCardfromDeck(level));
    }

    boolean removeToken(COLOR color, int n) {
        if (currPlayer.getNumOfTokens(color) < n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            removeToken(color);
        }
        return true;
    }

    boolean giveToken(COLOR color) {
        if (board.removeToken(color)) {
            currPlayer.addToken(color);
            return true;
        }
        return false;
    }

    boolean removeToken(COLOR color) {
        if (currPlayer.removeToken(color)) {
            board.addToken(color);
            return true;
        }
        return false;
    }

    boolean reserveDevelopmentCard(int level) {
        if (isActionDone) {
            return false;
        }
        if (currPlayer.getReservedCards().size() < 3) {
            currPlayer.getReservedCards().add(board.getCardfromDeck(level));
            giveToken(COLOR.GOLD);
            isActionDone = true;
            endTurn();
            return true;
        }
        return false;
    }

    boolean purchaseDevelopmentCard(DevelopmentCard card) {
        if (isActionDone) {
            return false;
        }
        if (canBuyCard(card)) {
            if (board.getDevelopmentCardRow(card.getLevel()).remove(card) || currPlayer.getReservedCards().remove(card)) {
                buyCard(card);
                updateTriggeredNobles();
                if (intrestedNobles.size() == 1) {
                    visitNoble(intrestedNobles.get(0));
                }
                isActionDone = true;
                endTurn();
                return true;
            }
        }
        return false;
    }

    boolean canBuyCard(DevelopmentCard card) {
        int goldToken = currPlayer.getNumOfTokens(COLOR.GOLD);
        int diff = calMissTokens(card);
        return (goldToken >= diff);
    }

    void buyCard(DevelopmentCard card) {
        for (int i = 0; i < calMissTokens(card); i++) {
            removeToken(COLOR.GOLD);
        }
        for (GemStack coust : card.getCoust()) {
            removeToken(coust.color, coust.amount);
        }
        currPlayer.getCards().add(card);
        currPlayer.addPoints(card.getPrestigePoints());
        currPlayer.addDiscount(card.getColor());
    }

    int calMissTokens(DevelopmentCard card) {
        int diff = 0;
        int res = 0;
        for (GemStack coust : card.getCoust()) {
            diff = coust.amount - currPlayer.getNumOfTokens(card.getColor());
            if (diff < 0) {
                res += (-1) * diff;
            }
        }
        return res;
    }

    void updateTriggeredNobles() {
        for (NobleTile noble : board.getNobles()) {
            if (isNobleTriggered(noble)) {
                intrestedNobles.add(noble);
            }
        }
    }

    boolean visitNoble(NobleTile noble) {
        if (intrestedNobles.remove(noble)) {
            currPlayer.addNoble(noble);
            currPlayer.addPoints(noble.prestigePoints);
            board.getNobles().remove(noble);
            board.getNobles().add(board.getNodlefromDeck());
            intrestedNobles.clear();
            return true;
        }
        return false;
    }

    boolean isNobleTriggered(NobleTile noble) {
        for (GemStack requestDevelopmentCard : noble.getRequestDevelopments()) {
            if (requestDevelopmentCard.getAmount() <= currPlayer.getDiscount(requestDevelopmentCard.getColor())) {
                return false;
            }
        }
        intrestedNobles.add(noble);
        return true;
    }

    boolean  endTurn(){
        if (!isActionDone) { return false; }
        if (currPlayer.getNumOfTokens() > 10) { return false; }
        if (intrestedNobles.size() != 0) {return false;}
        currPlayer = players.get((currPlayerIndex++) % players.size());
        isActionDone = false;
        return true;
    }

}
