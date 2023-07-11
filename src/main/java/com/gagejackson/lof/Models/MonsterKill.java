package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "monster_kill")
public class MonsterKill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "bounty")
    private int bounty;

    @Column(name = "monster_type", length = 12)
    private String monsterType;

    @Column(name = "monster_subtype", length = 25)
    private String monsterSubtype;

    @Column(name = "position_x")
    private int positionX;

    @Column(name = "position_y")
    private int positionY;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public MonsterKill() {
    }

    public MonsterKill(int bounty, String monsterType, String monsterSubtype, int positionX, int positionY, Event event) {
        this.bounty = bounty;
        this.monsterType = monsterType;
        this.monsterSubtype = monsterSubtype;
        this.positionX = positionX;
        this.positionY = positionY;
        this.event = event;
    }

    public MonsterKill(long id, int bounty, String monsterType, String monsterSubtype, int positionX, int positionY, Event event) {
        this.id = id;
        this.bounty = bounty;
        this.monsterType = monsterType;
        this.monsterSubtype = monsterSubtype;
        this.positionX = positionX;
        this.positionY = positionY;
        this.event = event;
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

    public int getBounty() {
        return bounty;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public String getMonsterSubtype() {
        return monsterSubtype;
    }

    public void setMonsterSubtype(String monsterSubtype) {
        this.monsterSubtype = monsterSubtype;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
