package com.gagejackson.lof.Models.MatchOverview;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column (name = "kills")
    private int kills;
    @Column (name = "deaths")
    private int deaths;
    @Column (name = "assists")
    private int assists;
    @Column (name = "gold")
    private int gold;
    @Column (name = "totalDamage")
    private int totalDamage;
    @Column (name = "magicDamage")
    private int magicDamage;
    @Column (name = "physicalDamage")
    private int physicalDamage;
    @Column (name = "trueDamage")
    private int trueDamage;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Objective> objective;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Ban>ban;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "matches_id", nullable = false)
    private Match match;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Team() {}

    public Team(int teamNum, boolean win, int kills, int deaths, int assists, int gold, int totalDamage, int magicDamage, int physicalDamage, int trueDamage, List<Objective> objective, List<Ban> ban, Match match) {
        this.teamNum = teamNum;
        this.win = win;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.gold = gold;
        this.totalDamage = totalDamage;
        this.magicDamage = magicDamage;
        this.physicalDamage = physicalDamage;
        this.trueDamage = trueDamage;
        this.objective = objective;
        this.ban = ban;
        this.match = match;
    }

    public Team(long id, int teamNum, boolean win, int kills, int deaths, int assists, int gold, int totalDamage, int magicDamage, int physicalDamage, int trueDamage, List<Objective> objective, List<Ban> ban, Match match) {
        this.id = id;
        this.teamNum = teamNum;
        this.win = win;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.gold = gold;
        this.totalDamage = totalDamage;
        this.magicDamage = magicDamage;
        this.physicalDamage = physicalDamage;
        this.trueDamage = trueDamage;
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

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public int getTrueDamage() {
        return trueDamage;
    }

    public void setTrueDamage(int trueDamage) {
        this.trueDamage = trueDamage;
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
