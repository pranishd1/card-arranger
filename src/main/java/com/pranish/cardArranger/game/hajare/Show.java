package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.player.Player;
import com.pranish.cardArranger.player.PlayerDict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 12/11/15.
 */
public class Show {
    private List<Player> players;
    private HajareConst hajareConst;

    private final int CONDITION_FIRST_CARD_SHOW_WINS=1;
    private final int CONDTION_LAST_CARD_SHOW_WINS=2;

    private int ruleFollowed=CONDITION_FIRST_CARD_SHOW_WINS;
    private static int round=0;
    private int numberOfRounds=0;

    private List<Player> roundWinner;

    public Show(List<Player> players){
        this.players=players;
        roundWinner=new ArrayList<>(0);
        numberOfRounds=this.players.get(0).getPlayerDict().getRounds().size();
        hajareConst=new HajareConst();

    }

    public void showCards(){
        //rounds
        for(int round=0;round<numberOfRounds;round++) {
            System.out.println();
            System.out.println("Round: "+(round+1));
            System.out.println();
            for(Player player:players){
                System.out.println("----------------------");
                System.out.println(player.toString());
                for(Card card:player.getPlayerDict().getRounds().get(round)){
                    System.out.println(card.toString());
                }
                System.out.println("----------------------");
            }
            Player winner=players.get(0);
            for(int player=0;player<players.size();player++){
               int result= hajareConst.getCompareResult(winner.getPlayerDict().getRounds().get(round),
                        players.get(player).getPlayerDict().getRounds().get(round));
                if(result==HajareConst.getIsSmaller()){
                    winner=players.get(player);
                }
            }
            System.out.println("Winner: "+winner.toString());
            addWinner(winner);
        }
        //printWinner();
    }

    private void addWinner(Player player){
        roundWinner.add(player);
    }

    private void printWinner() {
        int round=1;
        for(int i=0;i<roundWinner.size();i++){
            System.out.println("Round "+round+" Winner : -------> "+roundWinner.get(i).toString());
            round++;
        }
    }

    /*private void addWinnersCards(PlayerDict winner, List<List<Card>> wonCards) {

        if(hasThisPlayerWon(winner)){
            PlayerDict player=getPlayerOf(winner);
            for(List<Card> cards:wonCards){
                player.addWonCards(cards);
            }
        }else{
            for(List<Card> cards:wonCards){
                winner.addWonCards(cards);
            }
            winners.add(winner);
        }


    }*/

   /* private PlayerDict getPlayerOf(PlayerDict winner) {
        PlayerDict playerDict=null;
        for(PlayerDict player:winners){
            if(player.getPlayer().getId()==winner.getPlayer().getId()){
                playerDict=player;
            }
        }
        return playerDict;
    }*/

   /* public boolean hasThisPlayerWon(PlayerDict playerDict){
        boolean nope=false;
        for(PlayerDict player:winners){
            if(player.getPlayer().getId()==playerDict.getPlayer().getId()){
                nope=true;
            }
        }
        return nope;
    }*/

    private PlayerDict getWinnerPlayer(List<PlayerDict> playerDicts){
        PlayerDict winner=new PlayerDict();
        for(int i=0;i<playerDicts.size()-1;i++){
            if(hajareConst.getCompareResult(playerDicts.get(i).getMyCards(),playerDicts.get(i+1).getMyCards())
                    ==HajareConst.getIsGreater()){
                winner=playerDicts.get(i);
            }else if(hajareConst.getCompareResult(playerDicts.get(i).getMyCards(),playerDicts.get(i+1).getMyCards())
                    ==HajareConst.getIsSmaller()){
                winner=playerDicts.get(i + 1);
            }else{
                if(ruleFollowed==CONDITION_FIRST_CARD_SHOW_WINS){
                    winner=playerDicts.get(i);
                }else{
                    winner=playerDicts.get(i + 1);
                }
            }
        }
        return winner;
    }

    private Map<Integer,Integer> getHighestPlayerId(int[] tempHolder) {
        int maxNumber=tempHolder[0];
        int maxPlayerId=0;
        for(int i=0;i<tempHolder.length;i++){
            if(hajareConst.getCompareResult(maxNumber,tempHolder[i])==HajareConst.getIsSmaller()){
                maxNumber=tempHolder[i];
                maxPlayerId=i;
            }
        }
        Map<Integer,Integer> coll=new HashMap<>(0);
        coll.put(maxPlayerId,maxNumber);
        return coll;
    }
}
