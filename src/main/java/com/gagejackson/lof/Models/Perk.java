package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "perk")
public class Perk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "is_primary")
    private boolean isPrimary;

    @Column (name = "style_id")
    private int style;

    @Column (name = "perk_id")
    private int perkId;

    @Column (name = "perk_stat1")
    private int perkStat1;

    @Column (name = "perk_stat2")
    private int perkStat2;

    @Column (name = "perk_stat3")
    private int perkStat3;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;
}
