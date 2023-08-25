package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

@Entity
@Table(name = "champ_enemy_tips")
public class ChampEnemyTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "tip")
    private String tip;

    @ManyToOne
    @JoinColumn(name = "champion_id", nullable = false)
    private Champion champion;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public ChampEnemyTips() {
    }

    public ChampEnemyTips(String tip, Champion champion) {
        this.tip = tip;
        this.champion = champion;
    }

    public ChampEnemyTips(long id, String tip, Champion champion) {
        this.id = id;
        this.tip = tip;
        this.champion = champion;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }
}
