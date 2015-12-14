package com.pranish.cardArranger.player;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.player.Iface.PlayerIface;
import com.pranish.cardArranger.rules.common.ComboRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class Player implements PlayerIface{
    private int id;
    private String name;
    private List<Card> myCard;
    private PlayerStatus myStatus;
    private final int NUMBER_OF_CARDS_TO_SHOW=3;

    private ComboRunner comboRunner;
    private List<PlayerDict> playerDicts;

    public Player(){
        playerDicts=new ArrayList<>(0);
        this.myCard=new ArrayList<>(0);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setMyCards(List<Card> myCard) {
        this.myCard.clear();
        this.myCard = myCard;
        arrangeCards();
        PlayerDict playerDict=new PlayerDict();
        playerDict.setMyCards(this.myCard);
        playerDicts.add(playerDict);
        determineCardRounds(this.myCard);
    }

    @Override
    public List<Card> getMyCards() {
         return myCard;
    }

    @Override
    public int getPoints(){
        int temp=0;
        for(PlayerDict playerDict:playerDicts){
            temp+=playerDict.getPoints();
        }
        return temp;
    }

    public List<Card> arrangeCards(){
        comboRunner=new ComboRunner(myCard);
        myCard.clear();
        myCard=comboRunner.getArrangedList();
        return myCard;
    }

    public List<PlayerDict> getPlayerDict(){
        return playerDicts;
    }

    public int getThisRoundPoints(){
        int lastIndex=playerDicts.size()-1;
        return playerDicts.get(lastIndex).getPoints();
    }

    private void determineCardRounds(List<Card> myCard) {
        int newPlayerDict=playerDicts.size()-1;

        //System.out.println("New Player Dict Index: "+newPlayerDict);

        int numberOfRounds=myCard.size()/NUMBER_OF_CARDS_TO_SHOW;
        for(int i=0;i<numberOfRounds;i++){
            List<Card> newList=new ArrayList<>(0);
            for(int j=0;j<NUMBER_OF_CARDS_TO_SHOW;j++){
                int index=getCardIndex(i,j);
                newList.add(myCard.get(index));
            }

            if(myCard.size()%NUMBER_OF_CARDS_TO_SHOW!=0 && i==numberOfRounds-1){
                int index=getCardIndex(numberOfRounds-1,NUMBER_OF_CARDS_TO_SHOW);
                //System.out.println("Indexss: "+index);
                //newList.add(myCard.get(index));
                playerDicts.get(newPlayerDict).addOddCard(myCard.get(index));
            }
            playerDicts.get(newPlayerDict).addRounds(newList);
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

    private void printRoundCards() {

        int shuffleCount=1;
        for (PlayerDict shuffle : playerDicts) {
            int i = 1;
            System.out.println("------------*********************---------------");
            System.out.println();
            System.out.println("Shuffle : "+shuffleCount);
            for (List<Card> cards : shuffle.getRounds()) {
                System.out.println("------------------------");
                System.out.println("Round : " + i);
                for (Card card : cards) {
                    System.out.println(card.toString());
                }
                System.out.println("Odd Cards: ");
                for(Card card:shuffle.getOddCards()){
                    System.out.println(card.toString());
                }
                i++;

                System.out.println("------------------------");
            }

            shuffleCount++;
        }
    }

    public PlayerStatus getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(PlayerStatus myStatus) {
        this.myStatus = myStatus;
    }

    @Override
    public String toString(){
       // printRoundCards();
        return "Id: "+id+" Name: "+name;
    }

}
