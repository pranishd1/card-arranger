package com.pranish.cardArranger.cardCompare.ruleCompare;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public interface RuleComparison {
    public  int compareFor(int ruleNumber, List<Card> groupOne, List<Card> groupTwo);
}
