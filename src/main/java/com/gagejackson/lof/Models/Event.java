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
    private List<Item> item;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<SkillUp> skillUp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<LevelUp> levelUp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<ChampKill> champKill;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<BuildingKill> buildingKill;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<MonsterKill> monsterKill;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<SpecialKill> specialKill;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Event() {
    }

    public Event(long timestamp, Participant participant, List<Item> item, List<SkillUp> skillUp, List<LevelUp> levelUp, List<ChampKill> champKill, List<BuildingKill> buildingKill, List<MonsterKill> monsterKill, List<SpecialKill> specialKill) {
        this.timestamp = timestamp;
        this.participant = participant;
        this.item = item;
        this.skillUp = skillUp;
        this.levelUp = levelUp;
        this.champKill = champKill;
        this.buildingKill = buildingKill;
        this.monsterKill = monsterKill;
        this.specialKill = specialKill;
    }

    public Event(long id, long timestamp, Participant participant, List<Item> item, List<SkillUp> skillUp, List<LevelUp> levelUp, List<ChampKill> champKill, List<BuildingKill> buildingKill, List<MonsterKill> monsterKill, List<SpecialKill> specialKill) {
        this.id = id;
        this.timestamp = timestamp;
        this.participant = participant;
        this.item = item;
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

    public List<Item> getEventItem() {
        return item;
    }

    public void setEventItem(List<Item> item) {
        this.item = item;
    }

    public List<SkillUp> getEventSkillUp() {
        return skillUp;
    }

    public void setEventSkillUp(List<SkillUp> skillUp) {
        this.skillUp = skillUp;
    }

    public List<LevelUp> getEventLevelUp() {
        return levelUp;
    }

    public void setEventLevelUp(List<LevelUp> levelUp) {
        this.levelUp = levelUp;
    }

    public List<ChampKill> getEventChampKill() {
        return champKill;
    }

    public void setEventChampKill(List<ChampKill> champKill) {
        this.champKill = champKill;
    }

    public List<BuildingKill> getEventBuildingKill() {
        return buildingKill;
    }

    public void setEventBuildingKill(List<BuildingKill> buildingKill) {
        this.buildingKill = buildingKill;
    }

    public List<MonsterKill> getEventEliteMonsterKill() {
        return monsterKill;
    }

    public void setEventEliteMonsterKill(List<MonsterKill> monsterKill) {
        this.monsterKill = monsterKill;
    }

    public List<SpecialKill> getEventChampSpecialKill() {
        return specialKill;
    }

    public void setEventChampSpecialKill(List<SpecialKill> specialKill) {
        this.specialKill = specialKill;
    }
}
