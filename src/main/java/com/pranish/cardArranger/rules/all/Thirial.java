package com.pranish.cardArranger.rules.all;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.rules.RulesAbs;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.*;

/**
 * Created by pranish on 11/15/15.
 */


public class Thirial extends RulesAbs {
    private final int THIRIAL_SIZE = 3;

    private List<Card> myGroup = null;
    private Map<Integer, List<Card>> cardCounter = new HashMap<>(0);
    private List<Card> validCardSingleGroup = null;
    private List<List<Card>> sortedCardValidGroup = null;

    @Override
    public RulesIface initialize(List<Card> myGroup) {
        this.myGroup = myGroup;
        for (Card card : this.myGroup) {
            if (cardCounter.containsKey(card.getNumber())) {
                List<Card> getAllCards = cardCounter.get(card.getNumber());
                getAllCards.add(card);
                cardCounter.replace(card.getNumber(), getAllCards);
            } else {
                List<Card> cards = new ArrayList<>(0);
                cards.add(card);
                cardCounter.put(card.getNumber(), cards);
            }
        }
        return this;
    }

    @Override
    public boolean isValid() {
        boolean isValid = false;
        validCardSingleGroup=new ArrayList<>(0);
        sortedCardValidGroup=new ArrayList<>(0);

        for (Map.Entry<Integer, List<Card>> cardNumber : cardCounter.entrySet()) {
            if (cardNumber.getValue().size() >= THIRIAL_SIZE) {
                isValid = true;
                int singleGroupEntry = 0;
                int wholeGroupSize = cardNumber.getValue().size() / THIRIAL_SIZE;
                int wholeGroupEntry = 0;
                for (Card card : cardNumber.getValue()) {
                    if (wholeGroupEntry != wholeGroupSize) {
                        validCardSingleGroup.add(card);
                        singleGroupEntry++;
                        if (singleGroupEntry == THIRIAL_SIZE) {
                            sortedCardValidGroup.add(validCardSingleGroup);
                            validCardSingleGroup = new ArrayList<>(0);
                            wholeGroupEntry++;
                        }
                    }
                }
            }
        }
        sortAndAdd();
        return isValid;
    }

    @Override
    public int countValidOne() {
        int validCounts = 0;
        validCounts = sortedCardValidGroup.size();
        return validCounts;
    }

    @Override
    public List<Card> getValidCards() {
        List<Card> onlyValidCards = new ArrayList<>(0);
        for (List<Card> cards : sortedCardValidGroup) {
            for (Card card : cards) {
                onlyValidCards.add(card);
            }
        }
        return onlyValidCards;
    }

    private void sortAndAdd() {
        for (int i = 0; i < sortedCardValidGroup.size(); i++) {
            for (int j = 0; j < sortedCardValidGroup.size(); j++) {
                if (sortedCardValidGroup.get(i).get(0).getNumber() > sortedCardValidGroup.get(j).get(0).getNumber()) {
                    List<Card> temp = sortedCardValidGroup.get(i);
                    sortedCardValidGroup.set(i, sortedCardValidGroup.get(j));
                    sortedCardValidGroup.set(j, temp);
                }
            }
        }
    }

}
