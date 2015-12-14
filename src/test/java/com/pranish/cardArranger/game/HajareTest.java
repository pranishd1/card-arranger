package com.pranish.cardArranger.game;

import com.pranish.cardArranger.game.Iface.GameMeta;
import com.pranish.cardArranger.game.hajare.Hajare;
import org.junit.Test;

/**
 * Created by pranish on 12/11/15.
 */
public class HajareTest {


    @Test
    public void startTest(){
        Hajare hajare=new Hajare();
        int numberOfPlayer=4;
        try {
            while (!hajare.hasSomeoneGotMoreThanFinalPoint()) {
                hajare.initialize();
                hajare.setNumberOfPlayer(numberOfPlayer);
                hajare.shuffleAndDivideCards();
                hajare.start();
            }
            GameMeta meta=hajare.getMetaInfo();
            System.out.println("Final Winner: ");
            System.out.println(hajare.getWinner().toString()+" With Points: "+hajare.getWinner().getPoints());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
