package com.pranish.cardArranger.rules.all;

import com.pranish.cardArranger.Card;
import com.pranish.cardArranger.CardGroup;
import com.pranish.cardArranger.cardCompare.CardCompareDesc;
import com.pranish.cardArranger.cardCompare.CardGroupDesc;
import com.pranish.cardArranger.cardCompare.CardSorter;
import com.pranish.cardArranger.rules.RulesAbs;
import com.pranish.cardArranger.rules.RulesIface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranish on 11/15/15.
 */
public class Falash extends RulesAbs {
    private final int SINGLE_GROUP_SIZE=3;

    private List<Card> myGroup=null;
    private Map<CardGroup,Map<Card,Integer>> validCounter=new HashMap<>(0);
    private List<Card> validCardIds=null;
    private List<List<Card>> sortedValidList=null;
    private CardSorter cardSorter=null;


    @Override
    public RulesIface initialize(List<Card> myGroup) {
        cardSorter=new CardSorter(myGroup,new CardGroupDesc());
        this.myGroup=cardSorter.getMyGroup();

        for(Card card:this.myGroup){
            if(validCounter.containsKey(card.getGroup())){
                Map<Card,Integer> holder=validCounter.get(card.getGroup());
                holder.put(card,card.getNumber());
                validCounter.replace(card.getGroup(),holder);
            }else{
                Map<Card,Integer> holder=new HashMap<>(0);
                holder.put(card,card.getNumber());
                validCounter.put(card.getGroup(),holder);
            }
        }
        return this;
    }

    @Override
    public boolean isValid() {
        boolean isValid=false;
        validCardIds=new ArrayList<>(0);
        sortedValidList=new ArrayList<>(0);

        for(Map.Entry<CardGroup,Map<Card,Integer>> entry:validCounter.entrySet()){
            if(entry.getValue().size()>=SINGLE_GROUP_SIZE){
                int wholeEntryGroup=0;
                int singleEntryGroup=0;
                int NumberOfCardGroupSize=entry.getValue().size()-entry.getValue().size()%SINGLE_GROUP_SIZE;
                for(Map.Entry<Card,Integer> cardIdAndCount:entry.getValue().entrySet()){
                    if(wholeEntryGroup!=NumberOfCardGroupSize){
                        validCardIds.add(cardIdAndCount.getKey());
                        wholeEntryGroup++;
                        singleEntryGroup++;
                        if(singleEntryGroup==SINGLE_GROUP_SIZE){
                             cardSorter=new CardSorter(validCardIds,new CardCompareDesc());
                            List<Card> refined=cardSorter.getMyGroup();
                            sortedValidList.add(refined);
                            validCardIds=new ArrayList<>(0);
                            singleEntryGroup=0;
                        }
                    }
                }
                isValid= true;
            }
        }
        sortAndAdd();
        return isValid;
    }

    private void sortAndAdd(){
        for(int i=0;i<sortedValidList.size();i++){
            for(int j=0;j<sortedValidList.size();j++){
                boolean found=false;
                int k=0;
              while(!found){
                  if(k==SINGLE_GROUP_SIZE){
                      break;
                  }
                    if(sortedValidList.get(i).get(k).getNumber()==sortedValidList.get(j).get(k).getNumber()){
                        k++;
                    }else{
                        if(sortedValidList.get(i).get(k).getNumber()>sortedValidList.get(j).get(k).getNumber()){
                            List<Card> temp=sortedValidList.get(i);
                            sortedValidList.set(i, sortedValidList.get(j));
                            sortedValidList.set(j, temp);
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int countValidOne() {
        int totalValidCounts=0;
        for(Map.Entry<CardGroup,Map<Card,Integer>> entry:validCounter.entrySet()){
            if(entry.getValue().size()>=SINGLE_GROUP_SIZE){
                totalValidCounts+=entry.getValue().size()/SINGLE_GROUP_SIZE;
            }
        }
        return totalValidCounts;
    }

    @Override
    public List<Card> getValidCards() {
        List<Card> onlyValidCards=new ArrayList<>(0);
        for(List<Card> cards:sortedValidList){
            for(Card card:cards){
                onlyValidCards.add(card);
            }
        }
        return onlyValidCards;
    }
}
