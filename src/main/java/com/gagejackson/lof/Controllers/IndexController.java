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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class IndexController {

    private final FriendRepository friendRepositoryDao;
    private final MatchRepository matchRepositoryDao;

    public IndexController(
            FriendRepository friendRepositoryDao,
            MatchRepository matchRepositoryDao
    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
    }

    @GetMapping({"/", "/index"})
    public String goHome(Model model) {
        List<MatchInfo> matchInfos = new ArrayList<>();

        List<Match> matches = findAllMatches();
        List<FriendSelection> friends = getFriends(Collections.singletonList(0L));

        for (Match match : matches) {
            List<FriendMatch> friendMatches = match.getFriendMatch();
            List<Friend> friendsInMatch = findFriendsInMatch(friendMatches);
            boolean friendWon = didFriendWin(match, friendsInMatch.get(0));

            MatchInfo matchInfo = new MatchInfo();
            matchInfo.setMatch(match);
            matchInfo.setFriends(friendsInMatch);
            matchInfo.setFriendWin(friendWon);

            matchInfos.add(matchInfo);
        }

        model.addAttribute("matchInfos", matchInfos);
        model.addAttribute("friends", friends);

        return "index";
    }

    @GetMapping("/sort")
    public String homeSort(Model model, @RequestParam("friendIds") List<Long> friendIds) {
        List<MatchInfo> matchInfos = new ArrayList<>();

        List<Match> matches = findAllMatches();
        List<FriendSelection> friends = getFriends(friendIds);

        for (Match match : matches) {
            List<FriendMatch> friendMatches = match.getFriendMatch();
            List<Friend> friendsInMatch = findFriendsInMatch(friendMatches);
            boolean friendWon = didFriendWin(match, friendsInMatch.get(0));

            Set<Long> matchedFriendIds = new HashSet<>();
            for (FriendMatch friendMatch : friendMatches) {
                matchedFriendIds.add(friendMatch.getFriend().getId());
            }

            if (matchedFriendIds.containsAll(friendIds)) {
                MatchInfo matchInfo = new MatchInfo();
                matchInfo.setMatch(match);
                matchInfo.setFriends(friendsInMatch);
                matchInfo.setFriendWin(friendWon);

                matchInfos.add(matchInfo);
            }
        }

        model.addAttribute("matchInfos", matchInfos);
        model.addAttribute("friends", friends);

        return "index";
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

    private List<Match> findAllMatches(){
        String sortBy = "gameId";
        int page = 0;
        int matchCount = 100;
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, matchCount, sort);

        return matchRepositoryDao.findAll(pageable).getContent();
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
        boolean friendsWon = false;
        if(match.isSaved()){
            List<Participant> participants = match.getParticipant();
            for (Participant participant : participants) {
                if (participant.getPuuid().equals(friend.getPuuId())){
                    friendsWon = participant.isWin();
                }
            }
        }
        return friendsWon;
    }
}


