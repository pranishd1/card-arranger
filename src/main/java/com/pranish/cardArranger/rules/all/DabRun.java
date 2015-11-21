package com.pranish.cardArranger.rules.all;

import com.pranish.cardArranger.Card;
import com.pranish.cardArranger.CardGroup;
import com.pranish.cardArranger.cardCompare.CardGroupDesc;
import com.pranish.cardArranger.cardCompare.CardSorter;
import com.pranish.cardArranger.rules.CardNumbers;
import com.pranish.cardArranger.rules.RulesAbs;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 11/15/15.
 */
public class DabRun extends RulesAbs {
    private final int SINGLE_GROUP_SIZE=3;

    private List<Card> myGroup=new ArrayList<>(0);
    private Map<CardGroup,Map<Card,Integer>> validCounter=new HashMap<>(0);
    private Map<Integer,CardGroup> validCardIds=null;
    private List<Card> filteredCards=null;
    private List<List<Card>> sortedCardValidGroup =null;

    @Override
    public RulesIface initialize(List<Card> myGroup) {
        CardSorter cardSorter=new CardSorter(myGroup,new CardGroupDesc());
        this.myGroup=cardSorter.getMyGroup();
        for(Card card:this.myGroup){
            if(validCounter.containsKey(card.getGroup())){
                Map<Card,Integer> holder=validCounter.get(card.getGroup());
                holder.put(card, card.getNumber());
                validCounter.replace(card.getGroup(),holder);
            }else{
                Map<Card,Integer> holder=new HashMap<>(0);
                holder.put(card, card.getNumber());
                validCounter.put(card.getGroup(),holder);
            }
        }
        return this;
    }

    @Override
    public boolean isValid() {
       boolean isValid=false;
        sortedCardValidGroup =new ArrayList<>(0);
        filteredCards=new ArrayList<>(0);
        validCardIds=new HashMap<>(0);

       for(Map.Entry<CardGroup,Map<Card,Integer>> cardGroup:validCounter.entrySet() ){
           if(cardGroup.getValue().size()>=SINGLE_GROUP_SIZE){
                if(cardGroup.getValue().containsValue(CardNumbers.getAce())&&
                        cardGroup.getValue().containsValue(CardNumbers.getTwo())&&
                        cardGroup.getValue().containsValue(CardNumbers.getThree())) {
                    if (!validCardIds.containsKey(CardNumbers.getAce()) &&
                            !validCardIds.containsKey(CardNumbers.getTwo()) &&
                            !validCardIds.containsKey(CardNumbers.getThree())) {
                        filteredCards = new ArrayList<>(0);
                        addCards(cardGroup.getKey(), CardNumbers.getAce());
                        addCards(cardGroup.getKey(), CardNumbers.getTwo());
                        addCards(cardGroup.getKey(), CardNumbers.getThree());
                        sortedCardValidGroup.add(filteredCards);
                        isValid = true;
                    }
                }

            for(Map.Entry<Card,Integer> card:cardGroup.getValue().entrySet()){
                if(cardGroup.getValue().containsValue(card.getValue()-1) &&
                        cardGroup.getValue().containsValue(card.getValue()-2)){
                    if(!validCardIds.containsKey(card.getValue()) &&
                            !validCardIds.containsKey(card.getValue() - 1) &&
                            !validCardIds.containsKey(card.getValue() - 2)){
                        filteredCards=new ArrayList<>(0);
                        addCards(cardGroup.getKey(), card.getValue());
                        addCards(cardGroup.getKey(), card.getValue() - 1);
                        addCards(cardGroup.getKey(),card.getValue()-2);
                        sortedCardValidGroup.add(filteredCards);
                        isValid=true;
                    }
                }
            }
           }
       }
        sortAndAdd();
        return isValid;
    }

   @Override
    public int countValidOne() {
        return sortedCardValidGroup.size();
    }

    @Override
    public List<Card> getValidCards() {
        List<Card> onlyValidCards=new ArrayList<>(0);
        for(List<Card> cards: sortedCardValidGroup) {
            for (Card card : cards) {
                onlyValidCards.add(card);
            }
        }
        return onlyValidCards;
    }

    private Card getCardByGroupAndNumber(CardGroup cardGroup ,int number){
        for(Card card:myGroup){
            if(card.getGroup().getValue()==cardGroup.getValue() && card.getNumber()==number){
                return card;
            }
        }
        return new Card();
    }

    private void addCards(CardGroup cardGroup,int cardNumber){
        validCardIds.put(cardNumber, cardGroup);
        filteredCards.add(getCardByGroupAndNumber(cardGroup,cardNumber));
    }

    private void sortAndAdd(){
        for(int i=0;i< sortedCardValidGroup.size();i++){
            for(int j=0;j< sortedCardValidGroup.size();j++){
                if(sortedCardValidGroup.get(i).get(0).getNumber()> sortedCardValidGroup.get(j).get(0).getNumber()){
                    List<Card> temp= sortedCardValidGroup.get(i);
                    sortedCardValidGroup.set(i, sortedCardValidGroup.get(j));
                    sortedCardValidGroup.set(j,temp);
                }
            }
        }
    }
}
