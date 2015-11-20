package com.pranish.cardArranger.rules;

import com.pranish.cardArranger.Card;

import java.util.List;

/**
 * Created by pranish on 11/15/15.
 */
public interface RulesIface {
    public RulesIface initialize(List<Card> myGroup);
    public boolean isValid();
    public int countValidOne();
    public List<Card> getValidCards();
}
