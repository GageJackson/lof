package com.gagejackson.lof.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "timestamp")
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventItem> eventItem;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventSkillUp> eventSkillUp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    private List<EventLevelUp> eventLevelUp;
//
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
//    private List<Event> event;
//
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
//    private List<Event> event;
//
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
//    private List<Event> event;
//
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
//    private List<Event> event;
//
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
//    private List<Event> event;
}
