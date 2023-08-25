package com.gagejackson.lof.Models.Game;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "name", nullable = false)
    private String name;

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

    @Column(name = "gold_base")
    private int goldBase;

    @Column(name = "gold_total")
    private int goldTotal;

    @Column(name = "gold_sell")
    private int goldSell;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<ItemTags> itemTags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<ItemStats> itemStats;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public Item() {
    }

    public Item(int number, String name, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH, int goldBase, int goldTotal, int goldSell, List<ItemTags> itemTags, List<ItemStats> itemStats) {
        this.number = number;
        this.name = name;
        this.imageFull = imageFull;
        this.imageSprite = imageSprite;
        this.imageGroup = imageGroup;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageW = imageW;
        this.imageH = imageH;
        this.goldBase = goldBase;
        this.goldTotal = goldTotal;
        this.goldSell = goldSell;
        this.itemTags = itemTags;
        this.itemStats = itemStats;
    }

    public Item(long id, int number, String name, String imageFull, String imageSprite, String imageGroup, int imageX, int imageY, int imageW, int imageH, int goldBase, int goldTotal, int goldSell, List<ItemTags> itemTags, List<ItemStats> itemStats) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.imageFull = imageFull;
        this.imageSprite = imageSprite;
        this.imageGroup = imageGroup;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageW = imageW;
        this.imageH = imageH;
        this.goldBase = goldBase;
        this.goldTotal = goldTotal;
        this.goldSell = goldSell;
        this.itemTags = itemTags;
        this.itemStats = itemStats;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getGoldBase() {
        return goldBase;
    }

    public void setGoldBase(int goldBase) {
        this.goldBase = goldBase;
    }

    public int getGoldTotal() {
        return goldTotal;
    }

    public void setGoldTotal(int goldTotal) {
        this.goldTotal = goldTotal;
    }

    public int getGoldSell() {
        return goldSell;
    }

    public void setGoldSell(int goldSell) {
        this.goldSell = goldSell;
    }

    public List<ItemTags> getItemTags() {
        return itemTags;
    }

    public void setItemTags(List<ItemTags> itemTags) {
        this.itemTags = itemTags;
    }

    public List<ItemStats> getItemStats() {
        return itemStats;
    }

    public void setItemStats(List<ItemStats> itemStats) {
        this.itemStats = itemStats;
    }
}
