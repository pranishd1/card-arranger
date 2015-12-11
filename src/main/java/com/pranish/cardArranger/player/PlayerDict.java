package com.pranish.cardArranger.player;

import com.pranish.cardArranger.card.Card;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pranish on 12/11/15.
 */
public class PlayerDict{
    private List<Card> myCards;
    private Set<Card> wonCards;
    private List<List<Card>> rounds;


    public PlayerDict() {
        this.myCards=new ArrayList<>(0);
        this.wonCards=new HashSet<>(0);
        this.rounds=new ArrayList<>(0);
    }

    public PlayerDict(List<Card> myCards) {
        this.myCards = myCards;
        this.wonCards=new HashSet<>(0);
    }

    public List<Card> getWonCards() {
        List<Card> temp=new ArrayList<>(0);
        for(Card card:wonCards){
            temp.add(card);
        }
        return temp;
    }

    public void addWonCards(List<Card> wonCards) {
        for(Card card:wonCards){
            this.wonCards.add(card);
        }
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public void setMyCards(List<Card> myCards) {
        this.myCards = myCards;
    }

    public List<List<Card>> getRounds() {
        return rounds;
    }

    public void addRounds(List<Card> rounds) {
        this.rounds.add(rounds);
    }

}
