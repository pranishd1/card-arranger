package com.pranish.cardArranger.cardCompare;

import com.pranish.cardArranger.card.Card;

import java.util.Comparator;

/**
 * Created by pranish on 11/15/15.
 */
public class CardGroupDesc implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return Integer.compare(o2.getGroup().getValue(),o1.getGroup().getValue());
    }
}
