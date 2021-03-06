package com.pranish.cardArranger.card;

import com.pranish.cardArranger.cardCompare.CardSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CardFolder {
    public void shuffleCard(List<Card> arrangedCard,int times){
        while(times!=0) {
            Collections.shuffle(arrangedCard);
            times--;
        }
    }

    public List<List<Card>> getDivision(List<Card> shuffledCards,int size){
        List<List<Card>> allGroupCollection=new ArrayList<>();
        int count=0;
       for(int i=0;i<size;i++){
           List<Card> singleCollection=new ArrayList<>();
           for(int j=0;j<shuffledCards.size()/size;j++){
               if(count>=shuffledCards.size()){
                   break;
               }
               singleCollection.add(shuffledCards.get(count));
               count++;
           }
        allGroupCollection.add(singleCollection);
      }
        return allGroupCollection;
    }

    public List<List<Card>> getDivision(List<Card> shuffledCards,int divisionSize,int individualSize){
        List<List<Card>> allGroupCollection=new ArrayList<>();
        int count=0;
        for(int i=0;i<divisionSize;i++){
            List<Card> singleCollection=new ArrayList<>();
            for(int j=0;j<individualSize;j++){
                if(count>=shuffledCards.size()){
                    break;
                }
                singleCollection.add(shuffledCards.get(count));
                count++;
            }
            allGroupCollection.add(singleCollection);
        }
        return allGroupCollection;
    }

    public CardSorter sortDividedCards(List<Card> dividedCard){
        CardSorter cardSorter=new CardSorter();
        cardSorter.setMyGroup(dividedCard);
        return cardSorter;
    }

    public ReplaceCards replaceCards(List<Card> collectionCards,List<Card> replaceWith){
        ReplaceCards replaceCards=new ReplaceCards();
        List<Card> newFormedCard=new ArrayList<>(0);
        for(Card replaceCard:replaceWith){
                newFormedCard.add(replaceCard);
        }
        List<Card> leftOutCards= CardConst.subtractCards(collectionCards, replaceWith);
        replaceCards.setLeftOutCards(leftOutCards);
        replaceCards.setRuleArrangedCards(replaceWith);
        for(Card leftCards:leftOutCards){
            if(!replaceWith.contains(leftCards)) {
                newFormedCard.add(leftCards);
            }
        }
        replaceCards.setFormedCards(newFormedCard);
        return replaceCards;
    }

}
