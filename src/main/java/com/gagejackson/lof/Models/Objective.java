package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "objective")
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first")
    private boolean first;

    @Column(name = "kills")
    private int kills;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
