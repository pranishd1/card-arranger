package com.pranish.cardArranger.player;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.rules.common.ComboRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class Player {
    private int id;
    private String name;
    private List<Card> myCard;
    private PlayerStatus myStatus;
    private final int NUMBER_OF_CARDS_TO_SHOW=3;

    private ComboRunner comboRunner;
    private PlayerDict playerDict;

    public Player(){
        playerDict=new PlayerDict();
    }

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
         return myCard;
    }

    public List<Card> arrangeCards(){
        comboRunner=new ComboRunner(myCard);
        myCard.clear();
        myCard=comboRunner.getArrangedList();
        return myCard;
    }

    public void setMyCard(List<Card> myCard) {
        this.myCard = myCard;
        arrangeCards();
        playerDict.setMyCards(myCard);
        determineCardRounds(this.myCard);
    }

    public PlayerDict getPlayerDict(){
        return playerDict;
    }

    private void determineCardRounds(List<Card> myCard) {
        int numberOfRounds=myCard.size()/NUMBER_OF_CARDS_TO_SHOW;
        for(int i=0;i<numberOfRounds;i++){
            List<Card> newList=new ArrayList<>(0);
            for(int j=0;j<NUMBER_OF_CARDS_TO_SHOW;j++){
                int index=getCardIndex(i,j);
                System.out.println("Index: "+index);
                newList.add(myCard.get(index));
            }
            if(myCard.size()%NUMBER_OF_CARDS_TO_SHOW!=0 && i==numberOfRounds-1){
                int index=getCardIndex(numberOfRounds-1,NUMBER_OF_CARDS_TO_SHOW);
                System.out.println("Indexss: "+index);
                newList.add(myCard.get(index));
            }
            playerDict.addRounds(newList);
        }
    }


    private void printMyCards(){
        for(Card card:myCard){
            System.out.println(card.toString());
        }
    }

    private int getCardIndex(int round,int index){
        return (round*NUMBER_OF_CARDS_TO_SHOW)+index;
    }

    private void printRoundCards(){
        int i=1;
        for(List<Card> cards:playerDict.getRounds()){
            System.out.println("------------------------");
            System.out.println("Round : "+i);
            for(Card card:cards){
                System.out.println(card.toString());
            }
            i++;
            System.out.println("------------------------");
        }
    }

    @Override
    public String toString(){
        //printRoundCards();
        return "Id: "+id+" Name: "+name;
    }

}
