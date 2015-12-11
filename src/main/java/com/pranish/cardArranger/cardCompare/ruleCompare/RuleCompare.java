package com.pranish.cardArranger.cardCompare.ruleCompare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.cardCompare.ruleCompare.rule.*;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class RuleCompare implements RuleComparison {
    RuleComparator ruleComparator;
    @Override
    public int compareFor(int ruleNumber,List<Card> groupOne,List<Card> groupTwo) {
        setRuleComparator(ruleNumber);
        ruleComparator.initialize(groupOne,groupTwo);
        return ruleComparator.getResult();
    }

    public void setRuleComparator(int ruleNumber){
        switch (ruleNumber){
            case 0:
                ruleComparator= new ThirialCompare();
                break;
            case 1:
                ruleComparator=new DaabRunCompare();
                break;
            case 2:
                ruleComparator= new RunCompare();
                break;
            case 3:
                ruleComparator= new FalashCompare();
                break;
            case 4:
                ruleComparator= new JuteCompare();
                break;
            case 5:
                ruleComparator=new SortCompare();
                break;
        }
    }
}
