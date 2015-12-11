package com.pranish.cardArranger.game;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardGroup;
import com.pranish.cardArranger.game.hajare.HajareConst;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/27/15.
 */
public class HajareCardConstTest {

    @Test
    public void checkRuleNumber() {
        HajareConst hajareConst =new HajareConst();

        List<Card> collection = new ArrayList<>(0);
        try {
            Card card = new Card();
            card.setGroup(CardGroup.SPADE);
            card.setNumber(14);
            collection.add(card);

            Card card1 = new Card();
            card1.setGroup(CardGroup.CLUB);
            card1.setNumber(14);
            collection.add(card1);

            Card card2 = new Card();
            card2.setGroup(CardGroup.SPADE);
            card2.setNumber(13);
            collection.add(card2);

            System.out.println("Rule Number: "+ hajareConst.getCardGroupRuleNumber(collection));

        }catch (Exception e){
            e.printStackTrace();
        }

        }
}
