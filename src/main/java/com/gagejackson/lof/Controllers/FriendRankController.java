package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendRank;
import com.gagejackson.lof.Repositories.FriendRankRepository;
import com.gagejackson.lof.Repositories.FriendRepository;
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
        FriendRank friendRank = new FriendRank();

        String tier = (String) data.get("tier");
        String ranking = (String) data.get("ranking");
        int leaguePoints = (int) data.get("leaguePoints");

        String summonerId = (String) data.get("summonerId");
        Friend friend = friendRepositoryDao.findBySummonerId(summonerId);

        friendRank.setTier(tier);
        friendRank.setRanking(ranking);
        friendRank.setLeaguePoints(leaguePoints);
        friendRank.setFriend(friend);

        System.out.println("friendRank.getTier() = " + friendRank.getTier());
        System.out.println("friendRank.getRanking() = " + friendRank.getRanking());
        System.out.println("friendRank.getLeaguePoints() = " + friendRank.getLeaguePoints());
        System.out.println("friendID = " + friend.getId());
        
        //friendRankRepositoryDao.save(friendRank);
    }
}

