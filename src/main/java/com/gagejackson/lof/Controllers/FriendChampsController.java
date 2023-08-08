package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendChamps;
import com.gagejackson.lof.Repositories.Friend.FriendChampsRepository;
import com.gagejackson.lof.Repositories.Friend.FriendRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
        List<FriendChamps> updatedFriendChamps = new ArrayList<>();
        int listCounter = 0;
        Friend friend = null;
        List<FriendChamps> friendChampList = null;


        for (Map<String, Object> champ : champsArray) {
            int champId = (int) champ.get("champId");
            int champLevel = (int) champ.get("champLevel");
            int champPoints = (int) champ.get("champPoints");
            String summonerId = (String) champ.get("summonerId");

            if(friend == null){
                friend = friendRepositoryDao.findBySummonerId(summonerId);
                friendChampList = friendChampsRepositoryDao.findFriendChampsByFriend(friend);
            }

            if (friendChampList.size() < updatedFriendChamps.size() + 1){
                FriendChamps friendChamp = new FriendChamps();
                friendChamp.setChampId(champId);
                friendChamp.setChampLevel(champLevel);
                friendChamp.setChampPoints(champPoints);
                friendChamp.setFriend(friend);

                updatedFriendChamps.add(friendChamp);

            } else {
                FriendChamps friendChamp = friendChampList.get(updatedFriendChamps.size());
                friendChamp.setChampId(champId);
                friendChamp.setChampLevel(champLevel);
                friendChamp.setChampPoints(champPoints);

                updatedFriendChamps.add(friendChamp);
            }
        }
        friendChampsRepositoryDao.saveAll(updatedFriendChamps);
    }
}
