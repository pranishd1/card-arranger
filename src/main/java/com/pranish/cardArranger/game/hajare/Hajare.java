package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.game.Iface.GameIface;
import com.pranish.cardArranger.game.Iface.GameMeta;
import com.pranish.cardArranger.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/27/15.
 */
public class Hajare implements GameIface {
    private static final int TIMES_TO_SHUFFLE=3;
    private int roundHighestPlayerPoint=0;
    private int numberOfPlayers=0;
    private List<Player> players;
    private List<Card> allCards;

    private int wholeCardShuffle=0;

    CardFolder cardFolder;
    private int manualPlayer;
    Show show;

    public Hajare(){
        players=new ArrayList<>(0);
    }

    @Override
    public void start() {

            autoArrangeCards();
            show = new Show(wholeCardShuffle,players);
            show.showCards();
            show.distributeWonCards();
            roundHighestPlayerPoint=show.getMaxPointUpdate();
            List<Player> winners = show.getWinnerPlayers();
            System.out.println("This Shuffle Points:");
            for(Player player:players){
                System.out.println(player.toString()+" = "+player.getThisRoundPoints());
            }
            System.out.println("Whole Game Points:");
            for (Player player : players) {
                System.out.println(player.toString() + " = " + player.getPoints());
            }
        //show.printWinner();
        wholeCardShuffle++;
     }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public GameMeta getMetaInfo() {
        GameMeta gameMeta=new HajareMeta();
        gameMeta.setGame(this);
        return gameMeta;
    }

    @Override
    public void initialize() throws Exception {
        printHeader();
        createCards();
        roundHighestPlayerPoint=0;
    }

    @Override
    public void setNumberOfPlayer(int numberOfPlayer) {
        numberOfPlayers=numberOfPlayer;
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
            player.setMyCards(hold);
            tempHolder++;
        }
    }

    public boolean hasSomeoneGotMoreThanFinalPoint(){
            if(roundHighestPlayerPoint>=HajareConst.getFinalPoint()){
                return true;
            }
        return false;
    }

    public Player getWinner(){
        return show.getFinalWinner();
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
        if(players.isEmpty()) {
            players = new ArrayList<>(numberOfPlayers);
            for (int i = 0; i < numberOfPlayers; i++) {
                Player player = new Player();
                player.setId(i);
                player.setName(i + "");
                players.add(player);
            }
        }
    }

    private void autoArrangeCards(){
        for(Player player:players){
            player.arrangeCards();
        }
    }

}
