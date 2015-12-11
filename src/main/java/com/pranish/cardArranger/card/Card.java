package com.pranish.cardArranger.card;

/**
 * Created by pranish on 11/14/15.
 */
public class Card {
    private int id;
    private int number;
    private String name;
    private CardGroup group;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public Card setNumber(int number) throws Exception {
        this.number = number;
        this.name= CardConst.setNameForCard(number);
        return this;
    }

    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public CardGroup getGroup() {
        return group;
    }

    public Card setGroup(CardGroup group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return (" ID : " + this.id + " Number: " + this.number + " Name: " + this.name + " Group: " + this.group);
    }
}


