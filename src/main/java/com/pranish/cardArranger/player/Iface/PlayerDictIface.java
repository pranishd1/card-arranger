package com.pranish.cardArranger.player.Iface;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 12/14/15.
 */
public interface PlayerDictIface {
    public void setMyCards(List<Card> myCards);
    public List<Card> getMyCards();
    public void addWonCards(List<Card> wonCards);
    public List<Card> getWonCards();
    public int getPoints();
}
