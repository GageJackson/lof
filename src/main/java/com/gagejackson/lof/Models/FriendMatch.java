package com.gagejackson.lof.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "friend_match")
public class FriendMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public FriendMatch() {}

    public FriendMatch(long id, Friend friend, Match match) {
        this.id = id;
        this.friend = friend;
        this.match = match;
    }

    public FriendMatch(Friend friend, Match match) {
        this.friend = friend;
        this.match = match;
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

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
