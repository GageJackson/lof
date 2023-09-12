package com.gagejackson.lof.Models.MatchEvent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gagejackson.lof.Models.MatchOverview.Participant;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "champ_kill")
public class ChampKill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "bounty")
    private int bounty;

    @Column(name = "shutdown_bounty")
    private int shutdownBounty;

    @Column(name = "kill_streak_length")
    private int killStreakLength;

    @Column(name = "position_x")
    private int positionX;

    @Column(name = "position_y")
    private int positionY;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "victim_id", nullable = false)
    private Participant victim;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "champKill")
    private List<DamageDealt> damageDealt;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "champKill")
    private List<DamageReceived> damageReceived;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ChampKill() {
    }

    public ChampKill(int bounty, int shutdownBounty, int killStreakLength, int positionX, int positionY, Participant victim, Event event, List<DamageDealt> damageDealt, List<DamageReceived> damageReceived) {
        this.bounty = bounty;
        this.shutdownBounty = shutdownBounty;
        this.killStreakLength = killStreakLength;
        this.positionX = positionX;
        this.positionY = positionY;
        this.victim = victim;
        this.event = event;
        this.damageDealt = damageDealt;
        this.damageReceived = damageReceived;
    }

    public ChampKill(long id, int bounty, int shutdownBounty, int killStreakLength, int positionX, int positionY, Participant victim, Event event, List<DamageDealt> damageDealt, List<DamageReceived> damageReceived) {
        this.id = id;
        this.bounty = bounty;
        this.shutdownBounty = shutdownBounty;
        this.killStreakLength = killStreakLength;
        this.positionX = positionX;
        this.positionY = positionY;
        this.victim = victim;
        this.event = event;
        this.damageDealt = damageDealt;
        this.damageReceived = damageReceived;
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

    public int getBounty() {
        return bounty;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    public int getShutdownBounty() {
        return shutdownBounty;
    }

    public void setShutdownBounty(int shutdownBounty) {
        this.shutdownBounty = shutdownBounty;
    }

    public int getKillStreakLength() {
        return killStreakLength;
    }

    public void setKillStreakLength(int killStreakLength) {
        this.killStreakLength = killStreakLength;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Participant getVictim() {
        return victim;
    }

    public void setVictim(Participant victim) {
        this.victim = victim;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<DamageDealt> getEventChampKillVictimDamageDealt() {
        return damageDealt;
    }

    public void setEventChampKillVictimDamageDealt(List<DamageDealt> damageDealt) {
        this.damageDealt = damageDealt;
    }

    public List<DamageReceived> getEventChampKillVictimDamageReceived() {
        return damageReceived;
    }

    public void setEventChampKillVictimDamageReceived(List<DamageReceived> damageReceived) {
        this.damageReceived = damageReceived;
    }
}
