package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.card.Card;
import com.pranish.cardArranger.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 12/11/15.
 */
public class Show {
    private List<Player> players;
    private HajareConst hajareConst;

    private final int CONDITION_FIRST_CARD_SHOW_WINS=1;
    private final int CONDTION_LAST_CARD_SHOW_WINS=2;

    private int ruleFollowed=CONDITION_FIRST_CARD_SHOW_WINS;
    private static int round=0;
    private int numberOfRounds=0;

    private int shuffleRound=0;

    private List<Player> roundWinner;

    public Show(int shuffleRound,List<Player> players){
        this.shuffleRound=shuffleRound;
        this.players=players;
        roundWinner=new ArrayList<>(0);
        numberOfRounds=this.players.get(0).getPlayerDict().get(this.shuffleRound).getRounds().size();
        hajareConst=new HajareConst();
    }

    public void showCards(){
        System.out.println("Shuffle: "+(shuffleRound+1));
        for(int round=0;round<numberOfRounds;round++) {
            System.out.println();
            System.out.println("Round: "+(round+1));
            System.out.println();
            for(Player player:players) {
                System.out.println("----------------------");
                System.out.println(player.toString());
                for (Card card : player.getPlayerDict().get(shuffleRound).getRounds().get(round)) {
                    System.out.println(card.toString());
                }
                System.out.println("----------------------");
            }

            Player winner=players.get(0);
            for(int player=0;player<players.size();player++){
               int result= hajareConst.getCompareResult(winner.getPlayerDict().get(shuffleRound).getRounds().get(round),
                        players.get(player).getPlayerDict().get(shuffleRound).getRounds().get(round));
                if(result==HajareConst.getIsSmaller()){
                    winner=players.get(player);
                }
                if(result==HajareConst.getIsIdentical()){
                    int innerResult=hajareConst.getIntegerCompareResult(
                            winner.getPlayerDict().get(shuffleRound)
                                    .getOddCards().get(0)
                                    .getNumber(),
                            players.get(player).getPlayerDict().get(shuffleRound).getOddCards().get(0).getNumber());
                    if(innerResult==HajareConst.getIsSmaller()){
                        winner=players.get(player);
                    }else if(innerResult==HajareConst.getIsIdentical() && ruleFollowed==CONDTION_LAST_CARD_SHOW_WINS){
                        winner=players.get(player);
                    }
                }
            }
            addWinner(winner);
        }
        printWinner();
    }

    public void distributeWonCards(){
        int lastRound=roundWinner.size()-1;
        for(int round=0;round<roundWinner.size();round++) {
            for (Player player : players) {
                if(roundWinner.get(round).getId()==player.getId()){
                    for(Player wonCards:players){
                        player.getPlayerDict().get(shuffleRound).addWonCards(wonCards.getPlayerDict().get(shuffleRound).getRounds().get(round));

                        if(round==lastRound){
                            player.getPlayerDict().get(shuffleRound).addWonCards(wonCards.getPlayerDict().get(shuffleRound).getOddCards());
                        }
                    }
                }
            }
        }
    }

    public List<Player> getWinnerPlayers(){
        List<Player> temp=new ArrayList<>(0);
        for(Player player:roundWinner){
            if(!temp.contains(player)){
                temp.add(player);
            }
        }
        return temp;
    }

    public Player getFinalWinner(){
        int max=0;
        Player tempPlayer=players.get(0);
        for(Player player:players){
            if(player.getPoints()>max){
                tempPlayer=player;
                max=player.getPoints();
            }
        }
        return tempPlayer;
    }

    private void addWinner(Player player){
        roundWinner.add(player);
    }

    private void printWinner() {
        int round=1;
        for(int i=0;i<roundWinner.size();i++){
            System.out.println("Round "+round+" Winner : -------> "+roundWinner.get(i).toString());
            round++;
        }
    }

    public int getMaxPointUpdate() {
        int max=0;
        Player tempPlayer=players.get(0);
        for(Player player:players){
            if(player.getPoints()>max){
                tempPlayer=player;
                max=player.getPoints();
            }
        }
        return max;

    }
}
/* System.out.println();
            System.out.println("Round: "+(round+1));
            System.out.println();
            for(Player player:players){
                System.out.println("----------------------");
                System.out.println(player.toString());
                for(Card card:player.getPlayerDict().getRounds().get(round)){
                    System.out.println(card.toString());
                }
                System.out.println("----------------------");
            }*/

