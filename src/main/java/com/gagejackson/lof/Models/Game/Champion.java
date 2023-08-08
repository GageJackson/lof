package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "champion")
public class Champion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name_id", nullable = false)
    private String nameId;

    @Column(name = "key", nullable = false)
    private int key;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

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

    @Column(name = "lore")
    private String lore;

    @Column(name = "blurb")
    private String blurb;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private List<ChampAllyTips> champAllyTips;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private List<ChampEnemyTips> champEnemyTips;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private List<ChampTags> champTags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private List<ChampStats> champStats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private List<ChampSpells> champSpells;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public Champion() {
    }

    public Champion(String nameId, int key, String name, String title, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH, String lore, String blurb, List<ChampAllyTips> champAllyTips, List<ChampEnemyTips> champEnemyTips, List<ChampTags> champTags, List<ChampStats> champStats, List<ChampSpells> champSpells) {
        this.nameId = nameId;
        this.key = key;
        this.name = name;
        this.title = title;
        this.imageFull = imageFull;
        this.imageSprite = imageSprite;
        this.imageGroup = imageGroup;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageW = imageW;
        this.imageH = imageH;
        this.lore = lore;
        this.blurb = blurb;
        this.champAllyTips = champAllyTips;
        this.champEnemyTips = champEnemyTips;
        this.champTags = champTags;
        this.champStats = champStats;
        this.champSpells = champSpells;
    }

    public Champion(long id, String nameId, int key, String name, String title, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH, String lore, String blurb, List<ChampAllyTips> champAllyTips, List<ChampEnemyTips> champEnemyTips, List<ChampTags> champTags, List<ChampStats> champStats, List<ChampSpells> champSpells) {
        this.id = id;
        this.nameId = nameId;
        this.key = key;
        this.name = name;
        this.title = title;
        this.imageFull = imageFull;
        this.imageSprite = imageSprite;
        this.imageGroup = imageGroup;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageW = imageW;
        this.imageH = imageH;
        this.lore = lore;
        this.blurb = blurb;
        this.champAllyTips = champAllyTips;
        this.champEnemyTips = champEnemyTips;
        this.champTags = champTags;
        this.champStats = champStats;
        this.champSpells = champSpells;
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

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public List<ChampAllyTips> getChampAllyTips() {
        return champAllyTips;
    }

    public void setChampAllyTips(List<ChampAllyTips> champAllyTips) {
        this.champAllyTips = champAllyTips;
    }

    public List<ChampEnemyTips> getChampEnemyTips() {
        return champEnemyTips;
    }

    public void setChampEnemyTips(List<ChampEnemyTips> champEnemyTips) {
        this.champEnemyTips = champEnemyTips;
    }

    public List<ChampTags> getChampTags() {
        return champTags;
    }

    public void setChampTags(List<ChampTags> champTags) {
        this.champTags = champTags;
    }

    public List<ChampStats> getChampStats() {
        return champStats;
    }

    public void setChampStats(List<ChampStats> champStats) {
        this.champStats = champStats;
    }

    public List<ChampSpells> getChampSpells() {
        return champSpells;
    }

    public void setChampSpells(List<ChampSpells> champSpells) {
        this.champSpells = champSpells;
    }
}