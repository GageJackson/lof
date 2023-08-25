package com.gagejackson.lof.Repositories.Friend;

import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendMatch;
import com.gagejackson.lof.Models.MatchOverview.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendMatchRepository extends JpaRepository<FriendMatch, Long> {
    FriendMatch findFriendMatchByFriendAndMatch(Friend friend, Match match);
    List<FriendMatch> findAllByMatch(Match match);
}
