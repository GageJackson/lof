package com.gagejackson.lof.Models.MatchEvent;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class EventItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "item_num")
    private int itemNum;

    @Column(name = "item_type", length = 20)
    private String itemType;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public EventItem() {
    }

    public EventItem(int itemNum, String itemType, Event event) {
        this.itemNum = itemNum;
        this.itemType = itemType;
        this.event = event;
    }

    public EventItem(long id, int itemNum, String itemType, Event event) {
        this.id = id;
        this.itemNum = itemNum;
        this.itemType = itemType;
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

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemId) {
        this.itemNum = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
