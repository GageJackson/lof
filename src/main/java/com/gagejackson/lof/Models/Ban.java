package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "ban")
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "champ_id")
    private int champId;

    @Column(name = "pick_turn")
    private int pickTurn;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
