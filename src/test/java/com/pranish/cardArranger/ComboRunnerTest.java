package com.pranish.cardArranger;

import com.pranish.cardArranger.rules.common.ComboRunner;
import org.junit.Test;

import java.util.List;

/**
 * Created by pranish on 11/17/15.
 */
public class ComboRunnerTest {
    @Test
    public void ComboRunTest(){
        try{
            List<Card> allCards=Const.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(allCards,3);
            List<Card> myCards=cardFolder.getDivision(allCards,4,9).get(0);
            cardFolder.sortDividedCards(myCards);
            System.out.println("----------------OLD CARDS-------------------------");
            for(Card card:myCards){
                System.out.println(" Number: "+card.getNumber()+" Name: "+card.getName()+" Group: "+card.getGroup());
            }
            ComboRunner comboRunner=new ComboRunner(myCards);
            List<Card> cards=comboRunner.getArrangedList();
            System.out.println("----------------FORMED CARDS-------------------------");
            for(Card card:cards){
                System.out.println(" Number: "+card.getNumber()+" Name: "+card.getName()+" Group: "+card.getGroup());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
