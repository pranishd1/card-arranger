package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.cardCompare.ruleCompare.RuleCompare;
import com.pranish.cardArranger.cardCompare.ruleCompare.RuleComparison;
import com.pranish.cardArranger.rules.common.ComboRunner;
import com.pranish.cardArranger.rules.common.RuleNumber;

import java.util.*;

/**
 * Created by pranish on 11/27/15.
 */
public  class HajareConst {
    private final static int TEN_POINTS=10;
    private final static int FIVE_POINTS=5;
    private final static int THRESHOLD=10;


    private final static int IS_GREATER=1;
    private final static int IS_SMALLER=-1;
    private final static int IS_EQUAL=0;

    RuleComparison ruleComparison;

    public HajareConst(){
        ruleComparison=new RuleCompare();
    }

    public static int getIsGreater() {
        return IS_GREATER;
    }

    public static int getIsSmaller() {
        return IS_SMALLER;
    }

    public static int getIsEqual() {
        return IS_EQUAL;
    }

    public static int countHajereGamePoints(List<Card> cards){
        int sum=0;
        for(Card card:cards){
            if(card.getNumber()>=THRESHOLD){
                sum+=TEN_POINTS;
            }else{
                sum+=FIVE_POINTS;
            }
        }
        return sum;
    }

    public int getCompareResult(List<Card> cardGroupOne,List<Card> cardGroupTwo){
        if(getCardGroupRuleNumber(cardGroupOne)>getCardGroupRuleNumber(cardGroupTwo)){
            return  IS_SMALLER;
        }else if(getCardGroupRuleNumber(cardGroupOne)<getCardGroupRuleNumber(cardGroupTwo)){
            return IS_GREATER;
        }else{
           // return ruleComparison.compareFor(getCardGroupRuleNumber(cardGroupOne),cardGroupOne,cardGroupTwo);
            return getCardGroupRuleNumber(cardGroupOne,cardGroupTwo);
        }


    }

    public int getCompareResult(int cardGroupOne,int cardGroupTwo){
        if(cardGroupOne>cardGroupTwo){
            return  IS_SMALLER;
        }else if(cardGroupOne<cardGroupTwo){
            return IS_GREATER;
        }else{
            return IS_EQUAL;
        }
    }

    public int getCardGroupRuleNumber(List<Card> cards){
        int ruleNumber=6;
        ComboRunner comboRunner=new ComboRunner(cards);
        Map<RuleNumber,List<Card>> details=comboRunner.getValidCardsWithRuleNumber();
        for(Map.Entry<RuleNumber,List<Card>> entry:details.entrySet()){
            if(hasSameCardsInCollection(entry.getValue(),cards)){
                ruleNumber=entry.getKey().getValue();
            }
        }
        return ruleNumber;
    }

    public int getCardGroupRuleNumber(List<Card> groupOne,List<Card> groupTwo){
       List<Card> combined=combine(groupOne,groupTwo);
        ComboRunner comboRunner=new ComboRunner(combined);
        List<Card> arrangedList=comboRunner.getArrangedList();
        return findInWhichGroupFirstCardExist(arrangedList,groupOne);
    }

    private List<Card> combine(List<Card> groupOne, List<Card> groupTwo) {
        List<Card> combine=new ArrayList<>(0);
        for(Card card:groupOne){
            combine.add(card);
        }
        for(Card card:groupTwo){
            combine.add(card);
        }
        return combine;
    }

    private int findInWhichGroupFirstCardExist(List<Card> arrangedList,List<Card> group) {
        Card firstCard=arrangedList.get(0);
        for(Card card:group){
            if(card.getId()==firstCard.getId()){
                return IS_GREATER;
            }
        }
        return IS_SMALLER;
    }

    private boolean hasSameCardsInCollection(List<Card> first,List<Card> second){
        boolean hasSameCard=false;

        Set<Card> cardGroupOne=new HashSet<>(0);
        for(Card card:first){
            cardGroupOne.add(card);
        }
        Set<Card> cardGroupTwo=new HashSet<>(0);
        for(Card card:second){
            cardGroupTwo.add(card);
        }

        if(cardGroupOne.equals(cardGroupTwo)){
            hasSameCard=true;
        }

        return hasSameCard;
    }
}
