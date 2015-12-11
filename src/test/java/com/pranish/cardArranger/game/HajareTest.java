package com.pranish.cardArranger.game;

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
            hajare.initialize();
            hajare.setNumberOfPlayer(numberOfPlayer);
            hajare.shuffleAndDivideCards();
            hajare.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
