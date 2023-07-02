package com.gagejackson.lof.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column (name = "team_id")
    private int teamId;

    @Column (name = "win")
    private boolean win;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Objective> objective;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Ban>ban;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
}
