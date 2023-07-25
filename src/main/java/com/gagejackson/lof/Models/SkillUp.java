package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "skill_up")
public class SkillUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "skill_slot")
    private int skillSlot;

    @Column(name = "level_up_type", length = 15)
    private String levelUpType;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public SkillUp() {
    }

    public SkillUp(int skillSlot, String levelUpType, Event event) {
        this.skillSlot = skillSlot;
        this.levelUpType = levelUpType;
        this.event = event;
    }

    public SkillUp(long id, int skillSlot, String levelUpType, Event event) {
        this.id = id;
        this.skillSlot = skillSlot;
        this.levelUpType = levelUpType;
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

    public int getSkillSlot() {
        return skillSlot;
    }

    public void setSkillSlot(int skillSlot) {
        this.skillSlot = skillSlot;
    }

    public String getLevelUpType() {
        return levelUpType;
    }

    public void setLevelUpType(String levelUpType) {
        this.levelUpType = levelUpType;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
