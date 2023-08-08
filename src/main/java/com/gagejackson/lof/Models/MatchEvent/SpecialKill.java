package com.gagejackson.lof.Models.MatchEvent;

import jakarta.persistence.*;

@Entity
@Table(name = "special_kill")
public class SpecialKill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "kill_type", length = 25)
    private String killType;

    @Column(name = "multi_kill_length")
    private int multiKillLength;

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
    public SpecialKill() {
    }

    public SpecialKill(String killType, int multiKillLength, int positionX, int positionY, Event event) {
        this.killType = killType;
        this.multiKillLength = multiKillLength;
        this.positionX = positionX;
        this.positionY = positionY;
        this.event = event;
    }

    public SpecialKill(long id, String killType, int multiKillLength, int positionX, int positionY, Event event) {
        this.id = id;
        this.killType = killType;
        this.multiKillLength = multiKillLength;
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

    public String getKillType() {
        return killType;
    }

    public void setKillType(String killType) {
        this.killType = killType;
    }

    public int getMultiKillLength() {
        return multiKillLength;
    }

    public void setMultiKillLength(int multiKillLength) {
        this.multiKillLength = multiKillLength;
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
