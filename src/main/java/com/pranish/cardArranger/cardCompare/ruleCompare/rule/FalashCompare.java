package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class FalashCompare implements RuleComparator {
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
       for(Card card:groupOne){
           for(Card card1:groupTwo){
               if(card.getNumber()>card1.getNumber()){
                   return 1;
               }else if(card.getNumber()<card1.getNumber()){
                   return -1;
               }
           }
       }
        return 0;
    }
}
