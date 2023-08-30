package com.gagejackson.lof.Models.MatchOverview;

import jakarta.persistence.*;

@Entity
@Table(name = "participant_frame_damage")
public class ParticipantFrameDamage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "magic_damage_done")
    private int magicDamageDone;

    @Column (name = "magic_damage_done_to_champs")
    private int magicDamageDoneToChamps;

    @Column (name = "magic_damage_taken")
    private int magicDamageTaken;

    @Column (name = "physical_damage_done")
    private int physicalDamageDone;

    @Column (name = "physical_damage_done_to_champs")
    private int physicalDamageDoneToChamps;

    @Column (name = "physical_damage_taken")
    private int physicalDamageTaken;

    @Column (name = "total_damage_done")
    private int totalDamageDone;

    @Column (name = "total_damage_done_to_champs")
    private int totalDamageDoneToChamps;

    @Column (name = "total_damage_taken")
    private int totalDamageTaken;

    @Column (name = "true_damage_done")
    private int trueDamageDone;

    @Column (name = "true_damage_done_to_champs")
    private int trueDamageDoneToChamps;

    @Column (name = "true_damage_taken")
    private int trueDamageTaken;

    @OneToOne
    @JoinColumn(name = "participant_frame_id", nullable = false)
    private ParticipantFrame participantFrame;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantFrameDamage() {
    }

    public ParticipantFrameDamage(int magicDamageDone, int magicDamageDoneToChamps, int magicDamageTaken, int physicalDamageDone, int physicalDamageDoneToChamps, int physicalDamageTaken, int totalDamageDone, int totalDamageDoneToChamps, int totalDamageTaken, int trueDamageDone, int trueDamageDoneToChamps, int trueDamageTaken, ParticipantFrame participantFrame) {
        this.magicDamageDone = magicDamageDone;
        this.magicDamageDoneToChamps = magicDamageDoneToChamps;
        this.magicDamageTaken = magicDamageTaken;
        this.physicalDamageDone = physicalDamageDone;
        this.physicalDamageDoneToChamps = physicalDamageDoneToChamps;
        this.physicalDamageTaken = physicalDamageTaken;
        this.totalDamageDone = totalDamageDone;
        this.totalDamageDoneToChamps = totalDamageDoneToChamps;
        this.totalDamageTaken = totalDamageTaken;
        this.trueDamageDone = trueDamageDone;
        this.trueDamageDoneToChamps = trueDamageDoneToChamps;
        this.trueDamageTaken = trueDamageTaken;
        this.participantFrame = participantFrame;
    }

    public ParticipantFrameDamage(long id, int magicDamageDone, int magicDamageDoneToChamps, int magicDamageTaken, int physicalDamageDone, int physicalDamageDoneToChamps, int physicalDamageTaken, int totalDamageDone, int totalDamageDoneToChamps, int totalDamageTaken, int trueDamageDone, int trueDamageDoneToChamps, int trueDamageTaken, ParticipantFrame participantFrame) {
        this.id = id;
        this.magicDamageDone = magicDamageDone;
        this.magicDamageDoneToChamps = magicDamageDoneToChamps;
        this.magicDamageTaken = magicDamageTaken;
        this.physicalDamageDone = physicalDamageDone;
        this.physicalDamageDoneToChamps = physicalDamageDoneToChamps;
        this.physicalDamageTaken = physicalDamageTaken;
        this.totalDamageDone = totalDamageDone;
        this.totalDamageDoneToChamps = totalDamageDoneToChamps;
        this.totalDamageTaken = totalDamageTaken;
        this.trueDamageDone = trueDamageDone;
        this.trueDamageDoneToChamps = trueDamageDoneToChamps;
        this.trueDamageTaken = trueDamageTaken;
        this.participantFrame = participantFrame;
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

    public int getMagicDamageDone() {
        return magicDamageDone;
    }

    public void setMagicDamageDone(int magicDamageDone) {
        this.magicDamageDone = magicDamageDone;
    }

    public int getMagicDamageDoneToChamps() {
        return magicDamageDoneToChamps;
    }

    public void setMagicDamageDoneToChamps(int magicDamageDoneToChamps) {
        this.magicDamageDoneToChamps = magicDamageDoneToChamps;
    }

    public int getMagicDamageTaken() {
        return magicDamageTaken;
    }

    public void setMagicDamageTaken(int magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    public int getPhysicalDamageDone() {
        return physicalDamageDone;
    }

    public void setPhysicalDamageDone(int physicalDamageDone) {
        this.physicalDamageDone = physicalDamageDone;
    }

    public int getPhysicalDamageDoneToChamps() {
        return physicalDamageDoneToChamps;
    }

    public void setPhysicalDamageDoneToChamps(int physicalDamageDoneToChamps) {
        this.physicalDamageDoneToChamps = physicalDamageDoneToChamps;
    }

    public int getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public void setPhysicalDamageTaken(int physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    public int getTotalDamageDone() {
        return totalDamageDone;
    }

    public void setTotalDamageDone(int totalDamageDone) {
        this.totalDamageDone = totalDamageDone;
    }

    public int getTotalDamageDoneToChamps() {
        return totalDamageDoneToChamps;
    }

    public void setTotalDamageDoneToChamps(int totalDamageDoneToChamps) {
        this.totalDamageDoneToChamps = totalDamageDoneToChamps;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(int totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public int getTrueDamageDone() {
        return trueDamageDone;
    }

    public void setTrueDamageDone(int trueDamageDone) {
        this.trueDamageDone = trueDamageDone;
    }

    public int getTrueDamageDoneToChamps() {
        return trueDamageDoneToChamps;
    }

    public void setTrueDamageDoneToChamps(int trueDamageDoneToChamps) {
        this.trueDamageDoneToChamps = trueDamageDoneToChamps;
    }

    public int getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public void setTrueDamageTaken(int trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    public ParticipantFrame getParticipantFrame() {
        return participantFrame;
    }

    public void setParticipantFrame(ParticipantFrame participantFrame) {
        this.participantFrame = participantFrame;
    }
}
