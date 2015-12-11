package com.pranish.cardArranger.cardCompare.ruleCompare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.cardCompare.ruleCompare.rule.*;
import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.*;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class RuleCompare implements RuleComparison {
    RulesIface rulesIface;
    RuleComparatorIface ruleComparatorIface;

    @Override
    public int compareFor(int ruleNumber,List<Card> groupOne,List<Card> groupTwo) {
       rulesIface=getRuleComparator(ruleNumber);
       ruleComparatorIface =new RuleComparator();
        ruleComparatorIface.initialize(rulesIface);
        ruleComparatorIface.compare(groupOne,groupTwo);
        return ruleComparatorIface.getResult();
    }

    private RulesIface getRuleComparator(int ruleNumber){
        RulesIface rulesIface=null;
        switch (ruleNumber){
            case 0:
                rulesIface= new Thirial();
                break;
            case 1:
                rulesIface=new DaabRun();
                break;
            case 2:
                rulesIface= new Run();
                break;
            case 3:
                rulesIface= new Falash();
                break;
            case 4:
                rulesIface= new Jute();
                break;
            case 5:
                rulesIface=new Sort();
                break;
            default:
                rulesIface=new Sort();
        }
        return rulesIface;
    }
}
