package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class DaabRunCompare implements RuleComparator {
    private List<Card> groupOne;
    private List<Card> groupTwo;
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
        int maxFromGroupOne=getMaxFrom(groupOne);
        int maxFromGroupTwo=getMaxFrom(groupTwo);
       return  Integer.compare(maxFromGroupOne,maxFromGroupTwo);
    }

    private int getMaxFrom(List<Card> cards){
        int maxFromGroup=cards.get(0).getNumber();
        for(Card card:cards){
            if(card.getNumber()>maxFromGroup){
                maxFromGroup=card.getNumber();
            }
        }
        return maxFromGroup;
    }
}
