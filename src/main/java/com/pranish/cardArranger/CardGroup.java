package com.pranish.cardArranger;

/**
 * Created by pranish on 11/14/15.
 */
public enum CardGroup {
    SPADE(3),
    HEART(2),
    CLUB(1),
    DIAMOND(0);

    int value;

    private CardGroup(int i) {
        value = i;
    }

   public  int getValue() {
        return value;
    }
}