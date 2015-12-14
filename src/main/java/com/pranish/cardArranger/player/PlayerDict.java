package com.pranish.cardArranger.player;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.game.hajare.HajareConst;
import com.pranish.cardArranger.player.Iface.PlayerDictIface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class PlayerDict implements PlayerDictIface{
    private List<Card> myCards;
    private List<List<Card>> wonCards;
    private List<List<Card>> rounds;
    private List<Card> oddCards;
    private int points;

    public PlayerDict() {
        initialize();
    }

    public PlayerDict(List<Card> myCards) {
        initialize();
        this.myCards=myCards;
    }

    @Override
    public List<Card> getWonCards() {
        List<Card> temp=new ArrayList<>(0);
        for(List<Card> cards:wonCards){
            for(Card card:cards){
                temp.add(card);
            }
        }
        return temp;
    }

    @Override
    public void addWonCards(List<Card> wonCards) {
        this.wonCards.add(wonCards);
    }

    @Override
    public List<Card> getMyCards() {
        return myCards;
    }

    @Override
    public void setMyCards(List<Card> myCards) {
        this.myCards = myCards;
    }

    @Override
    public int getPoints() {
        determinePoints();
        return points;
    }

    public List<List<Card>> getRounds() {
        return rounds;
    }

    public void addRounds(List<Card> rounds) {
        this.rounds.add(rounds);
    }

    public List<Card> getOddCards() {
        return oddCards;
    }

    public void addOddCard(Card oddCard) {
        this.oddCards.add(oddCard);
    }

    private void initialize(){
        this.myCards=new ArrayList<>(0);
        this.wonCards=new ArrayList<>(0);
        this.rounds=new ArrayList<>(0);
        this.oddCards=new ArrayList<>(0);
    }

    private void determinePoints() {
        points=0;
        points= HajareConst.countHajereGamePointsFromListOfList(wonCards);
    }
}
