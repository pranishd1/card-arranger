package com.pranish.cardArranger.player;

/**
 * Created by pranish on 12/11/15.
 */
public enum PlayerStatus {
    AUTO(0),
    MANUAL(1);

    private int value;
    private PlayerStatus(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }

}
