package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "friend_champs")
public class FriendChamps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "champ_id")
    private int champId;

    @Column (name = "champ_level")
    private int champLevel;

    @Column (name = "champ_points")
    private int champPoints;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Friend friend;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public FriendChamps(){}

    public FriendChamps(int champId, int champLevel, int champPoints, Friend friend) {
        this.champId = champId;
        this.champLevel = champLevel;
        this.champPoints = champPoints;
        this.friend = friend;
    }

    public FriendChamps(long id, int champId, int champLevel, int champPoints, Friend friend) {
        this.id = id;
        this.champId = champId;
        this.champLevel = champLevel;
        this.champPoints = champPoints;
        this.friend = friend;
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

    public int getChampId() {
        return champId;
    }

    public void setChampId(int champId) {
        this.champId = champId;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getChampPoints() {
        return champPoints;
    }

    public void setChampPoints(int champPoints) {
        this.champPoints = champPoints;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
