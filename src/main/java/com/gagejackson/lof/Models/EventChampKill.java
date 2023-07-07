package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_elite_monster_kill")
public class EventChampKill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "bounty")
    private int bounty;

    @Column(name = "shutdown_bounty")
    private int shutdownBounty;

    @Column(name = "kill_streak_length")
    private int killStreakLength;

    @Column(name = "position_x")
    private int positionX;

    @Column(name = "position_y")
    private int positionY;

    @ManyToOne
    @JoinColumn(name = "victim_id", nullable = false)
    private Participant victim;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public EventChampKill() {
    }

    public EventChampKill(int bounty, int shutdownBounty, int killStreakLength, int positionX, int positionY, Participant victim, Event event) {
        this.bounty = bounty;
        this.shutdownBounty = shutdownBounty;
        this.killStreakLength = killStreakLength;
        this.positionX = positionX;
        this.positionY = positionY;
        this.victim = victim;
        this.event = event;
    }

    public EventChampKill(long id, int bounty, int shutdownBounty, int killStreakLength, int positionX, int positionY, Participant victim, Event event) {
        this.id = id;
        this.bounty = bounty;
        this.shutdownBounty = shutdownBounty;
        this.killStreakLength = killStreakLength;
        this.positionX = positionX;
        this.positionY = positionY;
        this.victim = victim;
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

    public int getShutdownBounty() {
        return shutdownBounty;
    }

    public void setShutdownBounty(int shutdownBounty) {
        this.shutdownBounty = shutdownBounty;
    }

    public int getKillStreakLength() {
        return killStreakLength;
    }

    public void setKillStreakLength(int killStreakLength) {
        this.killStreakLength = killStreakLength;
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

    public Participant getVictim() {
        return victim;
    }

    public void setVictim(Participant victim) {
        this.victim = victim;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}