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
    private List<List<Card>> sortedCardValidGroup = null;
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
        sortedCardValidGroup =new ArrayList<>(0);

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
                        sortedCardValidGroup.add(sortSingleGroup(singleGroup));
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
                sortedCardValidGroup.add(sortSingleGroup(singleGroup));
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
        for (List<Card> cards : sortedCardValidGroup) {
            for (Card allCard : cards) {
                if (card.getId() == allCard.getId()) {
                    foundInCollection = true;
                }
            }
        }
        return foundInCollection;
    }

}
