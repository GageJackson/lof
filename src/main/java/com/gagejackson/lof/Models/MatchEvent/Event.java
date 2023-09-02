package com.gagejackson.lof.Models.MatchEvent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gagejackson.lof.Models.MatchOverview.Participant;
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
    @JsonIgnore
    private Participant participant;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private EventItem eventItem;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private SkillUp skillUp;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private LevelUp levelUp;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private ChampKill champKill;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private BuildingKill buildingKill;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private MonsterKill monsterKill;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "event")
    private SpecialKill specialKill;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Event() {
    }

    public Event(long timestamp, Participant participant, EventItem eventItem, SkillUp skillUp, LevelUp levelUp, ChampKill champKill, BuildingKill buildingKill, MonsterKill monsterKill, SpecialKill specialKill) {
        this.timestamp = timestamp;
        this.participant = participant;
        this.eventItem = eventItem;
        this.skillUp = skillUp;
        this.levelUp = levelUp;
        this.champKill = champKill;
        this.buildingKill = buildingKill;
        this.monsterKill = monsterKill;
        this.specialKill = specialKill;
    }

    public Event(long id, long timestamp, Participant participant, EventItem eventItem, SkillUp skillUp, LevelUp levelUp, ChampKill champKill, BuildingKill buildingKill, MonsterKill monsterKill, SpecialKill specialKill) {
        this.id = id;
        this.timestamp = timestamp;
        this.participant = participant;
        this.eventItem = eventItem;
        this.skillUp = skillUp;
        this.levelUp = levelUp;
        this.champKill = champKill;
        this.buildingKill = buildingKill;
        this.monsterKill = monsterKill;
        this.specialKill = specialKill;
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

    public EventItem getEventItem() {
        return eventItem;
    }

    public void setEventItem(EventItem eventItem) {
        this.eventItem = eventItem;
    }

    public SkillUp getSkillUp() {
        return skillUp;
    }

    public void setSkillUp(SkillUp skillUp) {
        this.skillUp = skillUp;
    }

    public LevelUp getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(LevelUp levelUp) {
        this.levelUp = levelUp;
    }

    public ChampKill getChampKill() {
        return champKill;
    }

    public void setChampKill(ChampKill champKill) {
        this.champKill = champKill;
    }

    public BuildingKill getBuildingKill() {
        return buildingKill;
    }

    public void setBuildingKill(BuildingKill buildingKill) {
        this.buildingKill = buildingKill;
    }

    public MonsterKill getMonsterKill() {
        return monsterKill;
    }

    public void setMonsterKill(MonsterKill monsterKill) {
        this.monsterKill = monsterKill;
    }

    public SpecialKill getSpecialKill() {
        return specialKill;
    }

    public void setSpecialKill(SpecialKill specialKill) {
        this.specialKill = specialKill;
    }
}
