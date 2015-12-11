package com.pranish.cardArranger.cardCompare;

import com.pranish.cardArranger.card.Card;

import java.util.Comparator;

/**
 * Created by pranish on 11/14/15.
 */
public class CardCompareAsc implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return Integer.compare(o1.getNumber(),o2.getNumber());
    }
}
