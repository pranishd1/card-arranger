package com.pranish.cardArranger;

import org.junit.Test;

import java.util.List;

/**
 * Created by pranish on 11/14/15.
 */
public class CardTest {
    @Test
    public void getCards(){
        try {
            System.out.println("-----------------------");
            for(Card card:Const.getAllCards()){
                System.out.println(card.toString());
            }
            System.out.println("-----------------------");
            List<Card> allCards=Const.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            /*for(Card card:allCards){
                System.out.println(card.toString());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDivisionTest(){
        try {
           List<Card> allCards=Const.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(allCards, 3);
            List<List<Card>> dividedCard=cardFolder.getDivision(allCards,4);
            for(List<Card> group:dividedCard){
                System.out.println("-----BEFORE SORT--------------");
                for(Card card:group){
                    System.out.println(" Number: "+card.getNumber()+" Name: "+card.getName()+" Group: "+card.getGroup());
                }
                System.out.println("------AFTER SORT--------------");
                cardFolder.sortDividedCards(group);
                for(Card card:group){
                    System.out.println(" Number: "+card.getNumber()+" Name: "+card.getName()+" Group: "+card.getGroup());
                }
                System.out.println("------------------------------");
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AllCardSeparationTest(){
        try{
            List<Card> allCards=Const.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(allCards,3);
           List<Card> myCards=cardFolder.getDivision(allCards,4).get(0);
            cardFolder.sortDividedCards(myCards).toDescending();
            for(Card card:myCards){
                System.out.println(" Number: "+card.getNumber()+" Name: "+card.getName()+" Group: "+card.getGroup());
            }
        }catch (Exception e){
e.printStackTrace();
        }
    }

    @Test
    public void OnlyGivenNumberOfCardSeparationTest(){
        try{
            List<Card> allCards=Const.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(allCards,3);
            List<Card> myCards=cardFolder.getDivision(allCards,4,3).get(0);
            cardFolder.sortDividedCards(myCards).toDescending();
            for(Card card:myCards){
                System.out.println(" Number: "+card.getNumber()+" Name: "+card.getName()+" Group: "+card.getGroup());
            }
        }catch (Exception e){
        e.printStackTrace();
        }
    }

}
