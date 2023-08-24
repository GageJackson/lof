package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.DTOs.FriendSelection;
import com.gagejackson.lof.DTOs.MatchInfo;
import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendMatch;
import com.gagejackson.lof.Models.MatchOverview.Match;
import com.gagejackson.lof.Models.MatchOverview.Participant;
import com.gagejackson.lof.Repositories.Friend.FriendChampsRepository;
import com.gagejackson.lof.Repositories.Friend.FriendMatchRepository;
import com.gagejackson.lof.Repositories.Friend.FriendRankRepository;
import com.gagejackson.lof.Repositories.Friend.FriendRepository;
import com.gagejackson.lof.Repositories.MatchEvent.MatchRepository;
import com.gagejackson.lof.Repositories.MatchOverview.ParticipantRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MatchController {
    private final FriendRepository friendRepositoryDao;
    private final FriendChampsRepository friendChampsRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;

    public MatchController(
            FriendRepository friendRepositoryDao,
            FriendChampsRepository friendChampsRepositoryDao,
            FriendRankRepository friendRankRepositoryDao,
            FriendMatchRepository friendMatchRepositoryDao,
            MatchRepository matchRepositoryDao,
            ParticipantRepository participantRepositoryDao
    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.friendChampsRepositoryDao = friendChampsRepositoryDao;
        this.friendRankRepositoryDao = friendRankRepositoryDao;
        this.friendMatchRepositoryDao = friendMatchRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
        this.participantRepositoryDao = participantRepositoryDao;
    }

    @GetMapping("/match")
    public String showMatch(Model model, @RequestParam("match") long matchId) {
        Optional<Match> matchObject = matchRepositoryDao.findById(matchId);
        Match match = matchObject.get();

        List<FriendSelection> friends = getFriends(Collections.singletonList(0L));

        List<FriendMatch> friendMatches = match.getFriendMatch();
        List<Friend> friendsInMatch = findFriendsInMatch(friendMatches);
        boolean friendWon = didFriendWin(match, friendsInMatch.get(0));

        MatchInfo matchInfo = new MatchInfo();
        matchInfo.setMatch(match);
        matchInfo.setFriends(friendsInMatch);
        matchInfo.setFriendWin(friendWon);

        model.addAttribute("matchInfo", matchInfo);
        model.addAttribute("friends", friends);

        return "detailed-match";
    }

    private List<FriendSelection> getFriends(List<Long> selection){
        List<FriendSelection> selectedFriends = new ArrayList<>();
        List<Friend> friends = friendRepositoryDao.findAll();

        for (Friend friend : friends) {
            FriendSelection selectedFriend = new FriendSelection();
            selectedFriend.setId(friend.getId());
            selectedFriend.setPuuId(friend.getPuuId());
            selectedFriend.setName(friend.getName());
            selectedFriend.setIcon(friend.getIcon());
            selectedFriend.setSummonerLevel(friend.getSummonerLevel());

            //if list is 0 / Collections.singletonList(0L) no friends will be highlighted
            selectedFriend.setSelected(selection.contains(friend.getId()));

            selectedFriends.add(selectedFriend);
        }

        return selectedFriends;
    }

    private List<Friend> findFriendsInMatch(List<FriendMatch> friendMatches){
        List<Friend> friendsInMatch = new ArrayList<>();

        for (FriendMatch friendMatch : friendMatches) {
            Friend friend = friendMatch.getFriend();
            friendsInMatch.add(friend);
        }

        return friendsInMatch;
    }

    private boolean didFriendWin(Match match, Friend friend){
        if(match.isSaved()){
            Participant participant = participantRepositoryDao.findByMatchAndPuuid(match,friend.getPuuId());
            return participant.isWin();
        } else {
            return false;
        }
    }
}
