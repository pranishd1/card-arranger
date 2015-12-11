package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class RuleComparator implements RuleComparatorIface {
    private List<Card> groupOne;
    private List<Card> groupTwo;
    private List<Card> combine;
    private RulesIface rulesIface;
    private List<Card> result;

    @Override
    public void initialize(RulesIface rulesIface) {
        this.rulesIface=rulesIface;
        combine=new ArrayList<>(0);
        result=new ArrayList<>(0);
    }

    @Override
    public void compare(List<Card> groupOne, List<Card> groupTwo) {
        this.groupOne=groupOne;
        this.groupTwo=groupTwo;
        combineBoth(this.groupOne,this.groupTwo);
    }

    @Override
    public int getResult() {
        rulesIface.initialize(combine);
        if(rulesIface.isValid()){
            result=rulesIface.getValidCards();
        }
        return compare();
    }

    private int compare() {
        Card firstOfTheResultIsWinner=result.get(0);
        return findWhereThisCardExist(firstOfTheResultIsWinner);
    }

    private int findWhereThisCardExist(Card firstOfTheResultIsWinner) {
        for(Card card:groupOne){
            if(firstOfTheResultIsWinner.getId()==card.getId()){
                return 1;
            }
        }
        return -1;
    }
    private void combineBoth(List<Card> groupOne, List<Card> groupTwo) {
        for(Card card:groupOne){
            combine.add(card);
        }
        for(Card card:groupTwo){
            combine.add(card);
        }
    }

}
