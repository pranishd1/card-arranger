package com.pranish.cardArranger.card;

import java.util.List;

/**
 * Created by pranish on 11/15/15.
 */
public class ReplaceCards {
    private List<Card> ruleArrangedCards;
    private List<Card> leftOutCards;
    private List<Card> formedCards;

    public List<Card> getFormedCards() {
        return formedCards;
    }

    public void setFormedCards(List<Card> formedCards) {
        this.formedCards = formedCards;
    }

    public List<Card> getRuleArrangedCards() {
        return ruleArrangedCards;
    }

    public void setRuleArrangedCards(List<Card> ruleArrangedCards) {
        this.ruleArrangedCards = ruleArrangedCards;
    }

    public List<Card> getLeftOutCards() {
        return leftOutCards;
    }

    public void setLeftOutCards(List<Card> leftOutCards) {
        this.leftOutCards = leftOutCards;
    }

}
