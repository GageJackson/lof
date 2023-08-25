package com.gagejackson.lof.Models.MatchEvent;

import com.gagejackson.lof.Models.MatchOverview.Participant;
import jakarta.persistence.*;

@Entity
@Table(name = "damage_received")
public class DamageReceived {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "basic")
    private boolean basic;

    @Column(name = "magic_damage")
    private int magicDamage;

    @Column(name = "physical_damage")
    private int physicalDamage;

    @Column(name = "true_damage")
    private int trueDamage;

    @Column(name = "spell_slot")
    private int spellSlot;

    @Column(name = "spell_name", length = 50)
    private String spellName;

    @Column(name = "type", length = 25)
    private String type;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "champ_kill_id", nullable = false)
    private ChampKill champKill;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public DamageReceived() {
    }

    public DamageReceived(String name, boolean basic, int magicDamage, int physicalDamage, int trueDamage, int spellSlot, String spellName, String type, Participant participant, ChampKill champKill) {
        this.name = name;
        this.basic = basic;
        this.magicDamage = magicDamage;
        this.physicalDamage = physicalDamage;
        this.trueDamage = trueDamage;
        this.spellSlot = spellSlot;
        this.spellName = spellName;
        this.type = type;
        this.participant = participant;
        this.champKill = champKill;
    }

    public DamageReceived(long id, String name, boolean basic, int magicDamage, int physicalDamage, int trueDamage, int spellSlot, String spellName, String type, Participant participant, ChampKill champKill) {
        this.id = id;
        this.name = name;
        this.basic = basic;
        this.magicDamage = magicDamage;
        this.physicalDamage = physicalDamage;
        this.trueDamage = trueDamage;
        this.spellSlot = spellSlot;
        this.spellName = spellName;
        this.type = type;
        this.participant = participant;
        this.champKill = champKill;
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

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public int getTrueDamage() {
        return trueDamage;
    }

    public void setTrueDamage(int trueDamage) {
        this.trueDamage = trueDamage;
    }

    public int getSpellSlot() {
        return spellSlot;
    }

    public void setSpellSlot(int spellSlot) {
        this.spellSlot = spellSlot;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public ChampKill getEventChampKill() {
        return champKill;
    }

    public void setEventChampKill(ChampKill champKill) {
        this.champKill = champKill;
    }
}
