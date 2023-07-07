package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_item")
public class EventItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_type", length = 10)
    private String itemType;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public EventItem() {
    }

    public EventItem(int itemId, String itemType, Event event) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.event = event;
    }

    public EventItem(long id, int itemId, String itemType, Event event) {
        this.id = id;
        this.itemId = itemId;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
