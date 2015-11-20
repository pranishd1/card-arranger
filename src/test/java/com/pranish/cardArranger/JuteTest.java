package com.pranish.cardArranger;

import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.Jute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/20/15.
 */
public class JuteTest {
    @Test
    public void juteTest() {
        try {
            List<Card> allCards = Const.getAllCards();
            CardFolder cardFolder = new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            List<Card> myCards = cardFolder.getDivision(allCards, 4, 13).get(0);
            cardFolder.sortDividedCards(myCards).toDescending();
            for (Card card : myCards) {
                System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
            }
            RulesIface rules = new Jute();
            rules.initialize(myCards);
            if (rules.isValid()) {
                System.out.println(rules.countValidOne());
                List<Card> validCards = rules.getValidCards();
                System.out.println("-----------VALID CARDS----------------------");
                for (Card card : validCards) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
                List<Card> newFormed = cardFolder.replaceCards(myCards, validCards).getRuleArrangedCards();
                System.out.println("---------NEW FORMED CARDS---------------");
                for (Card card : newFormed) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void manualJuteTest() {
        List<Card> collection = new ArrayList<>(0);
        try {
            Card card = new Card();
            card.setGroup(CardGroup.SPADE);
            card.setNumber(14);
            collection.add(card);

            Card card1 = new Card();
            card1.setGroup(CardGroup.CLUB);
            card1.setNumber(14);
            collection.add(card1);

            Card card2 = new Card();
            card2.setGroup(CardGroup.SPADE);
            card2.setNumber(13);
            collection.add(card2);

            Card card3 = new Card();
            card3.setGroup(CardGroup.HEART);
            card3.setNumber(13);
            collection.add(card3);

            Card card4 = new Card();
            card4.setGroup(CardGroup.CLUB);
            card4.setNumber(12);
            collection.add(card4);


            Card card5 = new Card();
            card5.setGroup(CardGroup.HEART);
            card5.setNumber(12);
            collection.add(card5);

            Card card6 = new Card();
            card6.setGroup(CardGroup.CLUB);
            card6.setNumber(11);
            collection.add(card6);

            Card card7 = new Card();
            card7.setGroup(CardGroup.CLUB);
            card7.setNumber(9);
            collection.add(card7);

            Card card8 = new Card();
            card8.setGroup(CardGroup.SPADE);
            card8.setNumber(9);
            collection.add(card8);

            Card card9 = new Card();
            card9.setGroup(CardGroup.HEART);
            card9.setNumber(8);
            collection.add(card9);

            Card card10 = new Card();
            card10.setGroup(CardGroup.CLUB);
            card10.setNumber(6);
            collection.add(card10);

            Card card11 = new Card();
            card11.setGroup(CardGroup.DIAMOND);
            card11.setNumber(3);
            collection.add(card11);

            Card card12 = new Card();
            card12.setGroup(CardGroup.HEART);
            card12.setNumber(3);
            collection.add(card12);

            List<Card> otherCards = Const.getCertainCards(0);
            CardFolder cardFolder = new CardFolder();
            List<Card> newFormedCard = cardFolder.replaceCards(otherCards, collection).getFormedCards();
            cardFolder.sortDividedCards(newFormedCard).toDescending();
            System.out.println("-----------CREATED CARDS----------------------");
            for (Card newCard : newFormedCard) {
                System.out.println(" Number: " + newCard.getNumber() + " Name: " + newCard.getName() + " Group: " + newCard.getGroup());
            }

            RulesIface rules = new Jute();
            newFormedCard = Const.removeDuplicates(newFormedCard);
            rules.initialize(newFormedCard);
            if (rules.isValid()) {
                System.out.println(rules.countValidOne());
                List<Card> validCards = rules.getValidCards();
                System.out.println("-----------VALID CARDS----------------------");
                for (Card newCard : validCards) {
                    System.out.println(" Number: " + newCard.getNumber() + " Name: " + newCard.getName() + " Group: " + newCard.getGroup());
                }
                List<Card> newFormed = cardFolder.replaceCards(newFormedCard, validCards).getFormedCards();
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
