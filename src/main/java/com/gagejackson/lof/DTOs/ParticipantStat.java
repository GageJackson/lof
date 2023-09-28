package com.gagejackson.lof.DTOs;

import java.util.List;

public class ParticipantStat {
    private String statName;
    private List<String> stats;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantStat(String statName){
        this.statName = statName;
    }

    public ParticipantStat(){}


    /*////////////////////////////////////////////////////////////////
    GETTERS $ SETTERS
    ////////////////////////////////////////////////////////////////*/
    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public List<String> getStats() {
        return stats;
    }

    public void setStats(List<String> stats) {
        this.stats = stats;
    }
}
