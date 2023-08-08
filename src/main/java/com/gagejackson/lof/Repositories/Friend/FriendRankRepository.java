package com.gagejackson.lof.Repositories.Friend;

import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRankRepository extends JpaRepository<FriendRank, Long> {
    FriendRank findByFriend (Friend friend);
}
