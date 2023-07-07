package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_building_kill")
public class EventBuildingKill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "bounty")
    private int bounty;

    @Column(name = "lane_type", length = 12)
    private String laneType;

    @Column(name = "building_type", length = 25)
    private String buildingType;

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
    public EventBuildingKill() {
    }

    public EventBuildingKill(int bounty, String laneType, String buildingType, int positionX, int positionY, Event event) {
        this.bounty = bounty;
        this.laneType = laneType;
        this.buildingType = buildingType;
        this.positionX = positionX;
        this.positionY = positionY;
        this.event = event;
    }

    public EventBuildingKill(long id, int bounty, String laneType, String buildingType, int positionX, int positionY, Event event) {
        this.id = id;
        this.bounty = bounty;
        this.laneType = laneType;
        this.buildingType = buildingType;
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

    public String getLaneType() {
        return laneType;
    }

    public void setLaneType(String laneType) {
        this.laneType = laneType;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
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
