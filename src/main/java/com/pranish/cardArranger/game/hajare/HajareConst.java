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
    private final static int IS_IDENTICAL=2;

    private final static int FINAL_POINT=1000;

    public static int getFinalPoint() {
        return FINAL_POINT;
    }

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

    public static int getIsIdentical() {
        return IS_IDENTICAL;
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

    public static int countHajereGamePoints(Set<Card> cards){
        List<Card> temp=new ArrayList<>(0);
        for(Card card:cards){
            temp.add(card);
        }
        return countHajereGamePoints(temp);
    }

    public static int countHajereGamePointsFromListOfList(List<List<Card>> cards){
        int temp=0;
        for(List<Card> card:cards){
            temp+=countHajereGamePoints(card);
        }
        return temp;
    }

    public int getCompareResult(List<Card> cardGroupOne,List<Card> cardGroupTwo){
        if(getCardGroupRuleNumber(cardGroupOne)>getCardGroupRuleNumber(cardGroupTwo)){
            return  IS_SMALLER;
        }else if(getCardGroupRuleNumber(cardGroupOne)<getCardGroupRuleNumber(cardGroupTwo)){
            return IS_GREATER;
        }else if(areTheyIdentical(cardGroupOne,cardGroupTwo)){
            return IS_IDENTICAL;
        }
        else{
           return ruleComparison.compareFor(getCardGroupRuleNumber(cardGroupOne),cardGroupOne,cardGroupTwo);
            //System.out.println("Both of Same Group");
            //return checkUsingAllComboCasesAndSelectTheFirstCard(cardGroupOne, cardGroupTwo);
        }
    }

    private Map<Integer,Integer> getMappedCardNumber(List<Card> group){
        Map<Integer,Integer> mappedGroup=new HashMap<>(0);
        int incrementSize=1;
        for(Card card:group){
            if(mappedGroup.containsKey(card.getNumber())){
                mappedGroup.replace(card.getNumber(),mappedGroup.get(card.getNumber())+incrementSize);
            }else{
                mappedGroup.put(card.getNumber(),incrementSize);
            }
        }
        return mappedGroup;
    }

    private boolean areTheyIdentical(List<Card> cardGroupOne, List<Card> cardGroupTwo) {
        Map<Integer,Integer> cardOne=getMappedCardNumber(cardGroupOne);
        Map<Integer,Integer> cardTwo=getMappedCardNumber(cardGroupTwo);
        return doTheyHaveSameCount(cardOne,cardTwo);
    }

    private boolean doTheyHaveSameCount(Map<Integer, Integer> cardOne, Map<Integer, Integer> cardTwo) {
        for(Map.Entry<Integer,Integer> entry:cardOne.entrySet()){
            if(!cardTwo.containsKey(entry.getKey()) || cardTwo.get(entry.getKey())!=entry.getValue()){
                return false;
            }
        }
        return true;
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

    public int getIntegerCompareResult(int cardOneNumber,int cardTwoNumber){
        if(cardOneNumber>cardTwoNumber){
            return IS_GREATER;
        }else if(cardOneNumber<cardTwoNumber){
            return IS_SMALLER;
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

    public int checkUsingAllComboCasesAndSelectTheFirstCard(List<Card> groupOne, List<Card> groupTwo){
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
