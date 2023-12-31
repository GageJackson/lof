package com.gagejackson.lof.DTOs;

import com.gagejackson.lof.Models.MatchEvent.EventItem;
import com.gagejackson.lof.Models.MatchEvent.SkillUp;
import com.gagejackson.lof.Models.MatchOverview.Participant;
import com.gagejackson.lof.Models.MatchOverview.ParticipantFrame;
import jakarta.persistence.Column;

import java.util.List;

public class ParticipantDTO {
    private Participant participant;
    private List<ParticipantFrame> participantFrames;

    //Participant Events
    private List<List<EventItem>> eventItems;
    private List<SkillUp> skillUps;

    //Participant Stat Sets
    private List<ParticipantSet> participantSets;

    private List<ParticipantPerk> participantPerks;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantDTO() {
    }

    public ParticipantDTO(Participant participant, List<ParticipantFrame> participantFrames, List<List<EventItem>> eventItems, List<SkillUp> skillUps, List<ParticipantSet> participantSets, List<ParticipantPerk> participantPerks) {
        this.participant = participant;
        this.participantFrames = participantFrames;
        this.eventItems = eventItems;
        this.skillUps = skillUps;
        this.participantSets = participantSets;
        this.participantPerks = participantPerks;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<ParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    public void setParticipantFrames(List<ParticipantFrame> participantFrames) {
        this.participantFrames = participantFrames;
    }

    public List<List<EventItem>> getEventItems() {
        return eventItems;
    }

    public void setEventItems(List<List<EventItem>> eventItems) {
        this.eventItems = eventItems;
    }

    public List<SkillUp> getSkillUps() {
        return skillUps;
    }

    public void setSkillUps(List<SkillUp> skillUps) {
        this.skillUps = skillUps;
    }

    public List<ParticipantSet> getParticipantSets() {
        return participantSets;
    }

    public void setParticipantSets(List<ParticipantSet> participantSets) {
        this.participantSets = participantSets;
    }

    public List<ParticipantPerk> getParticipantPerks() {
        return participantPerks;
    }

    public void setParticipantPerks(List<ParticipantPerk> participantPerks) {
        this.participantPerks = participantPerks;
    }
}
