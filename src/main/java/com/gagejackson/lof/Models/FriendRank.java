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

    @Column (name = "ranking", length = 3)
    private String rank; //longest is "iii" = 3

    @Column (name = "league_points")
    private int leaguePoints;

    @Column (name = "rank_wins")
    private int rankWins;

    @Column (name = "rank_losses")
    private int rankLosses;

    @Column (name = "hot_streak")
    private boolean onHotStreak;

    @OneToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Friend friend;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public FriendRank(){}

    public FriendRank(String tier, String rank, int leaguePoints, int rankWins, int rankLosses, boolean onHotstreak, Friend friend) {
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.rankWins = rankWins;
        this.rankLosses = rankLosses;
        this.onHotStreak = onHotStreak;
        this.friend = friend;
    }

    public FriendRank(long id, String tier, String rank, int leaguePoints, int rankWins, int rankLosses, boolean onHotstreak, Friend friend) {
        this.id = id;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.rankWins = rankWins;
        this.rankLosses = rankLosses;
        this.onHotStreak = onHotStreak;
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

    public int getRankWins() {
        return rankWins;
    }

    public void setRankWins(int rankWins) {
        this.rankWins = rankWins;
    }

    public int getRankLosses() {
        return rankLosses;
    }

    public void setRankLosses(int rankLosses) {
        this.rankLosses = rankLosses;
    }

    public boolean getOnHotStreak() {
        return onHotStreak;
    }

    public void setOnHotStreak(boolean onHotStreak) {
        this.onHotStreak = onHotStreak;
    }
}
