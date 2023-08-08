package com.gagejackson.lof.Models.MatchOverview;

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


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Objective() {
    }

    public Objective(String name, boolean first, int kills, Team team) {
        this.name = name;
        this.first = first;
        this.kills = kills;
        this.team = team;
    }

    public Objective(long id, String name, boolean first, int kills, Team team) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.kills = kills;
        this.team = team;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
