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
        Collecter collecter=getRuleComparator(ruleNumber);

       rulesIface=collecter.getRulesIface();
       ruleComparatorIface =collecter.getRuleComparatorIface();

        ruleComparatorIface.initialize(rulesIface);
        ruleComparatorIface.compare(groupOne,groupTwo);
        return ruleComparatorIface.getResult();
    }

    private Collecter getRuleComparator(int ruleNumber){
        Collecter collecter=new Collecter();
        switch (ruleNumber){
            case 0:
                collecter.setRulesIface(new Thirial());
                collecter.setRuleComparatorIface(new ThirialCompare());
                break;
            case 1:
                collecter.setRulesIface(new DaabRun());
                collecter.setRuleComparatorIface(new DaabRunCompare());
                break;
            case 2:
                collecter.setRulesIface(new Run());
                collecter.setRuleComparatorIface(new RunCompare());
                break;
            case 3:
                collecter.setRulesIface(new Falash());
                collecter.setRuleComparatorIface(new FalashCompare());
                break;
            case 4:
                collecter.setRulesIface(new Jute());
                collecter.setRuleComparatorIface(new JuteCompare());
                break;
            case 5:
                collecter.setRulesIface(new Sort());
                collecter.setRuleComparatorIface(new SortCompare());
                break;
            default:
                collecter.setRulesIface(new Sort());
                collecter.setRuleComparatorIface(new SortCompare());
        }
        return collecter;
    }
}
class Collecter{
    private RuleComparatorIface ruleComparatorIface;
    private RulesIface rulesIface;

    public RuleComparatorIface getRuleComparatorIface() {
        return ruleComparatorIface;
    }

    public void setRuleComparatorIface(RuleComparatorIface ruleComparatorIface) {
        this.ruleComparatorIface = ruleComparatorIface;
    }

    public RulesIface getRulesIface() {
        return rulesIface;
    }

    public void setRulesIface(RulesIface rulesIface) {
        this.rulesIface = rulesIface;
    }
}
