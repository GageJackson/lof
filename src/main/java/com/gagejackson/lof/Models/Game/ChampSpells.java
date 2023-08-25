package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

@Entity
@Table(name = "champ_spells")
public class ChampSpells {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "abilityId")
    private String abilityId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tool_tip")
    private String toolTip;

    @Column(name = "max_rank")
    private int maxRank;

    @Column(name = "cooldown_burn")
    private String cooldownBurn;

    @Column(name = "cost_burn")
    private String costBurn;

    @Column(name = "cost_type")
    private String costType;

    @Column(name = "max_ammo")
    private String maxAmmo;

    @Column(name = "range_burn")
    private String rangeBurn;

    @Column(name = "image_full")
    private String imageFull;

    @Column(name = "image_sprite")
    private String imageSprite;

    @Column(name = "image_group")
    private String imageGroup;

    @Column(name = "image_x")
    private int imageX;

    @Column(name = "image_y")
    private int imageY;

    @Column(name = "image_w")
    private int imageW;

    @Column(name = "image_h")
    private int imageH;

    @ManyToOne
    @JoinColumn(name = "champion_id", nullable = false)
    private Champion champion;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public ChampSpells() {
    }

    public ChampSpells(String abilityId, String name, String description, String toolTip, int maxRank, String cooldownBurn, String costBurn, String costType, String maxAmmo, String rangeBurn, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH, Champion champion) {
        this.abilityId = abilityId;
        this.name = name;
        this.description = description;
        this.toolTip = toolTip;
        this.maxRank = maxRank;
        this.cooldownBurn = cooldownBurn;
        this.costBurn = costBurn;
        this.costType = costType;
        this.maxAmmo = maxAmmo;
        this.rangeBurn = rangeBurn;
        this.imageFull = imageFull;
        this.imageSprite = imageSprite;
        this.imageGroup = imageGroup;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageW = imageW;
        this.imageH = imageH;
        this.champion = champion;
    }

    public ChampSpells(long id, String abilityId, String name, String description, String toolTip, int maxRank, String cooldownBurn, String costBurn, String costType, String maxAmmo, String rangeBurn, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH, Champion champion) {
        this.id = id;
        this.abilityId = abilityId;
        this.name = name;
        this.description = description;
        this.toolTip = toolTip;
        this.maxRank = maxRank;
        this.cooldownBurn = cooldownBurn;
        this.costBurn = costBurn;
        this.costType = costType;
        this.maxAmmo = maxAmmo;
        this.rangeBurn = rangeBurn;
        this.imageFull = imageFull;
        this.imageSprite = imageSprite;
        this.imageGroup = imageGroup;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageW = imageW;
        this.imageH = imageH;
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

    public String getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(String abilityId) {
        this.abilityId = abilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public int getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(int maxRank) {
        this.maxRank = maxRank;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(String maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public String getImageFull() {
        return imageFull;
    }

    public void setImageFull(String imageFull) {
        this.imageFull = imageFull;
    }

    public String getImageSprite() {
        return imageSprite;
    }

    public void setImageSprite(String imageSprite) {
        this.imageSprite = imageSprite;
    }

    public String getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(String imageGroup) {
        this.imageGroup = imageGroup;
    }

    public int getImageX() {
        return imageX;
    }

    public void setImageX(int imageX) {
        this.imageX = imageX;
    }

    public int getImageY() {
        return imageY;
    }

    public void setImageY(int imageY) {
        this.imageY = imageY;
    }

    public int getImageW() {
        return imageW;
    }

    public void setImageW(int imageW) {
        this.imageW = imageW;
    }

    public int getImageH() {
        return imageH;
    }

    public void setImageH(int imageH) {
        this.imageH = imageH;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }
}
