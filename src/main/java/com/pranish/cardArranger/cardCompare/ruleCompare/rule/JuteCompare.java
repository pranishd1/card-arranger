package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 12/11/15.
 */
public class JuteCompare implements RuleComparator {
    private List<Card> groupOne;
    private List<Card> groupTwo;
    private final int INCREMENT_SIZE=1;
    @Override
    public void initialize(List<Card> groupOne, List<Card> groupTwo) {
        this.groupOne=groupOne;
        this.groupTwo=groupTwo;
    }

    @Override
    public int getResult() {
        return compare();
    }

    private int compare() {
    int juteNumberFromGroupOne=getJuteNumberFrom(groupOne);
    int juteNumberFromGroupTwo=getJuteNumberFrom(groupTwo);
        if(juteNumberFromGroupOne>juteNumberFromGroupTwo){
            return 1;
        }else if(juteNumberFromGroupOne<juteNumberFromGroupTwo){
            return -1;
        }else{
            return Integer.compare(getNonJuteNumberFrom(groupOne),getNonJuteNumberFrom(groupTwo));
        }
    }

    private int getJuteNumberFrom(List<Card> cards){
        int juteNumber=0;
        Map<Integer,Integer> counts=new HashMap<>(0);
        for(Card card:cards){
            if(counts.containsKey(card.getNumber())){
                counts.replace(card.getNumber(),counts.get(card.getNumber())+INCREMENT_SIZE);
            }else{
                counts.put(card.getNumber(),INCREMENT_SIZE);
            }
        }
        for(Map.Entry<Integer,Integer> entry:counts.entrySet()){
            if(entry.getValue()>INCREMENT_SIZE){
                juteNumber=entry.getValue();
            }
        }
        return juteNumber;
    }
    private int getNonJuteNumberFrom(List<Card> cards){
        int nonJuteNumber=0;
        int juteNumber=getJuteNumberFrom(cards);
        for(Card card:cards){
            if(card.getNumber()!=juteNumber){
                nonJuteNumber=card.getNumber();
            }
        }
        return nonJuteNumber;
    }
}
