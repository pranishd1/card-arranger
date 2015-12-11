package com.pranish.cardArranger.cardCompare;

import com.pranish.cardArranger.card.Card;

import java.util.Comparator;

/**
 * Created by pranish on 11/15/15.
 */
public class CardCompareDesc implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
          return Integer.compare(o2.getNumber(),o1.getNumber());
    }
}
