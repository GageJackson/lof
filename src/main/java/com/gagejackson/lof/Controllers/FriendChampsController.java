package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendChamps;
import com.gagejackson.lof.Models.FriendRank;
import com.gagejackson.lof.Repositories.FriendChampsRepository;
import com.gagejackson.lof.Repositories.FriendRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Map;

@RestController
public class FriendChampsController {
    private final FriendRepository friendRepositoryDao;
    private final FriendChampsRepository friendChampsRepositoryDao;

    public FriendChampsController(
            FriendRepository friendRepositoryDao,
            FriendChampsRepository friendChampsRepositoryDao
    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.friendChampsRepositoryDao = friendChampsRepositoryDao;
    }

    @PostMapping("/saveChampsData")
    public void saveChampsData(@RequestBody Map<String, Object>[] champsArray) {

        for (Map<String, Object> champ : champsArray) {
            FriendChamps friendChamp = new FriendChamps();

            int champId = (int) champ.get("champId");
            int champLevel = (int) champ.get("champLevel");
            int champPoints = (int) champ.get("champPoints");

            String summonerId = (String) champ.get("summonerId");
            Friend friend = friendRepositoryDao.findBySummonerId(summonerId);

            friendChamp.setChampId(champId);
            friendChamp.setChampLevel(champLevel);
            friendChamp.setChampPoints(champPoints);
            friendChamp.setFriend(friend);

            System.out.println("champId = " + champId);
            System.out.println("champLevel = " + champLevel);
            System.out.println("champPoints = " + champPoints);

            friendChampsRepositoryDao.save(friendChamp);
        }
    }
}
