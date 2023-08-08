package com.gagejackson.lof.Models.MatchOverview;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "participant_frame")
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

    @Column (name = "frame")
    private int frame;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "participantFrame")
    private List<ParticipantFrameChamp> participantFrameChamp;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "participantFrame")
    private List<ParticipantFrameDamage> participantFrameDamage;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantFrame() {
    }

    public ParticipantFrame(int currentGold, int jungleMinionsKilled, int level, int minionsKilled, int timeEnemySpentControlled, int totalGold, int xp, int frame, List<ParticipantFrameChamp> participantFrameChamp, List<ParticipantFrameDamage> participantFrameDamage, Participant participant) {
        this.currentGold = currentGold;
        this.jungleMinionsKilled = jungleMinionsKilled;
        this.level = level;
        this.minionsKilled = minionsKilled;
        this.timeEnemySpentControlled = timeEnemySpentControlled;
        this.totalGold = totalGold;
        this.xp = xp;
        this.frame = frame;
        this.participantFrameChamp = participantFrameChamp;
        this.participantFrameDamage = participantFrameDamage;
        this.participant = participant;
    }

    public ParticipantFrame(long id, int currentGold, int jungleMinionsKilled, int level, int minionsKilled, int timeEnemySpentControlled, int totalGold, int xp, int frame, List<ParticipantFrameChamp> participantFrameChamp, List<ParticipantFrameDamage> participantFrameDamage, Participant participant) {
        this.id = id;
        this.currentGold = currentGold;
        this.jungleMinionsKilled = jungleMinionsKilled;
        this.level = level;
        this.minionsKilled = minionsKilled;
        this.timeEnemySpentControlled = timeEnemySpentControlled;
        this.totalGold = totalGold;
        this.xp = xp;
        this.frame = frame;
        this.participantFrameChamp = participantFrameChamp;
        this.participantFrameDamage = participantFrameDamage;
        this.participant = participant;
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

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public List<ParticipantFrameChamp> getParticipantFrameChamp() {
        return participantFrameChamp;
    }

    public void setParticipantFrameChamp(List<ParticipantFrameChamp> participantFrameChamp) {
        this.participantFrameChamp = participantFrameChamp;
    }

    public List<ParticipantFrameDamage> getParticipantFrameDamage() {
        return participantFrameDamage;
    }

    public void setParticipantFrameDamage(List<ParticipantFrameDamage> participantFrameDamage) {
        this.participantFrameDamage = participantFrameDamage;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
