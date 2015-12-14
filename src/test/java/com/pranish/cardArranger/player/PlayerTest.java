package com.pranish.cardArranger.player;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.card.CardFolder;
import org.junit.Test;

import java.util.List;

/**
 * Created by pranish on 12/14/15.
 */
public class PlayerTest {
    @Test
    public void createPlayerTest(){
        try {
            List<Card> cards= CardConst.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(cards,3);
            List<Card> dividedCardsFirstRound=cardFolder.getDivision(cards, 4).get(0);
            System.out.println("---First Shuffle Insert--------");
            for(Card card:dividedCardsFirstRound){
                System.out.println(card.toString());
            }



            List<Card> dividedCardsSecondRound=cardFolder.getDivision(cards, 4).get(1);
            System.out.println("---Second Shuffle Insert--------");
            for(Card card:dividedCardsFirstRound){
                System.out.println(card.toString());
            }
            Player player=new Player();
            player.setId(1);
            player.setName("Test");
            player.setMyCards(dividedCardsFirstRound);
            player.setMyCards(dividedCardsSecondRound);

            List<Card> firstRound=player.getPlayerDict().get(0).getMyCards();
            System.out.println("---First Shuffle Player Retrieve --------");
            for(Card card:firstRound){
                System.out.println(card.toString());
            }
            System.out.println("First Round Odd Card:");
            System.out.println(firstRound.get(firstRound.size()-1).toString());

            List<Card> secondRound=player.getPlayerDict().get(1).getMyCards();
            System.out.println("---Second Shuffle Player Retrieve --------");
            for(Card card:secondRound){
                System.out.println(card.toString());
            }
            System.out.println("Second Round Odd Card:");
            System.out.println(secondRound.get(firstRound.size()-1).toString());
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("Player Retrieval");

            System.out.println("First Round");
            System.out.println("Size: "+player.getPlayerDict().get(0).getOddCards().size());
            System.out.println(player.getPlayerDict().get(0).getOddCards().get(0));


            System.out.println("Second Round");
            System.out.println("Size: "+player.getPlayerDict().get(1).getOddCards().size());
            System.out.println(player.getPlayerDict().get(1).getOddCards().get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void playerDictFromPlayerTest(){
        List<Card> cards= null;
        try {
            cards = CardConst.getAllCards();
            CardFolder cardFolder=new CardFolder();
            cardFolder.shuffleCard(cards,3);
            List<Card> dividedCardsFirstRound=cardFolder.getDivision(cards, 4).get(0);
            List<Card> dividedCardsSecondRound=cardFolder.getDivision(cards, 4).get(1);

            Player player=new Player();
            player.setId(1);
            player.setName("Test");
            player.setMyCards(dividedCardsFirstRound);
            player.setMyCards(dividedCardsSecondRound);

            System.out.println(player.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
