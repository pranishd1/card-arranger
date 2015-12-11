package com.pranish.cardArranger.cardCompare;

import com.pranish.cardArranger.card.Card;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pranish on 11/15/15.
 */
public class CardSorter  {
    private List<Card> myGroup;

    public CardSorter(){

    }
    public CardSorter(List<Card> myGroup,Comparator<Card> comparator){
        this.myGroup=myGroup;
        Collections.sort(this.myGroup,comparator);
    }

    public List<Card> getMyGroup() {
        return myGroup;
    }

    public List<Card> setMyGroup(List<Card> myGroup) {
        this.myGroup = myGroup;
        return toAscending();
    }

    public List<Card> toAscending(){
        Collections.sort(myGroup, new CardCompareAsc());
        return myGroup;
    }
    public List<Card> toDescending(){
        Collections.sort(myGroup, new CardCompareDesc());
        return myGroup;
    }

    public List<Card> toCustomComparator(Comparator<Card> comparator){
        Collections.sort(myGroup,comparator);
        return myGroup;
    }

}
