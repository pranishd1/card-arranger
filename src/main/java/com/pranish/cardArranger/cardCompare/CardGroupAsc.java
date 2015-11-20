package com.pranish.cardArranger.cardCompare;

import com.pranish.cardArranger.Card;

import java.util.Comparator;

/**
 * Created by pranish on 11/15/15.
 */
public class CardGroupAsc implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return Integer.compare(o1.getGroup().getValue(),o2.getGroup().getValue());
    }
}