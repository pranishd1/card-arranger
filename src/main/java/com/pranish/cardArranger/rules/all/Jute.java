package com.pranish.cardArranger.rules.all;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.cardCompare.CardCompareAsc;
import com.pranish.cardArranger.cardCompare.CardCompareDesc;
import com.pranish.cardArranger.cardCompare.CardSorter;
import com.pranish.cardArranger.rules.RulesAbs;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.*;

/**
 * Created by pranish on 11/17/15.
 */
public class Jute extends RulesAbs {

    private final int INCREMENT_SIZE = 1;
    private final int JUTE_SIZE =2;
    private final int SINGLE_CARD=0;

    private final static int ASCENDING_ORDER=1;
    private final static int DESENDING_ORDER=2;

    private List<Card> myGroup;
    private Map<Integer,List<Card>> cardCounter=new HashMap<>(0);
    private List<List<Card>> sortedCardValidGroup =null;
    private List<Card> nonJuteCards=null;

    private final int ON_JUTE_GROUP_CARD_SELECTION_TEHNIQUE=ASCENDING_ORDER;



    @Override
    public RulesIface initialize(List<Card> myGroup) {
        CardSorter cardSorter=new CardSorter(myGroup,new CardCompareAsc());
        this.myGroup=cardSorter.getMyGroup();
        for (Card card : this.myGroup) {
            if (cardCounter.containsKey(card.getNumber())) {
                List<Card> cards=cardCounter.get(card.getNumber());
                cards.add(card);
                cardCounter.replace(card.getNumber(),cards);
            } else {
                List<Card> cards=new ArrayList<>(0);
                cards.add(card);
                cardCounter.put(card.getNumber(),cards);
            }
        }
        return this;
    }

    @Override
    public boolean isValid() {
        boolean isValid=false;
        sortedCardValidGroup =new ArrayList<>(0);
        nonJuteCards=new ArrayList<>(0);

        List<Card> singleGroup=new ArrayList<>(0);
        for(Map.Entry<Integer,List<Card>> cardNumber:cardCounter.entrySet()){
            if(cardNumber.getValue().size()< JUTE_SIZE){
                nonJuteCards.add(cardNumber.getValue().get(SINGLE_CARD));
            }
        }
        for(Map.Entry<Integer,List<Card>> cardNumber:cardCounter.entrySet()){
            if(cardNumber.getValue().size()>=JUTE_SIZE){
                int singleGroupEntry=0;
                int wholeGroupSize=cardNumber.getValue().size()/JUTE_SIZE;
                int wholeGroupEntry=0;
                for(Card card:cardNumber.getValue()) {
                    if (wholeGroupEntry != wholeGroupSize) {
                        if (hasNextNonJuteNumber()) {
                            singleGroup.add(card);
                            singleGroupEntry++;
                            if (singleGroupEntry == JUTE_SIZE) {
                                Card sortCard;
                                if (ON_JUTE_GROUP_CARD_SELECTION_TEHNIQUE == ASCENDING_ORDER) {
                                    sortCard = getLowestCardNumberFromNonJute();
                                } else {
                                    sortCard = getHighestCardFromNonJute();
                                }
                                singleGroup.add(sortCard);
                                sortSingleGroupAndAdd(singleGroup);
                                singleGroup = new ArrayList<>(0);
                                wholeGroupEntry++;
                            }
                        }
                    }
                }
                isValid=true;
            }
        }
        sortAndAdd();
        return isValid;
    }

    @Override
    public int countValidOne() {
        int validCounts=0;
        validCounts= sortedCardValidGroup.size();
        return validCounts;
    }

    @Override
    public List<Card> getValidCards() {
        List<Card> onlyValidCards=new ArrayList<>(0);
        for(List<Card> cards: sortedCardValidGroup){
            for(Card card:cards){
                onlyValidCards.add(card);
            }
        }
        return onlyValidCards;
    }


    private void sortSingleGroupAndAdd(List<Card> singleGroup){
        List<Card> temp=new ArrayList<>(0);
        int juteNumber=getJuteNumberFrom(singleGroup);
        for(Card card:singleGroup){
            if(card.getNumber()==juteNumber){
                temp.add(card);
            }
        }
        for (Card card : singleGroup) {
            if (card.getNumber() != juteNumber) {
                temp.add(card);
            }
        }
        sortedCardValidGroup.add(temp);
    }

    private void sortAndAdd(){
        for(int i=0;i< sortedCardValidGroup.size();i++){
            for(int j=0;j< sortedCardValidGroup.size();j++){
                if(getJuteNumberFrom(sortedCardValidGroup.get(i))==getJuteNumberFrom(sortedCardValidGroup.get(j))){
                    if(getNonJuteNumberFrom(sortedCardValidGroup.get(i))>getNonJuteNumberFrom(sortedCardValidGroup.get(j))){
                        List<Card> temp= sortedCardValidGroup.get(i);
                        sortedCardValidGroup.set(i, sortedCardValidGroup.get(j));
                        sortedCardValidGroup.set(j, temp);
                    }
                }
                if(getJuteNumberFrom(sortedCardValidGroup.get(i))>getJuteNumberFrom(sortedCardValidGroup.get(j))){
                    List<Card> temp= sortedCardValidGroup.get(i);
                    sortedCardValidGroup.set(i, sortedCardValidGroup.get(j));
                    sortedCardValidGroup.set(j, temp);
                }
            }
        }
    }

    private Map<Integer,Integer> getJuteCombo(List<Card> cards){
        Map<Integer,Integer> counter=new HashMap<>(0);
        for(Card card:cards){
            if(counter.containsKey(card.getNumber())){
                int previousCount= counter.get(card.getNumber());
                int newCount=previousCount+INCREMENT_SIZE;
                counter.replace(card.getNumber(),newCount);
            }else{
                counter.put(card.getNumber(),INCREMENT_SIZE);
            }
        }
        return counter;
    }

    private int getNonJuteNumberFrom(List<Card> cards){
        int juteNumber=0;
        Map<Integer,Integer> counter=getJuteCombo(cards);
            for(Map.Entry<Integer,Integer> count:counter.entrySet()){
                if(count.getValue()<JUTE_SIZE){
                    juteNumber=count.getKey();
                }
            }
        return juteNumber;
    }

    private int getJuteNumberFrom(List<Card> cards){
        int juteNumber=0;
        Map<Integer,Integer> counter=getJuteCombo(cards);
        if(counter.containsValue(JUTE_SIZE)){
            for(Map.Entry<Integer,Integer> count:counter.entrySet()){
                if(count.getValue()==JUTE_SIZE){
                    juteNumber=count.getKey();
                }
            }
        }
        return juteNumber;
    }

    private Card getHighestCardFromNonJute(){
        Card highestCard=null;
        Collections.sort(nonJuteCards,new CardCompareDesc());
        if(nonJuteCards.size()>0) {
            highestCard=nonJuteCards.get(0);
            nonJuteCards.remove(0);
        }
        return highestCard;
    }
    private Card getLowestCardNumberFromNonJute(){
        Card highestCard=null;
        Collections.sort(nonJuteCards,new CardCompareAsc());
        if(nonJuteCards.size()>0) {
            highestCard=nonJuteCards.get(0);
            nonJuteCards.remove(0);
        }
        return highestCard;
    }

    private boolean hasNextNonJuteNumber() {
        boolean hasNextNonJuteNumber=false;
        if(nonJuteCards.size()!=0){
            hasNextNonJuteNumber=true;
        }
        return hasNextNonJuteNumber;
    }
}
