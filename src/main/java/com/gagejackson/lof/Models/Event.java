package com.gagejackson.lof.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "timestamp")
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventItem> eventItem;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventSkillUp> eventSkillUp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventLevelUp> eventLevelUp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventChampKill> eventChampKill;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventBuildingKill> eventBuildingKill;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventEliteMonsterKill> eventEliteMonsterKill;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventChampSpecialKill> eventChampSpecialKill;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Event() {
    }

    public Event(long timestamp, Participant participant, List<EventItem> eventItem, List<EventSkillUp> eventSkillUp, List<EventLevelUp> eventLevelUp, List<EventChampKill> eventChampKill, List<EventBuildingKill> eventBuildingKill, List<EventEliteMonsterKill> eventEliteMonsterKill, List<EventChampSpecialKill> eventChampSpecialKill) {
        this.timestamp = timestamp;
        this.participant = participant;
        this.eventItem = eventItem;
        this.eventSkillUp = eventSkillUp;
        this.eventLevelUp = eventLevelUp;
        this.eventChampKill = eventChampKill;
        this.eventBuildingKill = eventBuildingKill;
        this.eventEliteMonsterKill = eventEliteMonsterKill;
        this.eventChampSpecialKill = eventChampSpecialKill;
    }

    public Event(long id, long timestamp, Participant participant, List<EventItem> eventItem, List<EventSkillUp> eventSkillUp, List<EventLevelUp> eventLevelUp, List<EventChampKill> eventChampKill, List<EventBuildingKill> eventBuildingKill, List<EventEliteMonsterKill> eventEliteMonsterKill, List<EventChampSpecialKill> eventChampSpecialKill) {
        this.id = id;
        this.timestamp = timestamp;
        this.participant = participant;
        this.eventItem = eventItem;
        this.eventSkillUp = eventSkillUp;
        this.eventLevelUp = eventLevelUp;
        this.eventChampKill = eventChampKill;
        this.eventBuildingKill = eventBuildingKill;
        this.eventEliteMonsterKill = eventEliteMonsterKill;
        this.eventChampSpecialKill = eventChampSpecialKill;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<EventItem> getEventItem() {
        return eventItem;
    }

    public void setEventItem(List<EventItem> eventItem) {
        this.eventItem = eventItem;
    }

    public List<EventSkillUp> getEventSkillUp() {
        return eventSkillUp;
    }

    public void setEventSkillUp(List<EventSkillUp> eventSkillUp) {
        this.eventSkillUp = eventSkillUp;
    }

    public List<EventLevelUp> getEventLevelUp() {
        return eventLevelUp;
    }

    public void setEventLevelUp(List<EventLevelUp> eventLevelUp) {
        this.eventLevelUp = eventLevelUp;
    }

    public List<EventChampKill> getEventChampKill() {
        return eventChampKill;
    }

    public void setEventChampKill(List<EventChampKill> eventChampKill) {
        this.eventChampKill = eventChampKill;
    }

    public List<EventBuildingKill> getEventBuildingKill() {
        return eventBuildingKill;
    }

    public void setEventBuildingKill(List<EventBuildingKill> eventBuildingKill) {
        this.eventBuildingKill = eventBuildingKill;
    }

    public List<EventEliteMonsterKill> getEventEliteMonsterKill() {
        return eventEliteMonsterKill;
    }

    public void setEventEliteMonsterKill(List<EventEliteMonsterKill> eventEliteMonsterKill) {
        this.eventEliteMonsterKill = eventEliteMonsterKill;
    }

    public List<EventChampSpecialKill> getEventChampSpecialKill() {
        return eventChampSpecialKill;
    }

    public void setEventChampSpecialKill(List<EventChampSpecialKill> eventChampSpecialKill) {
        this.eventChampSpecialKill = eventChampSpecialKill;
    }
}
