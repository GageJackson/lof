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
public class FriendController {

    private final FriendRepository friendRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;

    public FriendController(
            FriendRepository friendRepositoryDao,
            FriendRankRepository friendRankRepositoryDao
    ) {
        this.friendRepositoryDao = friendRepositoryDao;
        this.friendRankRepositoryDao = friendRankRepositoryDao;
    }

    @PostMapping("/saveFriendData")
    public void saveRankData(@RequestBody Map<String, Object> data) {
        String summonerId = (String) data.get("summonerId");
        Friend friend = friendRepositoryDao.findBySummonerId(summonerId);

        int icon = (int) data.get("icon");
        int summonerLevel = (int) data.get("summonerLevel");

        friend.setIcon(icon);
        friend.setSummonerLevel(summonerLevel);

        System.out.println("icon = " + icon);
        System.out.println("summonerLevel = " + summonerLevel);

        //friendRepositoryDao.save(friend);
    }

}