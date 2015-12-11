package com.pranish.cardArranger.rules;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 11/15/15.
 */
public abstract class RulesAbs implements RulesIface {
    abstract public RulesIface initialize(List<Card> myGroup) ;
    abstract public boolean isValid() ;
    abstract public int countValidOne() ;
    abstract public List<Card> getValidCards();
}
