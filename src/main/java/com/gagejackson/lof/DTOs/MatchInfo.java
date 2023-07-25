package com.gagejackson.lof.DTOs;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.Match;

import java.util.List;

public class MatchInfo {
    private Match match;
    private List<Friend> friends;
    private boolean friendWin;

    public MatchInfo() {
    }
    public MatchInfo(Match match, List<Friend> friends, boolean friendWind) {
        this.match = match;
        this.friends = friends;
        this.friendWin = friendWind;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public boolean isFriendWin() {
        return friendWin;
    }

    public void setFriendWin(boolean friendWin) {
        this.friendWin = friendWin;
    }
}
