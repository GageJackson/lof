package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

@Entity
@Table(name = "champ_tags")
public class ChampTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "tag")
    private String tag;

    @ManyToOne
    @JoinColumn(name = "champion_id", nullable = false)
    private Champion champion;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public ChampTags() {
    }

    public ChampTags(String tag, Champion champion) {
        this.tag = tag;
        this.champion = champion;
    }

    public ChampTags(long id, String tag, Champion champion) {
        this.id = id;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }
}
