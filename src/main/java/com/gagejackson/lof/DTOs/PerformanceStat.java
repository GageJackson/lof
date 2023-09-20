package com.gagejackson.lof.DTOs;

import java.util.List;

public class PerformanceStat {
    private String statName;
    private int redTeamTotal;
    private int blueTeamTotal;
    private int highestPlayerTotal;
    private List<Integer> playerTotals;

    public PerformanceStat() {
    }

    public PerformanceStat(String statName) {
        this.statName = statName;
    }

    public PerformanceStat(String statName, int redTeamTotal, int blueTeamTotal, int highestPlayerTotal, List<Integer> playerTotals) {
        this.statName = statName;
        this.redTeamTotal = redTeamTotal;
        this.blueTeamTotal = blueTeamTotal;
        this.highestPlayerTotal = highestPlayerTotal;
        this.playerTotals = playerTotals;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public int getRedTeamTotal() {
        return redTeamTotal;
    }

    public void setRedTeamTotal(int redTeamTotal) {
        this.redTeamTotal = redTeamTotal;
    }

    public int getBlueTeamTotal() {
        return blueTeamTotal;
    }

    public void setBlueTeamTotal(int blueTeamTotal) {
        this.blueTeamTotal = blueTeamTotal;
    }

    public int getHighestPlayerTotal() {
        return highestPlayerTotal;
    }

    public void setHighestPlayerTotal(int highestPlayerTotal) {
        this.highestPlayerTotal = highestPlayerTotal;
    }

    public List<Integer> getPlayerTotals() {
        return playerTotals;
    }

    public void setPlayerTotals(List<Integer> playerTotals) {
        this.playerTotals = playerTotals;
    }
}
