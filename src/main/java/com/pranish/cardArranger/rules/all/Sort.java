package com.pranish.cardArranger.rules.all;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.cardCompare.CardCompareDesc;
import com.pranish.cardArranger.cardCompare.CardSorter;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 11/27/15.
 */
public class Sort implements RulesIface {
    private List<Card> myGroup = null;
    private Map<Integer, List<Card>> cardCounter = new HashMap<>(0);
    private List<List<Card>> sortedCardValidGroup = null;
    private List<Card> singleGroup = null;
    private int SIZE_OF_GROUP=3;
    @Override
    public RulesIface initialize(List<Card> myGroup) {
        CardSorter cardSorter = new CardSorter(myGroup, new CardCompareDesc());
        this.myGroup = cardSorter.getMyGroup();
        return this;
    }

    @Override
    public boolean isValid() {
       boolean isValid=false;
        if(myGroup.size()>0){
            isValid=true;
        }
        return isValid;
    }

    @Override
    public int countValidOne() {
       return myGroup.size()/SIZE_OF_GROUP+myGroup.size()%SIZE_OF_GROUP;
    }

    @Override
    public List<Card> getValidCards() {
        return myGroup;
    }
}
