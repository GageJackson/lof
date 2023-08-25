package com.gagejackson.lof.Models.Friend;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "icon", nullable = false)
    private int icon;

    @Column(name = "summoner_level", nullable = false)
    private int summonerLevel;

    @Column(name = "summoner_id", nullable = false, length = 60)
    private String summonerId;

    @Column(name = "puu_id", nullable = false, length = 100)
    private String puuId;

    @Column(name = "account_id", nullable = false, length = 60)
    private String accountId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friend")
    List<FriendMatch> friendMatch;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Friend() {}

    public Friend(String name, int icon, int summonerLevel, String summonerId, String puuId, String accountId) {
        this.name = name;
        this.icon = icon;
        this.summonerLevel = summonerLevel;
        this.summonerId = summonerId;
        this.puuId = puuId;
        this.accountId = accountId;
    }

    public Friend(long id, String name, int icon, int summonerLevel, String summonerId, String puuId, String accountId) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.summonerLevel = summonerLevel;
        this.summonerId = summonerId;
        this.puuId = puuId;
        this.accountId = accountId;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getPuuId() {
        return puuId;
    }

    public void setPuuId(String puuId) {
        this.puuId = puuId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
