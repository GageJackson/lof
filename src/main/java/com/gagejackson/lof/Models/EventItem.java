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
}
