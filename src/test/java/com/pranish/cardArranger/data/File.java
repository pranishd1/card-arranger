package com.pranish.cardArranger.data;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.rules.common.ComboRunner;
import org.junit.Test;

import java.util.List;

/**
 * Created by pranish on 11/17/15.
 */
public class File {

    public static String input="";
    public static String output="";

    public static void ComboRunTest(){
        String cardInput="";
        String cardOutput="";
        try{
            List<Card> allCards= CardConst.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(allCards,3);
            List<Card> myCards=cardFolder.getDivision(allCards,4,13).get(0);
            cardFolder.sortDividedCards(myCards);
            for(Card card:myCards){
//                System.out.print(card.getId() + ",");
                cardInput=cardInput+(card.getId()+",");
            }

            input=input+cardInput.substring(0,cardInput.length()-1)+"\n";
//            System.out.println();
            ComboRunner comboRunner=new ComboRunner(myCards);
            List<Card> cards=comboRunner.getArrangedList();
            for(Card card:cards){
//                System.out.print(card.getId() + ",");
                cardOutput=cardOutput+(card.getId()+",");
            }
            output=output+cardOutput.substring(0,cardOutput.length()-1)+"\n";
//            System.out.println();
//            System.out.println("*******");
//            System.out.println();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void run(){
        for(int i=0;i<500;i++){
            ComboRunTest();
        }
        System.out.println("****");
        System.out.println(input);
        System.out.println("****");
        System.out.println(output);
    }
}
