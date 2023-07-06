package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_level_up")
public class EventLevelUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
