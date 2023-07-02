package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "perk")
public class Perk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "is_primary")
    private boolean isPrimary;

    @Column (name = "style_id")
    private int style;

    @Column (name = "perk_id")
    private int perkId;

    @Column (name = "perk_stat1")
    private int perkStat1;

    @Column (name = "perk_stat2")
    private int perkStat2;

    @Column (name = "perk_stat3")
    private int perkStat3;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Perk(){}

    public Perk(long id, boolean isPrimary, int style, int perkId, int perkStat1, int perkStat2, int perkStat3, Participant participant) {
        this.id = id;
        this.isPrimary = isPrimary;
        this.style = style;
        this.perkId = perkId;
        this.perkStat1 = perkStat1;
        this.perkStat2 = perkStat2;
        this.perkStat3 = perkStat3;
        this.participant = participant;
    }

    public Perk(boolean isPrimary, int style, int perkId, int perkStat1, int perkStat2, int perkStat3, Participant participant) {
        this.isPrimary = isPrimary;
        this.style = style;
        this.perkId = perkId;
        this.perkStat1 = perkStat1;
        this.perkStat2 = perkStat2;
        this.perkStat3 = perkStat3;
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

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getPerkId() {
        return perkId;
    }

    public void setPerkId(int perkId) {
        this.perkId = perkId;
    }

    public int getPerkStat1() {
        return perkStat1;
    }

    public void setPerkStat1(int perkStat1) {
        this.perkStat1 = perkStat1;
    }

    public int getPerkStat2() {
        return perkStat2;
    }

    public void setPerkStat2(int perkStat2) {
        this.perkStat2 = perkStat2;
    }

    public int getPerkStat3() {
        return perkStat3;
    }

    public void setPerkStat3(int perkStat3) {
        this.perkStat3 = perkStat3;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
