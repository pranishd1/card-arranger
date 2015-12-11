package com.pranish.cardArranger.rules.common;

/**
 * Created by pranish on 11/27/15.
 */
public enum RuleNumber {
    THIRIAL(0),
    DAABRUN(1),
    RUN(2),
    FALASH(3),
    JUTE(4),
    SORT(5);

    int value;

    private RuleNumber(int i){
        value=i;
    }

    public int getValue() {
        return value;
    }

    public static RuleNumber findFromValue(int value){
        RuleNumber ruleNumber=null;
        switch (value){
            case 0:
                ruleNumber=RuleNumber.THIRIAL;
                break;
            case 1:
                ruleNumber=RuleNumber.DAABRUN;
                break;
            case 2:
                ruleNumber=RuleNumber.RUN;
                break;
            case 3:
                ruleNumber=RuleNumber.FALASH;
                break;
            case 4:
                ruleNumber=RuleNumber.JUTE;
                break;
            case 5:
                ruleNumber=RuleNumber.SORT;
                break;
        }
        return ruleNumber;
    }

}
