package com.pranish.cardArranger.rules;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.card.CardGroup;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.Run;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/20/15.
 */
public class RunTest {
    @Test
    public void runTest() {
        try {
            List<Card> allCards = CardConst.getAllCards();
            CardFolder cardFolder = new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            List<Card> myCards = cardFolder.getDivision(allCards, 4, 13).get(0);
            cardFolder.sortDividedCards(myCards).toDescending();
            System.out.println("-------------MY CARDS----------------");
            for (Card card : myCards) {
                System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
            }
            RulesIface run = new Run();
            run.initialize(myCards);
            if (run.isValid()) {
                System.out.println("-------------ONLY VALID CARDS----------------");
                run.countValidOne();
                List<Card> validCards = run.getValidCards();
                for (Card card : validCards) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
                System.out.println("-------------FORMED CARDS----------------");
                List<Card> formedCards = cardFolder.replaceCards(myCards, validCards).getLeftOutCards();
                for (Card card : formedCards) {
                    System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void manualRunTest(){
        List<Card> collection = new ArrayList<>(0);
        try {
            Card card = new Card();
            card.setGroup(CardGroup.CLUB);
            card.setNumber(5);
            card.setId(CardConst.getCardIdFromGroupAndName(card.getGroup(), card.getName()));
            collection.add(card);

            Card card1 = new Card();
            card1.setGroup(CardGroup.SPADE);
            card1.setNumber(6);
            card1.setId(CardConst.getCardIdFromGroupAndName(card1.getGroup(), card1.getName()));
            collection.add(card1);

            Card card2 = new Card();
            card2.setGroup(CardGroup.HEART);
            card2.setNumber(7);
            card2.setId(CardConst.getCardIdFromGroupAndName(card2.getGroup(), card2.getName()));
            collection.add(card2);

            Card card3 = new Card();
            card3.setGroup(CardGroup.SPADE);
            card3.setNumber(8);
            card3.setId(CardConst.getCardIdFromGroupAndName(card3.getGroup(), card3.getName()));
            collection.add(card3);

            Card card4 = new Card();
            card4.setGroup(CardGroup.HEART);
            card4.setNumber(9);
            card4.setId(CardConst.getCardIdFromGroupAndName(card4.getGroup(), card4.getName()));
            collection.add(card4);

            Card card5 = new Card();
            card5.setGroup(CardGroup.CLUB);
            card5.setNumber(7);
            card5.setId(CardConst.getCardIdFromGroupAndName(card5.getGroup(), card5.getName()));
            collection.add(card5);

            Card card6 = new Card();
            card6.setGroup(CardGroup.CLUB);
            card6.setNumber(4);
            card6.setId(CardConst.getCardIdFromGroupAndName(card6.getGroup(), card6.getName()));
            collection.add(card6);

            List<Card> otherCards = CardConst.getCertainCards(6);
            CardFolder cardFolder = new CardFolder();
            List<Card> newFormedCard = cardFolder.replaceCards(otherCards, collection).getFormedCards();
            cardFolder.sortDividedCards(newFormedCard).toDescending();
            RulesIface rules = new Run();
            newFormedCard = CardConst.removeDuplicates(newFormedCard);
            System.out.println("-----------CREATED CARDS----------------------");
            for (Card newCard : newFormedCard) {
                System.out.println(newCard.toString());
            }
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
