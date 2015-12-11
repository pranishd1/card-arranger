package com.pranish.cardArranger.player;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.rules.common.ComboRunner;

import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class Player {
    private int id;
    private String name;
    private List<Card> myCard;
    private PlayerStatus myStatus;

    private ComboRunner comboRunner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getMyCard() {
        myCard=arrangeCards();
        return myCard;
    }

    public List<Card> arrangeCards(){
        comboRunner=new ComboRunner(myCard);
        myCard.clear();
        return comboRunner.getArrangedList();
    }

    public void setMyCard(List<Card> myCard) {
        this.myCard = myCard;
    }

    @Override
    public String toString(){
        printMyCards();
        return "Id: "+id+" Name: "+name;
    }

    private void printMyCards(){
        for(Card card:myCard){
            System.out.println(card.toString());
        }
    }
}
