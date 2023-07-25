package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendChamps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendChampsRepository extends JpaRepository<FriendChamps, Long> {
    List<FriendChamps> findFriendChampsByFriend(Friend friend);
    FriendChamps findByFriendAndChampId(Friend friend, int champId);
}
