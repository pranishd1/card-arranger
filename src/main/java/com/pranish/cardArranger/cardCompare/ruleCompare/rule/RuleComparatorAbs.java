package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.game.hajare.HajareConst;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public abstract class RuleComparatorAbs implements RuleComparatorIface {
    protected List<Card> groupOne;
    protected List<Card> groupTwo;
    protected List<Card> combine;
    protected RulesIface rulesIface;
    protected List<Card> result;

    @Override
    public abstract void initialize(RulesIface rulesIface) ;

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
        if(result.size()>0) {
            Card firstOfTheResultIsWinner = result.get(0);
            return findWhereThisCardExist(firstOfTheResultIsWinner);
        }else{
            return HajareConst.getIsEqual();
        }
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
