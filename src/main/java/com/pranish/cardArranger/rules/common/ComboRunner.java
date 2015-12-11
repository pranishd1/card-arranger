package com.pranish.cardArranger.rules.common;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.rules.RulesIface;
import com.pranish.cardArranger.rules.all.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 11/17/15.
 */
public class ComboRunner {
    private static List<Card> replacer ;
    private static Map<RuleNumber,List<Card>> validCardsWithRuleNumber;
    private CardFolder cardFolder = new CardFolder();

    public ComboRunner(List<Card> myGroup) {
        replacer=new ArrayList<>(0);
        validCardsWithRuleNumber=new HashMap<>(0);
        arranger(myGroup);


    }

    private void arranger(List<Card> myGroup){
        for(int i=0;i<=5;i++){
            if(!myGroup.isEmpty()) {
                RulesIface rulesIface = getRule(i);
                rulesIface.initialize(myGroup);
                if (rulesIface.isValid()) {
                    List<Card> temp = rulesIface.getValidCards();
                    addToReplacer(temp, i);
                    myGroup = cardFolder.replaceCards(myGroup, temp).getLeftOutCards();
                }
            }
        }
    }

    private RulesIface getRule(int ruleNumber){
        RulesIface rulesIface=null;
        switch (ruleNumber){
            case 0:
                rulesIface= new Thirial();
                break;
            case 1:
                rulesIface=new DaabRun();
                break;
            case 2:
                rulesIface= new Run();
                break;
            case 3:
                rulesIface= new Falash();
                break;
            case 4:
                rulesIface= new Jute();
                break;
            case 5:
                rulesIface=new Sort();
                break;
        }
        return rulesIface;
    }

    private static void addToReplacer(List<Card> myGroup, int caseNumber) {
        if(myGroup.size()>0) {
           // System.out.println("------------ " + getName(caseNumber) + " ---------------");
            for (Card card : myGroup) {
                //System.out.println(" Number: " + card.getNumber() + " Name: " + card.getName() + " Group: " + card.getGroup());
                replacer.add(card);
            }
            validCardsWithRuleNumber.put(RuleNumber.findFromValue(caseNumber),myGroup);
        }
    }

    public Map<RuleNumber,List<Card>> getValidCardsWithRuleNumber(){
        return validCardsWithRuleNumber;
    }

    public List<Card> getArrangedList() {
        return replacer;
    }

    private static String getName(int value) {
        switch (value) {
            case 0:
                return "THIRIAL";
            case 1:
                return "DAABRUN";
            case 2:
                return "RUN";
            case 3:
                return "FALASH";
            case 4:
                return "JUTE";
            case 5:
                return "SORT";
        }
        return "NULL";
    }
}
