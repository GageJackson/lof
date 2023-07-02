package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendChamps;
import com.gagejackson.lof.Models.FriendMatch;
import com.gagejackson.lof.Models.Match;
import com.gagejackson.lof.Repositories.FriendChampsRepository;
import com.gagejackson.lof.Repositories.FriendMatchRepository;
import com.gagejackson.lof.Repositories.FriendRepository;
import com.gagejackson.lof.Repositories.MatchRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class FriendMatchController {
    private final FriendRepository friendRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;

    public FriendMatchController(
            FriendRepository friendRepositoryDao,
            MatchRepository matchRepositoryDao,
            FriendMatchRepository friendMatchRepositoryDao
    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
        this.friendMatchRepositoryDao = friendMatchRepositoryDao;
    }

    @PostMapping("/saveFriendMatches")
    public void saveChampsData(@RequestBody Map<String, Object>[] matchesArray) {

        for (Map<String, Object> match : matchesArray) {
            FriendMatch friendMatch = new FriendMatch();

            String matchIdString = (String) match.get("matchId");
            long matchId = Long.parseLong(matchIdString.substring(matchIdString.indexOf("_") + 1));
            Match newMatch = matchRepositoryDao.findByGameId(matchId);

            String summonerId = (String) match.get("summonerId");
            Friend friend = friendRepositoryDao.findByPuuId(summonerId);

            friendMatch.setMatch(newMatch);
            friendMatch.setFriend(friend);

            System.out.println("friend.getId() = " + friend.getId());
            System.out.println("matchId = " + newMatch.getId());

            //friendChampsRepositoryDao.save(friendChamp);
        }
    }
}
