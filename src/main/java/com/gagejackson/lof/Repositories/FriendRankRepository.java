package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRankRepository extends JpaRepository<FriendRank, Long> {
    FriendRank findByFriend (Friend friend);
}
