package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "friend_rank")
public class FriendRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "tier", length = 12)
    private String tier; //longest is "Grand Master" = 12

    @Column (name = "ranking", length = 5)
    private String rank; //longest is "THREE" = 5

    @Column (name = "league_points")
    private int leaguePoints;

    @OneToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Friend friend;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public FriendRank(){}

    public FriendRank(String tier, String ranking, int leaguePoints, Friend friend) {
        this.tier = tier;
        this.rank = ranking;
        this.leaguePoints = leaguePoints;
        this.friend = friend;
    }

    public FriendRank(long id, String tier, String ranking, int leaguePoints, Friend friend) {
        this.id = id;
        this.tier = tier;
        this.rank = ranking;
        this.leaguePoints = leaguePoints;
        this.friend = friend;
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

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRanking() {
        return rank;
    }

    public void setRanking(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
