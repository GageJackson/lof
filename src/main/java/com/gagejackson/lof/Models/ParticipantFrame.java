package com.gagejackson.lof.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "participant_frames")
public class ParticipantFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "current_gold")
    private int currentGold;

    @Column (name = "jungle_minions_killed")
    private int jungleMinionsKilled;

    @Column (name = "level")
    private int level;

    @Column (name = "minions_killed")
    private int minionsKilled;

    @Column (name = "time_enemy_spent_controlled")
    private int timeEnemySpentControlled;

    @Column (name = "total_gold")
    private int totalGold;

    @Column (name = "xp")
    private int xp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "participant_frames")
    private List<ParticipantFrameChamp> champStats;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "participant_frames")
    private List<ParticipantFrameDamage> damageStats;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantFrame() {
    }

    public ParticipantFrame(int currentGold, int jungleMinionsKilled, int level, int minionsKilled, int timeEnemySpentControlled, int totalGold, int xp, List<ParticipantFrameChamp> champStats, List<ParticipantFrameDamage> damageStats) {
        this.currentGold = currentGold;
        this.jungleMinionsKilled = jungleMinionsKilled;
        this.level = level;
        this.minionsKilled = minionsKilled;
        this.timeEnemySpentControlled = timeEnemySpentControlled;
        this.totalGold = totalGold;
        this.xp = xp;
        this.champStats = champStats;
        this.damageStats = damageStats;
    }

    public ParticipantFrame(long id, int currentGold, int jungleMinionsKilled, int level, int minionsKilled, int timeEnemySpentControlled, int totalGold, int xp, List<ParticipantFrameChamp> champStats, List<ParticipantFrameDamage> damageStats) {
        this.id = id;
        this.currentGold = currentGold;
        this.jungleMinionsKilled = jungleMinionsKilled;
        this.level = level;
        this.minionsKilled = minionsKilled;
        this.timeEnemySpentControlled = timeEnemySpentControlled;
        this.totalGold = totalGold;
        this.xp = xp;
        this.champStats = champStats;
        this.damageStats = damageStats;
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

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public int getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    public void setJungleMinionsKilled(int jungleMinionsKilled) {
        this.jungleMinionsKilled = jungleMinionsKilled;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public void setMinionsKilled(int minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    public int getTimeEnemySpentControlled() {
        return timeEnemySpentControlled;
    }

    public void setTimeEnemySpentControlled(int timeEnemySpentControlled) {
        this.timeEnemySpentControlled = timeEnemySpentControlled;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public List<ParticipantFrameChamp> getChampStats() {
        return champStats;
    }

    public void setChampStats(List<ParticipantFrameChamp> champStats) {
        this.champStats = champStats;
    }

    public List<ParticipantFrameDamage> getDamageStats() {
        return damageStats;
    }

    public void setDamageStats(List<ParticipantFrameDamage> damageStats) {
        this.damageStats = damageStats;
    }
}
