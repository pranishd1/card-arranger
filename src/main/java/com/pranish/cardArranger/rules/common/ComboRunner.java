package com.pranish.cardArranger.rules.common;

import com.pranish.cardArranger.Card;
import com.pranish.cardArranger.CardFolder;
import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/17/15.
 */
public class ComboRunner {
    private static List<Card> replacer = new ArrayList<>(0);
    private CardFolder cardFolder = new CardFolder();

    public ComboRunner(List<Card> myGroup) {
        arranger(1, myGroup);
    }

    private void arranger(int level, List<Card> myGroup) {
        RulesIface rulesIface;

        switch (level) {
            case 1:
                rulesIface = new Thirial();
                rulesIface.initialize(myGroup);
                if (rulesIface.isValid()) {
                    List<Card> temp = rulesIface.getValidCards();
                    addToReplacer(temp, 1);
                    myGroup = cardFolder.replaceCards(myGroup, temp).getLeftOutCards();
                }
                arranger(2, myGroup);
                break;
            case 2:
                rulesIface = new DabRun();
                rulesIface.initialize(myGroup);
                if (rulesIface.isValid()) {
                    List<Card> temp = rulesIface.getValidCards();
                    addToReplacer(temp, 2);
                    myGroup = cardFolder.replaceCards(myGroup, temp).getLeftOutCards();
                }
                arranger(3, myGroup);
                break;
            case 3:
                rulesIface = new Run();
                rulesIface.initialize(myGroup);
                if (rulesIface.isValid()) {
                    List<Card> temp = rulesIface.getValidCards();
                    addToReplacer(temp, 3);
                    myGroup = cardFolder.replaceCards(myGroup, temp).getLeftOutCards();
                }
                arranger(4, myGroup);
                break;
            case 4:
                rulesIface = new Falash();
                rulesIface.initialize(myGroup);
                if (rulesIface.isValid()) {
                    List<Card> temp = rulesIface.getValidCards();
                    addToReplacer(temp, 4);
                    myGroup = cardFolder.replaceCards(myGroup, temp).getLeftOutCards();
                }
                arranger(5, myGroup);
                break;
            case 5:
                rulesIface = new Jute();
                rulesIface.initialize(myGroup);
                if (rulesIface.isValid()) {
                    List<Card> temp = rulesIface.getValidCards();
                    addToReplacer(temp, 5);
                    myGroup = cardFolder.replaceCards(myGroup, temp).getLeftOutCards();
                }
                arranger(6, myGroup);
                break;
            case 6:
                addToReplacer(cardFolder.sortDividedCards(myGroup).toDescending(), 6);
                return;
        }
    }

    private static void addToReplacer(List<Card> myGroup, int caseNumber) {
        if(myGroup.size()>0) {
            System.out.println("------------ " + getName(caseNumber) + " ---------------");
            for (Card card : myGroup) {
                System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                replacer.add(card);
            }
        }
    }

    public List<Card> getArrangedList() {
        return replacer;
    }

    private static String getName(int value) {
        switch (value) {
            case 1:
                return "THIRIAL";
            case 2:
                return "DAABRUN";
            case 3:
                return "RUN";
            case 4:
                return "FALASH";
            case 5:
                return "JUTE";
            case 6:
                return "SORT";
        }
        return "NULL";
    }
}
