package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

@Entity
@Table(name = "summoner_spell")
public class SummonerSpell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tooltip")
    private String tooltip;

    @Column(name = "max_rank")
    private int maxRank;

    @Column(name = "cooldown_burn")
    private String cooldownBurn;

    @Column(name = "key_id")
    private String keyId;

    @Column(name = "summoner_level")
    private int summonerLevel;

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

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public SummonerSpell() {
    }

    public SummonerSpell(String name, String description, String tooltip, int maxRank, String cooldownBurn, String keyId, int summonerLevel, String costType, String maxAmmo, String rangeBurn, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH) {
        this.name = name;
        this.description = description;
        this.tooltip = tooltip;
        this.maxRank = maxRank;
        this.cooldownBurn = cooldownBurn;
        this.keyId = keyId;
        this.summonerLevel = summonerLevel;
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
    }

    public SummonerSpell(long id, String name, String description, String tooltip, int maxRank, String cooldownBurn, String keyId, int summonerLevel, String costType, String maxAmmo, String rangeBurn, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tooltip = tooltip;
        this.maxRank = maxRank;
        this.cooldownBurn = cooldownBurn;
        this.keyId = keyId;
        this.summonerLevel = summonerLevel;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
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

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
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
}
