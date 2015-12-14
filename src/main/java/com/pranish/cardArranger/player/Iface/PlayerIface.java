package com.pranish.cardArranger.player.Iface;

import com.pranish.cardArranger.card.Card;

import java.util.List;

/**
 * Created by pranish on 12/14/15.
 */
public interface PlayerIface {
    public void setId(int id);
    public int getId();
    public void setName(String name);
    public String getName();
    public void setMyCards(List<Card> myCard);
    public List<Card> getMyCards();
    public int getPoints();
}
