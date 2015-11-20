package com.pranish.cardArranger;

import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.DabRun;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/20/15.
 */
public class DabRunTest {
    @Test
    public void daabRunTest() {
        try {
            List<Card> allCards = Const.getAllCards();
            CardFolder cardFolder = new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            List<Card> myCards = cardFolder.getDivision(allCards, 4, 13).get(0);
            cardFolder.sortDividedCards(myCards).toDescending();
            System.out.println("-------------MY CARDS----------------");
            for (Card card : myCards) {
                System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
            }
            RulesIface dabRun = new DabRun();
            dabRun.initialize(myCards);
            if (dabRun.isValid()) {
                System.out.println("-------------ONLY VALID CARDS----------------");
                System.out.println(dabRun.countValidOne());
                List<Card> validCards = dabRun.getValidCards();
                for (Card card : validCards) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
                System.out.println("-------------FORMED CARDS----------------");
                List<Card> formedCards = cardFolder.replaceCards(myCards, validCards).getFormedCards();
                for (Card card : formedCards) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void manualDaabRunTest() {
        List<Card> collection = new ArrayList<>(0);
        try {
            Card card = new Card();
            card.setGroup(CardGroup.CLUB);
            card.setNumber(2);
            collection.add(card);

            Card card1 = new Card();
            card1.setGroup(CardGroup.CLUB);
            card1.setNumber(3);
            collection.add(card1);

            Card card2 = new Card();
            card2.setGroup(CardGroup.CLUB);
            card2.setNumber(14);
            collection.add(card2);

            Card card3 = new Card();
            card3.setGroup(CardGroup.CLUB);
            card3.setNumber(4);
            collection.add(card3);

            Card card4 = new Card();
            card4.setGroup(CardGroup.HEART);
            card4.setNumber(4);
            collection.add(card4);

            List<Card> otherCards = Const.getCertainCards(8);
            CardFolder cardFolder = new CardFolder();
            List<Card> newFormedCard = cardFolder.replaceCards(otherCards, collection).getFormedCards();
            cardFolder.sortDividedCards(newFormedCard).toDescending();
            System.out.println("-----------CREATED CARDS----------------------");
            for (Card newCard : newFormedCard) {
                System.out.println(" Number: " + newCard.getNumber() + " Name: " + newCard.getName() + " Group: " + newCard.getGroup());
            }

            RulesIface rules = new DabRun();
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
