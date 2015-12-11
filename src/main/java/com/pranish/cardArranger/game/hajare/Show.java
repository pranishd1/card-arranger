package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 12/11/15.
 */
public class Show {
    private List<Player> players;
    private Map<Player,List<Card>> winnerCollection;
    private final static int NUMBER_OF_CARDS_TO_SHOW=3;
    private static int ROUND=0;
    private static int NUMBER_OF_ROUNDS=0;

    HajareConst hajareConst;

    private final int CONDITION_FIRST_CARD_SHOW_WINS=1;
    private final int CONDTION_LAST_CARD_SHOW_WINS=2;

    private int ruleFollowed=CONDITION_FIRST_CARD_SHOW_WINS;

    public Show(List<Player> players){
        this.players=players;
        winnerCollection=new HashMap<>(0);
        NUMBER_OF_ROUNDS=this.players.get(0).getMyCard().size()/NUMBER_OF_CARDS_TO_SHOW;
        hajareConst=new HajareConst();
    }

    public void showCards(){
        Map<Player,List<Card>> groups=getFirstGroupCards();
        int numberOfPlayers=groups.size();
        for(Map.Entry<Player,List<Card>> entry:groups.entrySet()){
            int maxPlayerId=0;
            int maxNumber=0;
            for(Map.Entry<Player,List<Card>> listEntry:groups.entrySet()){
                if(entry.getKey().equals(listEntry.getKey())){
                    continue;
                }else{
                        if(hajareConst.getCompareResult(entry.getValue(),listEntry.getValue())==HajareConst.getIsGreater()){
                            maxPlayerId=entry.getKey().getId();
                        }else if(hajareConst.getCompareResult(entry.getValue(),listEntry.getValue())==HajareConst.getIsSmaller()){
                            maxPlayerId=listEntry.getKey().getId();
                        }else{
                            if(ruleFollowed==CONDITION_FIRST_CARD_SHOW_WINS){
                                maxPlayerId=entry.getKey().getId();
                            }else{
                                maxPlayerId=listEntry.getKey().getId();
                            }
                        }
                }
            }
        }

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

    private int getCardIndex(int index){
        return (ROUND*NUMBER_OF_CARDS_TO_SHOW)+index;
    }

    private Map<Player,List<Card>> getFirstGroupCards(){
        Map<Player,List<Card>> collection=new HashMap<>(0);
            for(Player player:players){
                List<Card> myCards=new ArrayList<>(0);
                myCards.add(player.getMyCard().get(getCardIndex(0)));
                myCards.add(player.getMyCard().get(getCardIndex(1)));
                myCards.add(player.getMyCard().get(getCardIndex(2)));
                if(ROUND==NUMBER_OF_ROUNDS){
                    myCards.add(player.getMyCard().get(getCardIndex(3)));
                }
                collection.put(player, myCards);
            }
        ROUND++;
        return collection;
    }


}
