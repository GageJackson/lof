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

    @Column (name = "team_num")
    private int teamNum;

    @Column (name = "win")
    private boolean win;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Objective> objective;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Ban>ban;

    @ManyToOne
    @JoinColumn(name = "matches_id", nullable = false)
    private Match match;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Team() {}

    public Team(long id, int teamNum, boolean win, List<Objective> objective, List<Ban> ban, Match match) {
        this.id = id;
        this.teamNum = teamNum;
        this.win = win;
        this.objective = objective;
        this.ban = ban;
        this.match = match;
    }

    public Team(int teamNum, boolean win, List<Objective> objective, List<Ban> ban, Match match) {
        this.teamNum = teamNum;
        this.win = win;
        this.objective = objective;
        this.ban = ban;
        this.match = match;
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

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamId) {
        this.teamNum = teamId;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public List<Objective> getObjective() {
        return objective;
    }

    public void setObjective(List<Objective> objective) {
        this.objective = objective;
    }

    public List<Ban> getBan() {
        return ban;
    }

    public void setBan(List<Ban> ban) {
        this.ban = ban;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
