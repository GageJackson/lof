package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend findByAccountId(String accountId);
    Friend findByPuuId(String puuId);
    Friend findBySummonerId(String summonerId);
}
