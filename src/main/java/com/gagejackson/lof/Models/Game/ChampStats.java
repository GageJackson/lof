package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

@Entity
@Table(name = "champ_stats")
public class ChampStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "attack")
    private int attack;

    @Column(name = "defense")
    private int defense;

    @Column(name = "magic")
    private int magic;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "hp")
    private int hp;

    @Column(name = "hpperlevel")
    private int hpperlevel;

    @Column(name = "mp")
    private int mp;

    @Column(name = "mpperlevel")
    private int mpperlevel;

    @Column(name = "movespeed")
    private int movespeed;

    @Column(name = "armor")
    private double armor;

    @Column(name = "armorperlevel")
    private double armorperlevel;

    @Column(name = "spellblock")
    private double spellblock;

    @Column(name = "spellblockperlevel")
    private double spellblockperlevel;

    @Column(name = "attackrange")
    private int attackrange;

    @Column(name = "hpregen")
    private double hpregen;

    @Column(name = "hpregenperlevel")
    private double hpregenperlevel;

    @Column(name = "mpregen")
    private double mpregen;

    @Column(name = "mpregenperlevel")
    private double mpregenperlevel;

    @Column(name = "crit")
    private int crit;

    @Column(name = "critperlevel")
    private int critperlevel;

    @Column(name = "attackdamage")
    private double attackdamage;

    @Column(name = "attackdamageperlevel")
    private double attackdamageperlevel;

    @Column(name = "attackspeedperlevel")
    private double attackspeedperlevel;

    @Column(name = "attackspeed")
    private double attackspeed;

    @ManyToOne
    @JoinColumn(name = "champion_id", nullable = false)
    private Champion champion;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public ChampStats() {
    }

    public ChampStats(int attack, int defense, int magic, int difficulty, int hp, int hpperlevel, int mp, int mpperlevel, int movespeed, double armor, double armorperlevel, double spellblock, double spellblockperlevel, int attackrange, double hpregen, double hpregenperlevel, double mpregen, double mpregenperlevel, int crit, int critperlevel, double attackdamage, double attackdamageperlevel, double attackspeedperlevel, double attackspeed, Champion champion) {
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
        this.hp = hp;
        this.hpperlevel = hpperlevel;
        this.mp = mp;
        this.mpperlevel = mpperlevel;
        this.movespeed = movespeed;
        this.armor = armor;
        this.armorperlevel = armorperlevel;
        this.spellblock = spellblock;
        this.spellblockperlevel = spellblockperlevel;
        this.attackrange = attackrange;
        this.hpregen = hpregen;
        this.hpregenperlevel = hpregenperlevel;
        this.mpregen = mpregen;
        this.mpregenperlevel = mpregenperlevel;
        this.crit = crit;
        this.critperlevel = critperlevel;
        this.attackdamage = attackdamage;
        this.attackdamageperlevel = attackdamageperlevel;
        this.attackspeedperlevel = attackspeedperlevel;
        this.attackspeed = attackspeed;
        this.champion = champion;
    }

    public ChampStats(long id, int attack, int defense, int magic, int difficulty, int hp, int hpperlevel, int mp, int mpperlevel, int movespeed, double armor, double armorperlevel, double spellblock, double spellblockperlevel, int attackrange, double hpregen, double hpregenperlevel, double mpregen, double mpregenperlevel, int crit, int critperlevel, double attackdamage, double attackdamageperlevel, double attackspeedperlevel, double attackspeed, Champion champion) {
        this.id = id;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
        this.hp = hp;
        this.hpperlevel = hpperlevel;
        this.mp = mp;
        this.mpperlevel = mpperlevel;
        this.movespeed = movespeed;
        this.armor = armor;
        this.armorperlevel = armorperlevel;
        this.spellblock = spellblock;
        this.spellblockperlevel = spellblockperlevel;
        this.attackrange = attackrange;
        this.hpregen = hpregen;
        this.hpregenperlevel = hpregenperlevel;
        this.mpregen = mpregen;
        this.mpregenperlevel = mpregenperlevel;
        this.crit = crit;
        this.critperlevel = critperlevel;
        this.attackdamage = attackdamage;
        this.attackdamageperlevel = attackdamageperlevel;
        this.attackspeedperlevel = attackspeedperlevel;
        this.attackspeed = attackspeed;
        this.champion = champion;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpperlevel() {
        return hpperlevel;
    }

    public void setHpperlevel(int hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMpperlevel() {
        return mpperlevel;
    }

    public void setMpperlevel(int mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    public int getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getArmorperlevel() {
        return armorperlevel;
    }

    public void setArmorperlevel(double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    public double getSpellblock() {
        return spellblock;
    }

    public void setSpellblock(double spellblock) {
        this.spellblock = spellblock;
    }

    public double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    public void setSpellblockperlevel(double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    public int getAttackrange() {
        return attackrange;
    }

    public void setAttackrange(int attackrange) {
        this.attackrange = attackrange;
    }

    public double getHpregen() {
        return hpregen;
    }

    public void setHpregen(double hpregen) {
        this.hpregen = hpregen;
    }

    public double getHpregenperlevel() {
        return hpregenperlevel;
    }

    public void setHpregenperlevel(double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    public double getMpregen() {
        return mpregen;
    }

    public void setMpregen(double mpregen) {
        this.mpregen = mpregen;
    }

    public double getMpregenperlevel() {
        return mpregenperlevel;
    }

    public void setMpregenperlevel(double mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getCritperlevel() {
        return critperlevel;
    }

    public void setCritperlevel(int critperlevel) {
        this.critperlevel = critperlevel;
    }

    public double getAttackdamage() {
        return attackdamage;
    }

    public void setAttackdamage(double attackdamage) {
        this.attackdamage = attackdamage;
    }

    public double getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    public void setAttackdamageperlevel(double attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    public double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    public void setAttackspeedperlevel(double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    public double getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(double attackspeed) {
        this.attackspeed = attackspeed;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }
}
