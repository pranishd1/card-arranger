package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.rules.RulesIface;

import java.util.ArrayList;

/**
 * Created by pranish on 12/12/15.
 */
public class SortCompare extends RuleComparatorAbs {
    @Override
    public void initialize(RulesIface rulesIface) {
       // System.out.println("Sorting Here");
        this.rulesIface=rulesIface;
        combine=new ArrayList<>(0);
        result=new ArrayList<>(0);
    }
}
