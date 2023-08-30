package com.gagejackson.lof.Models.MatchOverview;

import jakarta.persistence.*;

@Entity
@Table(name = "participant_frame_champ")
public class ParticipantFrameChamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column(name = "ability_haste")
    private int abilityHaste;

    @Column(name = "ability_power")
    private int abilityPower;

    @Column(name = "armor")
    private int armor;

    @Column(name = "armor_pen")
    private int armorPen;

    @Column(name = "armor_pen_percent")
    private int armorPenPercent;

    @Column(name = "attack_damage")
    private int attackDamage;

    @Column(name = "attack_speed")
    private int attackSpeed;

    @Column(name = "bonus_armor_pen_percent")
    private int bonusArmorPenPercent;

    @Column(name = "bonus_magic_pen_percent")
    private int bonusMagicPenPercent;

    @Column(name = "cc_reduction")
    private int ccReduction;

    @Column(name = "cooldown_reduction")
    private int cooldownReduction;

    @Column(name = "health")
    private int health;

    @Column(name = "health_max")
    private int healthMax;

    @Column(name = "health_regen")
    private int healthRegen;

    @Column(name = "lifesteal")
    private int lifesteal;

    @Column(name = "magic_pen")
    private int magicPen;

    @Column(name = "magic_pen_percent")
    private int magicPenPercent;

    @Column(name = "magic_resist")
    private int magicResist;

    @Column(name = "movement_speed")
    private int movementSpeed;

    @Column(name = "omnivamp")
    private int omnivamp;

    @Column(name = "physical_vamp")
    private int physicalVamp;

    @Column(name = "power")
    private int power;

    @Column(name = "power_max")
    private int powerMax;

    @Column(name = "power_regen")
    private int powerRegen;

    @Column(name = "spell_vamp")
    private int spellVamp;

    @OneToOne
    @JoinColumn(name = "participant_frame_id", nullable = false)
    private ParticipantFrame participantFrame;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ParticipantFrameChamp() {
    }

    public ParticipantFrameChamp(int abilityHaste, int abilityPower, int armor, int armorPen, int armorPenPercent, int attackDamage, int attackSpeed, int bonusArmorPenPercent, int bonusMagicPenPercent, int ccReduction, int cooldownReduction, int health, int healthMax, int healthRegen, int lifesteal, int magicPen, int magicPenPercent, int magicResist, int movementSpeed, int omnivamp, int physicalVamp, int power, int powerMax, int powerRegen, int spellVamp, ParticipantFrame participantFrame) {
        this.abilityHaste = abilityHaste;
        this.abilityPower = abilityPower;
        this.armor = armor;
        this.armorPen = armorPen;
        this.armorPenPercent = armorPenPercent;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.bonusArmorPenPercent = bonusArmorPenPercent;
        this.bonusMagicPenPercent = bonusMagicPenPercent;
        this.ccReduction = ccReduction;
        this.cooldownReduction = cooldownReduction;
        this.health = health;
        this.healthMax = healthMax;
        this.healthRegen = healthRegen;
        this.lifesteal = lifesteal;
        this.magicPen = magicPen;
        this.magicPenPercent = magicPenPercent;
        this.magicResist = magicResist;
        this.movementSpeed = movementSpeed;
        this.omnivamp = omnivamp;
        this.physicalVamp = physicalVamp;
        this.power = power;
        this.powerMax = powerMax;
        this.powerRegen = powerRegen;
        this.spellVamp = spellVamp;
        this.participantFrame = participantFrame;
    }

    public ParticipantFrameChamp(long id, int abilityHaste, int abilityPower, int armor, int armorPen, int armorPenPercent, int attackDamage, int attackSpeed, int bonusArmorPenPercent, int bonusMagicPenPercent, int ccReduction, int cooldownReduction, int health, int healthMax, int healthRegen, int lifesteal, int magicPen, int magicPenPercent, int magicResist, int movementSpeed, int omnivamp, int physicalVamp, int power, int powerMax, int powerRegen, int spellVamp, ParticipantFrame participantFrame) {
        this.id = id;
        this.abilityHaste = abilityHaste;
        this.abilityPower = abilityPower;
        this.armor = armor;
        this.armorPen = armorPen;
        this.armorPenPercent = armorPenPercent;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.bonusArmorPenPercent = bonusArmorPenPercent;
        this.bonusMagicPenPercent = bonusMagicPenPercent;
        this.ccReduction = ccReduction;
        this.cooldownReduction = cooldownReduction;
        this.health = health;
        this.healthMax = healthMax;
        this.healthRegen = healthRegen;
        this.lifesteal = lifesteal;
        this.magicPen = magicPen;
        this.magicPenPercent = magicPenPercent;
        this.magicResist = magicResist;
        this.movementSpeed = movementSpeed;
        this.omnivamp = omnivamp;
        this.physicalVamp = physicalVamp;
        this.power = power;
        this.powerMax = powerMax;
        this.powerRegen = powerRegen;
        this.spellVamp = spellVamp;
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

    public int getAbilityHaste() {
        return abilityHaste;
    }

    public void setAbilityHaste(int abilityHaste) {
        this.abilityHaste = abilityHaste;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmorPen() {
        return armorPen;
    }

    public void setArmorPen(int armorPen) {
        this.armorPen = armorPen;
    }

    public int getArmorPenPercent() {
        return armorPenPercent;
    }

    public void setArmorPenPercent(int armorPenPercent) {
        this.armorPenPercent = armorPenPercent;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getBonusArmorPenPercent() {
        return bonusArmorPenPercent;
    }

    public void setBonusArmorPenPercent(int bonusArmorPenPercent) {
        this.bonusArmorPenPercent = bonusArmorPenPercent;
    }

    public int getBonusMagicPenPercent() {
        return bonusMagicPenPercent;
    }

    public void setBonusMagicPenPercent(int bonusMagicPenPercent) {
        this.bonusMagicPenPercent = bonusMagicPenPercent;
    }

    public int getCcReduction() {
        return ccReduction;
    }

    public void setCcReduction(int ccReduction) {
        this.ccReduction = ccReduction;
    }

    public int getCooldownReduction() {
        return cooldownReduction;
    }

    public void setCooldownReduction(int cooldownReduction) {
        this.cooldownReduction = cooldownReduction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

    public int getLifesteal() {
        return lifesteal;
    }

    public void setLifesteal(int lifesteal) {
        this.lifesteal = lifesteal;
    }

    public int getMagicPen() {
        return magicPen;
    }

    public void setMagicPen(int magicPen) {
        this.magicPen = magicPen;
    }

    public int getMagicPenPercent() {
        return magicPenPercent;
    }

    public void setMagicPenPercent(int magicPenPercent) {
        this.magicPenPercent = magicPenPercent;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int magicResist) {
        this.magicResist = magicResist;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getOmnivamp() {
        return omnivamp;
    }

    public void setOmnivamp(int omnivamp) {
        this.omnivamp = omnivamp;
    }

    public int getPhysicalVamp() {
        return physicalVamp;
    }

    public void setPhysicalVamp(int physicalVamp) {
        this.physicalVamp = physicalVamp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(int powerMax) {
        this.powerMax = powerMax;
    }

    public int getPowerRegen() {
        return powerRegen;
    }

    public void setPowerRegen(int powerRegen) {
        this.powerRegen = powerRegen;
    }

    public int getSpellVamp() {
        return spellVamp;
    }

    public void setSpellVamp(int spellVamp) {
        this.spellVamp = spellVamp;
    }

    public ParticipantFrame getParticipantFrame() {
        return participantFrame;
    }

    public void setParticipantFrame(ParticipantFrame participantFrame) {
        this.participantFrame = participantFrame;
    }
}
