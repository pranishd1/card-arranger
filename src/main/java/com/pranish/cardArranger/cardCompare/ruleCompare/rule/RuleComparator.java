package com.pranish.cardArranger.cardCompare.ruleCompare.rule;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public interface RuleComparator {
    void initialize(List<Card> groupOne,List<Card> groupTwo);
    int getResult();
}
