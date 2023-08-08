package com.gagejackson.lof.Controllers;

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

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final FriendRepository friendRepositoryDao;
    private final FriendChampsRepository friendChampsRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;

    public IndexController(
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

    @GetMapping("/")
    public String goHome(Model model) {
        List<MatchInfo> matchInfos = new ArrayList<>();

        Sort sort = Sort.by(Sort.Direction.DESC, "gameId");
        Pageable pageable = PageRequest.of(0, 50, sort);
        List<Match> matches = matchRepositoryDao.findAll(pageable).getContent();

        for (Match match : matches) {
            List<FriendMatch> friendMatches = friendMatchRepositoryDao.findAllByMatch(match);
            List<Friend> friendsInMatch = new ArrayList<>();
            boolean friendWinIsSet = false;
            boolean friendWon = false;

            for (FriendMatch friendMatch : friendMatches) {
                Friend friend = friendMatch.getFriend();


                if(!friendWinIsSet && match.isSaved()){
                    friendWinIsSet = true;
                    Participant participant = participantRepositoryDao.findByMatchAndPuuid(match,friend.getPuuId());
                    friendWon = participant.isWin();
                }

                friendsInMatch.add(friend);
            }

            MatchInfo matchInfo = new MatchInfo();
            matchInfo.setMatch(match);
            matchInfo.setFriends(friendsInMatch);
            matchInfo.setFriendWin(friendWon);

            matchInfos.add(matchInfo);
        }

        model.addAttribute("matchInfos", matchInfos);

        List<Friend> friends = friendRepositoryDao.findAll();
        model.addAttribute("friends", friends);

        return "index";
    }

}
