package com.pranish.cardArranger.game;

import com.pranish.cardArranger.game.Iface.GameMeta;
import com.pranish.cardArranger.game.hajare.Hajare;
import com.pranish.cardArranger.player.Player;

import java.util.List;

/**
 * Created by pranish on 12/15/15.
 */
public class HajareManualPlayerTest {

    public static void main(String[] args){
        Hajare hajare=new Hajare();
        int numberOfAllPlayer=4;
        List<Player> manualPlayer=hajare.setNumberOfManualPlayer(1);
        try {

                hajare.initialize();
                hajare.setNumberOfPlayer(numberOfAllPlayer);
                hajare.shuffleAndDivideCards();
                if(hajare.isManualPlayerReady()) {
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

