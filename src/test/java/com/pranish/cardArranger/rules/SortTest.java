package com.pranish.cardArranger.rules;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.Sort;
import org.junit.Test;

import java.util.List;

/**
 * Created by pranish on 11/27/15.
 */
public class SortTest {
    @Test
    public void sortTest() {
        try {
            List<Card> allCards = CardConst.getAllCards();
            CardFolder cardFolder = new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            List<Card> myCards = cardFolder.getDivision(allCards, 4, 13).get(0);
            cardFolder.sortDividedCards(myCards).toDescending();
            System.out.println("-------------MY CARDS----------------");
            for (Card card : myCards) {
                System.out.println(card.toString());
            }
            RulesIface run = new Sort();
            run.initialize(myCards);
            if (run.isValid()) {
                System.out.println("-------------ONLY VALID CARDS----------------");
                run.countValidOne();
                List<Card> validCards = run.getValidCards();
                for (Card card : validCards) {
                    System.out.println(card.toString());
                }
                System.out.println("-------------FORMED CARDS----------------");
                List<Card> formedCards = cardFolder.replaceCards(myCards, validCards).getLeftOutCards();
                for (Card card : formedCards) {
                    System.out.println(card.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
