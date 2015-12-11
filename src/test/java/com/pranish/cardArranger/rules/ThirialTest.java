package com.pranish.cardArranger.rules;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.card.CardGroup;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/15/15.
 */
public class ThirialTest {
    @Test
    public void thirialTest() {
        try {
            List<Card> allCards = CardConst.getAllCards();
            CardFolder cardFolder = new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            List<Card> myCards = cardFolder.getDivision(allCards, 4, 17).get(0);
            cardFolder.sortDividedCards(myCards);
            for (Card card : myCards) {
                System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
            }
            Thirial thirial = new Thirial();
            thirial.initialize(myCards);
            System.out.println(thirial.isValid());
            if (thirial.isValid()) {
                System.out.println(thirial.countValidOne());
                List<Card> validCards = thirial.getValidCards();
                cardFolder.sortDividedCards(validCards).toDescending();
                for (Card card : validCards) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
                List<Card> newFormed = cardFolder.replaceCards(myCards, validCards).getFormedCards();
                System.out.println("--------NEW FORMED----------");
                for (Card card : newFormed) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void manualThirialTest() {
        List<Card> collection = new ArrayList<>(0);
        try {
            Card card = new Card();
            card.setGroup(CardGroup.CLUB);
            card.setNumber(2);
            collection.add(card);

            Card card1 = new Card();
            card1.setGroup(CardGroup.DIAMOND);
            card1.setNumber(2);
            collection.add(card1);

            Card card2 = new Card();
            card2.setGroup(CardGroup.HEART);
            card2.setNumber(2);
            collection.add(card2);

            Card card3 = new Card();
            card3.setGroup(CardGroup.SPADE);
            card3.setNumber(3);
            collection.add(card3);

            Card card4 = new Card();
            card4.setGroup(CardGroup.CLUB);
            card4.setNumber(3);
            collection.add(card4);

            Card card5 = new Card();
            card5.setGroup(CardGroup.DIAMOND);
            card5.setNumber(3);
            collection.add(card5);


            List<Card> otherCards = CardConst.getCertainCards(7);
            CardFolder cardFolder = new CardFolder();
            List<Card> newFormedCard = cardFolder.replaceCards(otherCards, collection).getFormedCards();
            System.out.println("-----------CREATED CARDS----------------------");
            for (Card newCard : newFormedCard) {
                System.out.println(" Number: " + newCard.getNumber() + " Name: " + newCard.getName() + " Group: " + newCard.getGroup());
            }

            RulesIface rules = new Thirial();
            newFormedCard = CardConst.removeDuplicates(newFormedCard);
            rules.initialize(newFormedCard);
            if (rules.isValid()) {
                System.out.println(rules.countValidOne());
                List<Card> validCards = rules.getValidCards();
                System.out.println("-----------VALID CARDS----------------------");
                for (Card newCard : validCards) {
                    System.out.println(" Number: " + newCard.getNumber() + " Name: " + newCard.getName() + " Group: " + newCard.getGroup());
                }
                List<Card> newFormed = cardFolder.replaceCards(newFormedCard, validCards).getLeftOutCards();
                System.out.println("---------NEW FORMED CARDS---------------");
                for (Card newCard : newFormed) {
                    System.out.println(" Number: " + newCard.getNumber() + " Name: " + newCard.getName() + " Group: " + newCard.getGroup());
                }

            } else {
                System.out.println("Not Valid.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
