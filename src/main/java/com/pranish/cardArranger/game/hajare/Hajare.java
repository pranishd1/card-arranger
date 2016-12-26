package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.card.CardConst;
import com.pranish.cardArranger.card.CardFolder;
import com.pranish.cardArranger.game.Iface.GameIface;
import com.pranish.cardArranger.game.Iface.GameMeta;
import com.pranish.cardArranger.player.Player;
import com.pranish.cardArranger.player.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 11/27/15.
 */
public class Hajare implements GameIface {

    private static final int TIMES_TO_SHUFFLE=3;

    private int roundHighestPlayerPoint=0;
    private int numberOfPlayers=0;
    private int wholeCardShuffle=0;

    private List<Player> manualPlayers;
    private List<Player> autoPlayers;
    private List<Player> allPlayers;
    private List<Card> allCards;

    private CardFolder cardFolder;
    private Show show;
    private GameMeta gameMeta;

    public Hajare(){
        autoPlayers =new ArrayList<>(0);
        manualPlayers=new ArrayList<>(0);
        allPlayers=new ArrayList<>(0);
    }

    @Override
    public void start() {

            autoArrangeCards();
            show = new Show(wholeCardShuffle, getPlayers());
            show.showCards();
            show.distributeWonCards();
            roundHighestPlayerPoint=show.getMaxPointUpdate();
            List<Player> winners = show.getWinnerPlayers();
            System.out.println("This Shuffle Points:");
            for(Player player: autoPlayers){
                System.out.println(player.toString()+" = "+player.getThisRoundPoints());
            }
            System.out.println("Whole Game Points:");
            for (Player player : autoPlayers) {
                System.out.println(player.toString() + " = " + player.getPoints());
            }
        //show.printWinner();
        wholeCardShuffle++;
     }

    @Override
    public List<Player> getPlayers() {
        combineAllPlayers();
        return allPlayers;
    }

    @Override
    public GameMeta getMetaInfo() {
        gameMeta=new HajareMeta();
        gameMeta.setGame(this);
        return gameMeta;
    }

    @Override
    public boolean isManualPlayerReady() {
        return false;
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
        createAutoPlayers(numberOfPlayers);
        combineAllPlayers();
    }

    @Override
    public List<Player> setNumberOfManualPlayer(int numberOfPlayer) {
      manualPlayers=new ArrayList<>(0);
        for(int i=0;i<numberOfPlayer;i++){
            Player player=new Player();
            player.setMyStatus(PlayerStatus.MANUAL);
            player.setId(i);
            player.setName(i + "");
            manualPlayers.add(player);
       }
        return manualPlayers;
    }

    @Override
    public void shuffleAndDivideCards(){
        int tempHolder=0;
        cardFolder=new CardFolder();
        cardFolder.shuffleCard(allCards,TIMES_TO_SHUFFLE);
        List<List<Card>> dividedCards=cardFolder.getDivision(allCards,numberOfPlayers);

        for(Player player: allPlayers){
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

    private void createAutoPlayers(int numberOfPlayers){
        if(autoPlayers.isEmpty()) {
            autoPlayers = new ArrayList<>(numberOfPlayers);
            for (int i = 0; i < numberOfPlayers; i++) {
                Player player = new Player();
                player.setId(i);
                player.setName(i + "");
                player.setMyStatus(PlayerStatus.AUTO);
                autoPlayers.add(player);
            }
        }
    }

    private void autoArrangeCards(){
        for(Player player: autoPlayers){
            player.arrangeCards();
        }
    }

    private void combineAllPlayers(){
        allPlayers.clear();
        for(Player player:manualPlayers){
            allPlayers.add(player);
        }
        for(Player player:autoPlayers){
            allPlayers.add(player);
        }
    }

}
