package com.gagejackson.lof.DTOs;


import java.util.List;

public class ParticipantPerk {
    private boolean isPrimary;
    private int style;
    private int perkNum;
    private List<String> perkStats;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantPerk(){}

    public ParticipantPerk(boolean isPrimary, int style, int perkNum) {
        this.isPrimary = isPrimary;
        this.style = style;
        this.perkNum = perkNum;
    }
    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getPerkNum() {
        return perkNum;
    }

    public void setPerkNum(int perkNum) {
        this.perkNum = perkNum;
    }

    public List<String> getPerkStats() {
        return perkStats;
    }

    public void setPerkStats(List<String> perkStats) {
        this.perkStats = perkStats;
    }
}
