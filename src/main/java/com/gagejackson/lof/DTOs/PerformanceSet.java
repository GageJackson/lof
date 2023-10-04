package com.gagejackson.lof.DTOs;

import java.util.List;

public class PerformanceSet {
    private  String groupName;
    private List<PerformanceStat> performanceStats;

    public PerformanceSet(){}

    public PerformanceSet(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<PerformanceStat> getPerformanceStats() {
        return performanceStats;
    }

    public void setPerformanceStats(List<PerformanceStat> performanceStats) {
        this.performanceStats = performanceStats;
    }
}
