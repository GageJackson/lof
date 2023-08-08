package com.gagejackson.lof.Models.MatchOverview;

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


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Ban() {
    }

    public Ban(int champId, int pickTurn, Team team) {
        this.champId = champId;
        this.pickTurn = pickTurn;
        this.team = team;
    }

    public Ban(long id, int champId, int pickTurn, Team team) {
        this.id = id;
        this.champId = champId;
        this.pickTurn = pickTurn;
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

    public int getChampId() {
        return champId;
    }

    public void setChampId(int champId) {
        this.champId = champId;
    }

    public int getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
