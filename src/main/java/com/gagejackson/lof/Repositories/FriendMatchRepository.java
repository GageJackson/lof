package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendMatch;
import com.gagejackson.lof.Models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendMatchRepository extends JpaRepository<FriendMatch, Long> {
    FriendMatch findFriendMatchByFriendAndMatch(Friend friend, Match match);
}
