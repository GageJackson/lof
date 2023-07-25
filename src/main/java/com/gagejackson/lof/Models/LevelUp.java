package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "level_up")
public class LevelUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public LevelUp() {
    }

    public LevelUp(int level, Event event) {
        this.level = level;
        this.event = event;
    }

    public LevelUp(long id, int level, Event event) {
        this.id = id;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
