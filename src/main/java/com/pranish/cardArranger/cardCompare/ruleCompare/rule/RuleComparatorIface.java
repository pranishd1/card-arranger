package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public interface RuleComparatorIface {
    void initialize(RulesIface rulesIface);
    void compare(List<Card> groupOne,List<Card> groupTwo);
    int getResult();
}
