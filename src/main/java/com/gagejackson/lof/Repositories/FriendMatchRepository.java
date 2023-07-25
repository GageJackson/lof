package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendMatch;
import com.gagejackson.lof.Models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendMatchRepository extends JpaRepository<FriendMatch, Long> {
    FriendMatch findFriendMatchByFriendAndMatch(Friend friend, Match match);
    List<FriendMatch> findAllByMatch(Match match);
}
