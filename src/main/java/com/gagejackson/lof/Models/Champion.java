package com.gagejackson.lof.Models;

import jakarta.persistence.*;

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

    @Column(name = "ally_tips") // *******************************
    private String allyTips;

    @Column(name = "enemy_tips") // *******************************
    private String enemyTips;

    @Column(name = "tags") // *******************************
    private String tags;

}
