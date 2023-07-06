package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_skill_up")
public class EventSkillUp {
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
}
