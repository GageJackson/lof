package com.gagejackson.lof.DTOs;

import java.util.List;

public class ParticipantSet {
    private String setName;
    private String section1Name;
    private String section2Name;
    private String section1IconName;
    private String section2IconName;
    private List<ParticipantStat> section1Stats;
    private List<ParticipantStat> section2Stats;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantSet(){}
    public ParticipantSet(String setName){
        this.setName = setName;
    }
    public ParticipantSet(String setName, String section1Name, String section2Name){
        this.setName = setName;
        this.section1Name = section1Name;
        this.section2Name = section2Name;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSection1Name() {
        return section1Name;
    }

    public void setSection1Name(String section1Name) {
        this.section1Name = section1Name;
    }

    public String getSection2Name() {
        return section2Name;
    }

    public void setSection2Name(String section2Name) {
        this.section2Name = section2Name;
    }

    public String getSection1IconName() {
        return section1IconName;
    }

    public void setSection1IconName(String section1IconName) {
        this.section1IconName = section1IconName;
    }

    public String getSection2IconName() {
        return section2IconName;
    }

    public void setSection2IconName(String section2IconName) {
        this.section2IconName = section2IconName;
    }

    public List<ParticipantStat> getSection1Stats() {
        return section1Stats;
    }

    public void setSection1Stats(List<ParticipantStat> section1Stats) {
        this.section1Stats = section1Stats;
    }

    public List<ParticipantStat> getSection2Stats() {
        return section2Stats;
    }

    public void setSection2Stats(List<ParticipantStat> section2Stats) {
        this.section2Stats = section2Stats;
    }
}
