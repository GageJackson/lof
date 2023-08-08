package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendRank;
import com.gagejackson.lof.Repositories.Friend.FriendRankRepository;
import com.gagejackson.lof.Repositories.Friend.FriendRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FriendRankController {

    private final FriendRepository friendRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;

    public FriendRankController(
            FriendRepository friendRepositoryDao,
            FriendRankRepository friendRankRepositoryDao
    ) {
        this.friendRepositoryDao = friendRepositoryDao;
        this.friendRankRepositoryDao = friendRankRepositoryDao;
    }

    @PostMapping("/saveRankData")
    public void saveRankData(@RequestBody Map<String, Object> data) {
        String tier = (String) data.getOrDefault("tier", "iron");
        String ranking = (String) data.getOrDefault("ranking", "i");
        int leaguePoints = (int) data.getOrDefault("leaguePoints", 0);
        int rankWins = (int) data.getOrDefault("rankWins", 0);
        int rankLosses = (int) data.getOrDefault("rankLosses", 0);
        boolean onHotStreak = (boolean) data.getOrDefault("onHotStreak", false);

        String summonerId = (String) data.get("summonerId");
        Friend friend = friendRepositoryDao.findBySummonerId(summonerId);
        FriendRank friendRank = friendRankRepositoryDao.findByFriend(friend);

        if (friendRank == null) {
            friendRank = new FriendRank();
        }

        friendRank.setTier(tier);
        friendRank.setRanking(ranking);
        friendRank.setLeaguePoints(leaguePoints);
        friendRank.setRankWins(rankWins);
        friendRank.setRankLosses(rankLosses);
        friendRank.setOnHotStreak(onHotStreak);
        friendRank.setFriend(friend);

        friendRankRepositoryDao.save(friendRank);
    }
}

