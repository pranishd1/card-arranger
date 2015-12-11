package com.pranish.cardArranger.rules;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.rules.all.Falash;
import org.junit.Test;

import java.util.List;

/**
 * Created by pranish on 11/20/15.
 */
public class FalashTest {
    @Test
    public void falashTest() {
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
            RulesIface falash = new Falash();
            falash.initialize(myCards);
            if (falash.isValid()) {
                System.out.println("-------------ONLY VALID CARDS----------------");
                System.out.println(falash.countValidOne());
                List<Card> validCards = falash.getValidCards();
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

}
