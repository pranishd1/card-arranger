package com.pranish.cardArranger.game.Iface;

/**
 * Created by pranish on 11/27/15.
 */
public interface GameIface {
    public void initialize() throws Exception;
    public void setNumberOfPlayer(int numberOfPlayer);
    public void setManualNumberOfPlayer(int numberOfPlayer);
    public void shuffleAndDivideCards();
    public void start();
}
