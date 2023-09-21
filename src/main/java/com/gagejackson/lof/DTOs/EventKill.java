package com.gagejackson.lof.DTOs;

import com.gagejackson.lof.Models.MatchOverview.Participant;

public class EventKill {
    private Participant killer;
    private Participant killed;
    private int posX;
    private int posY;
    private long timestamp;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public EventKill() {
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public Participant getKiller() {
        return killer;
    }

    public void setKiller(Participant killer) {
        this.killer = killer;
    }

    public Participant getKilled() {
        return killed;
    }

    public void setKilled(Participant killed) {
        this.killed = killed;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
