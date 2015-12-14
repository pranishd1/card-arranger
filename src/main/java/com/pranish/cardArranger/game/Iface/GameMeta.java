package com.pranish.cardArranger.game.Iface;

import com.pranish.cardArranger.player.Player;

import java.util.List;

/**
 * Created by pranish on 12/14/15.
 */
public interface GameMeta {
    public void setGame(GameIface iface);
    public List<Player> getAllPlayers();
    public List<Player> getManualPlayers();
    public List<Player> getAutoPlayers();
    public int getCurrentRoundNumber();
    public int getCurrentShuffleNumber();
}
