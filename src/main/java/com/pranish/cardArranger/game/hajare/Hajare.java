package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.game.Iface.GameIface;
import com.pranish.cardArranger.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/27/15.
 */
public class Hajare implements GameIface {
    private static final int TIMES_TO_SHUFFLE=3;
    private int numberOfPlayers=0;
    private List<Player> players;
    private List<Card> allCards;

    CardFolder cardFolder;
    private int manualPlayer;
    Show show;

    @Override
    public void start() {
        autoArrangeCards();
        show=new Show(players);
        show.showCards();
        //show.printWinner();
     }

    @Override
    public void initialize() throws Exception {
        printHeader();
        createCards();
    }

    @Override
    public void setNumberOfPlayer(int numberOfPlayer) {
        numberOfPlayers+=numberOfPlayer;
        createPlayers(numberOfPlayers);
    }

    @Override
    public void setManualNumberOfPlayer(int numberOfPlayer) {
        manualPlayer=numberOfPlayer;
        numberOfPlayers+=manualPlayer;
        createPlayers(numberOfPlayers);
    }

    @Override
    public void shuffleAndDivideCards(){
        int tempHolder=0;
        cardFolder=new CardFolder();
        cardFolder.shuffleCard(allCards,TIMES_TO_SHUFFLE);
        List<List<Card>> dividedCards=cardFolder.getDivision(allCards,numberOfPlayers);
        for(Player player:players){
            List<Card> hold=dividedCards.get(tempHolder);
            player.setMyCard(hold);
            player.setId(tempHolder);
            player.setName(tempHolder+"");
            tempHolder++;
        }
    }

    private void printHeader(){
        System.out.println();
        System.out.println("*****    HAJARE    *****");
        System.out.println();
    }

    private void createCards() throws Exception {
        allCards= CardConst.getAllCards();
    }

    private void createPlayers(int numberOfPlayers){
        players=new ArrayList<>(numberOfPlayers);
        for(int i=0;i<numberOfPlayers;i++){
            players.add(new Player());
        }
    }

    private void autoArrangeCards(){
        for(Player player:players){
            player.arrangeCards();
        }
    }

}
