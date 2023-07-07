package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_champ_kill_victim_damage_dealt")
public class EventChampKillVictimDamageDealt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

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
    @JoinColumn(name = "event_champ_kill_id", nullable = false)
    private EventChampKill eventChampKill;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public EventChampKillVictimDamageDealt() {
    }

    public EventChampKillVictimDamageDealt(boolean basic, int magicDamage, int physicalDamage, int trueDamage, int spellSlot, String spellName, String type, EventChampKill eventChampKill) {
        this.basic = basic;
        this.magicDamage = magicDamage;
        this.physicalDamage = physicalDamage;
        this.trueDamage = trueDamage;
        this.spellSlot = spellSlot;
        this.spellName = spellName;
        this.type = type;
        this.eventChampKill = eventChampKill;
    }

    public EventChampKillVictimDamageDealt(long id, boolean basic, int magicDamage, int physicalDamage, int trueDamage, int spellSlot, String spellName, String type, EventChampKill eventChampKill) {
        this.id = id;
        this.basic = basic;
        this.magicDamage = magicDamage;
        this.physicalDamage = physicalDamage;
        this.trueDamage = trueDamage;
        this.spellSlot = spellSlot;
        this.spellName = spellName;
        this.type = type;
        this.eventChampKill = eventChampKill;
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

    public EventChampKill getEventChampKill() {
        return eventChampKill;
    }

    public void setEventChampKill(EventChampKill eventChampKill) {
        this.eventChampKill = eventChampKill;
    }
}
