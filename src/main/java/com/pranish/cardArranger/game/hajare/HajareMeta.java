package com.pranish.cardArranger.game.hajare;

import com.pranish.cardArranger.game.Iface.GameIface;
import com.pranish.cardArranger.game.Iface.GameMeta;
import com.pranish.cardArranger.player.Player;
import com.pranish.cardArranger.player.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranish on 12/14/15.
 */
public class HajareMeta implements GameMeta{
    private GameIface gameIface;
    private PlayerStatusDiff playerStatusDiff;
    @Override
    public void setGame(GameIface iface) {
        this.gameIface=iface;

        initialize();
    }

    @Override
    public List<Player> getAllPlayers() {
       return gameIface.getPlayers();
    }

    @Override
    public List<Player> getManualPlayers() {
       return playerStatusDiff.getManual();
    }

    @Override
    public List<Player> getAutoPlayers() {
        return playerStatusDiff.getAuto();
    }

    @Override
    public int getCurrentRoundNumber() {
        return 0;
    }

    @Override
    public int getCurrentShuffleNumber() {
        return 0;
    }

    private void initialize(){
        playerStatusDiff=new PlayerStatusDiff();
        List<Player> allPlayers=gameIface.getPlayers();
        List<Player> manualPlayer=new ArrayList<>(0);
        List<Player> autoPlayer=new ArrayList<>(0);
        for(Player player:allPlayers){
            if(player.getMyStatus()== PlayerStatus.MANUAL){
                manualPlayer.add(player);
            }else if(player.getMyStatus()==PlayerStatus.AUTO){
                autoPlayer.add(player);
            }
        }
        playerStatusDiff.setAuto(autoPlayer);
        playerStatusDiff.setManual(manualPlayer);
    }
}

class PlayerStatusDiff{
    private List<Player> manual;
    private List<Player> auto;

    public List<Player> getManual() {
        return manual;
    }

    public void setManual(List<Player> manual) {
        this.manual = manual;
    }

    public List<Player> getAuto() {
        return auto;
    }

    public void setAuto(List<Player> auto) {
        this.auto = auto;
    }
}
