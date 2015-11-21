package com.pranish.cardArranger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pranish on 11/14/15.
 */
public class Const {
    public static final int TOTAL_CARDS =52;
    public static final int TOTAL_CARD_GROUP =4;
    public static final int STARTING_CARD =1;
    public static final int GROUP_STARTING_CARD =2;
    public static final int SINGLE_GROUP_CARD_SIZE=14;
    public static final int HIGHEST_NUMBER=14;

    private static final int LAST_CARD=1;

    public static List<Card> getAllCards() throws Exception {
        List<Card> allCards=new ArrayList<Card>(TOTAL_CARDS);
        List<CardGroup> allGroups=getAllGroup();
        int count=1;
            for(CardGroup cardGroup:allGroups){
                int temp= SINGLE_GROUP_CARD_SIZE;
                while (temp!=LAST_CARD){
                    Card card=new Card();
                    card.setGroup(cardGroup);
                    card.setNumber(temp);
                    card.setId(count);
                    allCards.add(card);
                    count++;
                    temp--;
            }
        }
        return allCards;
    }

    public static List<Card> getCertainCards(int numberOfCards) throws Exception{
       List<Card> allCards=getAllCards();
        Collections.shuffle(allCards);
        List<Card> newCertainCards=new ArrayList<>(numberOfCards);
        int initialIndex=0;
        while(numberOfCards!=0){
            newCertainCards.add(allCards.get(initialIndex));
            initialIndex++;
            numberOfCards--;
        }
        return newCertainCards;
    }

    public static List<CardGroup> getAllGroup(){
       List<CardGroup> temp=new ArrayList<>(TOTAL_CARD_GROUP);
        temp.add(CardGroup.CLUB);
        temp.add(CardGroup.DIAMOND);
        temp.add(CardGroup.HEART);
        temp.add(CardGroup.SPADE);
        return temp;
    }

    public static String setNameForCard(int number) throws Exception {
        switch (number){
            case 2:
                return 2+"";
            case 3:
                return 3+"";
            case 4:
                return 4+"";
            case 5:
                return 5+"";
            case 6:
                return 6+"";
            case 7:
                return 7+"";
            case 8:
                return 8+"";
            case 9:
                return 9+"";
            case 10:
                return 10+"";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            default:
                throw new Exception("Not a valid Number");
        }
    }

    public static List<Card> removeDuplicates(List<Card> mixedCards){
        List<Card> newFormedCards=new ArrayList<>(0);
        for(Card card:mixedCards){
            boolean isUnique=true;
            for(Card check:newFormedCards){
                if(card.getName().equals(check.getName()) &&
                        card.getGroup().getValue()==check.getGroup().getValue() &&
                           card.getNumber()==check.getNumber()){
                    isUnique=false;
                }
            }
            if(isUnique){
                newFormedCards.add(card);
            }
        }
        return newFormedCards;
    }

    public static List<Card> subtractCards(List<Card> collections,List<Card> subtract){
        List<Card> remainingCards=new ArrayList<>(0);
        for(Card card:collections){
            boolean isUnique=true;
            for(Card check:subtract){
                if(card.getName().equals(check.getName()) &&
                        card.getGroup().getValue()==check.getGroup().getValue() &&
                        card.getNumber()==check.getNumber()){
                    isUnique=false;
                }
            }
            if(isUnique){
                remainingCards.add(card);
            }
        }
        return remainingCards;
    }

    public static int getCardIdFromGroupAndName(CardGroup cardGroup,String name) throws Exception {
        Card temp=null;
        List<Card> allCards=getAllCards();
        for(Card card:allCards){
            if(card.getGroup().getValue()==cardGroup.getValue()
                    && card.getName().equals(name)){
                temp=card;
            }
        }
        return temp.getId();
    }
}
