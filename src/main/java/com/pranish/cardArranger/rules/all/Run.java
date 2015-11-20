package com.pranish.cardArranger.rules.all;

import com.pranish.cardArranger.Card;
import com.pranish.cardArranger.cardCompare.CardCompareDesc;
import com.pranish.cardArranger.cardCompare.CardSorter;
import com.pranish.cardArranger.rules.CardNumbers;
import com.pranish.cardArranger.rules.RulesAbs;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 11/15/15.
 */
public class Run extends RulesAbs {
    private List<Card> myGroup = null;
    private Map<Integer, List<Card>> cardCounter = new HashMap<>(0);
    private List<List<Card>> collectedCards = null;
    private List<Card> singleGroup = null;

    @Override
    public RulesIface initialize(List<Card> myGroup) {
        CardSorter cardSorter = new CardSorter(myGroup, new CardCompareDesc());
        this.myGroup = cardSorter.getMyGroup();
        for (Card card : this.myGroup) {
            if (cardCounter.containsKey(card.getNumber())) {
                List<Card> previousCards = cardCounter.get(card.getNumber());
                previousCards.add(card);
                cardCounter.replace(card.getNumber(), previousCards);
            } else {
                List<Card> newCards = new ArrayList<>(0);
                newCards.add(card);
                cardCounter.put(card.getNumber(), newCards);
            }
        }
        return this;
    }

    @Override
    public boolean isValid() {
        boolean isValid = false;
        singleGroup=new ArrayList<>(0);
        collectedCards=new ArrayList<>(0);

        if (hasAceTwoThree()) {
            addAceTwoAndThree();
            isValid = true;
        }

        if (myGroup.size() > 0) {
            for (Card card : myGroup) {
                if (cardCounter.containsKey(card.getNumber()) && cardCounter.containsKey(card.getNumber() - 1)
                        && cardCounter.containsKey(card.getNumber() - 2)) {
                    Card cardOne = getCardForNumber(card.getNumber() - 1);
                    Card cardTwo = getCardForNumber(card.getNumber() - 2);
                    Card cardThree = getCardForNumber(card.getNumber());
                    if (cardThree != null && cardOne != null && cardTwo != null) {
                        singleGroup.add(cardThree);
                        singleGroup.add(cardOne);
                        singleGroup.add(cardTwo);
                        collectedCards.add(sortSingleGroup(singleGroup));
                        singleGroup = new ArrayList<>(0);
                        isValid = true;
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
        validCounts = collectedCards.size();
        return validCounts;
    }

    @Override
    public List<Card> getValidCards() {
        List<Card> onlyValidCards = new ArrayList<>(0);
        for (List<Card> cards : collectedCards) {
            for (Card card : cards) {
                onlyValidCards.add(card);
            }
        }
        return onlyValidCards;
    }

    private void addAceTwoAndThree() {
        if (cardCounter.containsKey(CardNumbers.getAce()) &&
                cardCounter.containsKey(CardNumbers.getTwo()) &&
                cardCounter.containsKey(CardNumbers.getThree())) {
            Card cardOne = getCardForNumber(CardNumbers.getAce());
            Card cardTwo = getCardForNumber(CardNumbers.getTwo());
            Card cardThree = getCardForNumber(CardNumbers.getThree());
            if (!isInCollection(cardOne) && cardTwo != null && cardThree != null) {
                singleGroup.add(cardOne);
                singleGroup.add(cardTwo);
                singleGroup.add(cardThree);
                collectedCards.add(sortSingleGroup(singleGroup));
                singleGroup = new ArrayList<>(0);
            }
        }
    }

    private boolean hasAceTwoThree() {
        boolean isFound = false;
        if (cardCounter.containsKey(CardNumbers.getAce()) &&
                cardCounter.containsKey(CardNumbers.getTwo()) &&
                cardCounter.containsKey(CardNumbers.getThree())) {
            isFound = true;
        }
        return isFound;
    }

    private List<Card> sortSingleGroup(List<Card> cards) {
        CardSorter cardSorter = new CardSorter(cards, new CardCompareDesc());
        return cardSorter.getMyGroup();
    }

    private void sortAndAdd() {
        for (int i = 0; i < collectedCards.size(); i++) {
            for (int j = 0; j < collectedCards.size(); j++) {
                if (collectedCards.get(i).get(0).getNumber() > collectedCards.get(j).get(0).getNumber()) {
                    List<Card> temp = collectedCards.get(i);
                    collectedCards.set(i, collectedCards.get(j));
                    collectedCards.set(j, temp);
                }
            }
        }
    }

    private Card getCardForNumber(int cardNumber) {
        Card temp = null;
        List<Card> cards = cardCounter.get(cardNumber);
        for (Card card : cards) {
            if (!isInCollection(card)) {
                temp = card;
                break;
            }
        }
        return temp;
    }

    private boolean isInCollection(Card card) {
        boolean foundInCollection = false;
        for (List<Card> cards : collectedCards) {
            for (Card allCard : cards) {
                if (card.getId() == allCard.getId()) {
                    foundInCollection = true;
                }
            }
        }
        return foundInCollection;
    }

}
