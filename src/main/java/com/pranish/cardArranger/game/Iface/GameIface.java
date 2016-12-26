package com.pranish.cardArranger.game.Iface;

import com.pranish.cardArranger.player.Player;

import java.util.List;

/**
 * Created by pranish on 11/27/15.
 */
public interface GameIface {
    public void initialize() throws Exception;
    public void setNumberOfPlayer(int numberOfPlayer);
    public List<Player> setNumberOfManualPlayer(int numberOfPlayer);
    public void shuffleAndDivideCards();
    public void start();
    public List<Player> getPlayers();
    public GameMeta getMetaInfo();
    public boolean isManualPlayerReady();
}
