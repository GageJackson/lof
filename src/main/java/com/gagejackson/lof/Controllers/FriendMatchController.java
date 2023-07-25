package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendChamps;
import com.gagejackson.lof.Models.FriendMatch;
import com.gagejackson.lof.Models.Match;
import com.gagejackson.lof.Repositories.FriendChampsRepository;
import com.gagejackson.lof.Repositories.FriendMatchRepository;
import com.gagejackson.lof.Repositories.FriendRepository;
import com.gagejackson.lof.Repositories.MatchRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    public void saveChampsData(@RequestBody Map<String, Object>[] matchesData) {
        List<FriendMatch> friendMatches = new ArrayList<>();
        List<Match> matches = new ArrayList<>();
        Friend friend = null;

        for (Map<String, Object> matchData : matchesData) {
            FriendMatch friendMatch = new FriendMatch();

            String puuId = (String) matchData.get("puuId");
            if(friend == null){
                friend = friendRepositoryDao.findByPuuId(puuId);
            }

            String matchIdString = (String) matchData.get("matchId");
            long matchId = Long.parseLong(matchIdString.substring(matchIdString.indexOf("_") + 1));

            Match oldMatch = matchRepositoryDao.findByGameId(matchId);
            if(oldMatch != null){
                if(friendMatchRepositoryDao.findFriendMatchByFriendAndMatch(friend, oldMatch) == null){
                    friendMatch.setMatch(oldMatch);
                    friendMatch.setFriend(friend);
                    friendMatches.add(friendMatch);
                }

            } else {
                Match newMatch = new Match();
                newMatch.setSaved(false);
                newMatch.setGameId(matchId);
                matches.add(newMatch);

                friendMatch.setMatch(newMatch);
                friendMatch.setFriend(friend);
                friendMatches.add(friendMatch);
            }
        }
        matchRepositoryDao.saveAll(matches);
        friendMatchRepositoryDao.saveAll(friendMatches);
    }
}
