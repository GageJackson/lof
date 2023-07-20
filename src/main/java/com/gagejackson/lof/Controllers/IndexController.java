package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.FriendMatch;
import com.gagejackson.lof.Models.FriendRank;
import com.gagejackson.lof.Models.Match;
import com.gagejackson.lof.Repositories.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final FriendRepository friendRepositoryDao;
    private final FriendChampsRepository friendChampsRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final MatchRepository matchRepositoryDao;

    public IndexController(
            FriendRepository friendRepositoryDao,
            FriendChampsRepository friendChampsRepositoryDao,
            FriendRankRepository friendRankRepositoryDao,
            FriendMatchRepository friendMatchRepositoryDao,
            MatchRepository matchRepositoryDao
    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.friendChampsRepositoryDao = friendChampsRepositoryDao;
        this.friendRankRepositoryDao = friendRankRepositoryDao;
        this.friendMatchRepositoryDao = friendMatchRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
    }

    @GetMapping("/")
    public String goHome(Model model) {

        //friendRepositoryDao.findAll();
        //friendRankRepositoryDao.findAll();
        //friendChampsRepositoryDao.findAll();
        List<FriendMatch> allFriendMatches = new ArrayList<>();

        Sort sort = Sort.by(Sort.Direction.DESC, "gameId");
        Pageable pageable = PageRequest.of(0, 20, sort);
        List<Match> matches = matchRepositoryDao.findAll(pageable).getContent();
        model.addAttribute("matches", matches);

        for (Match match : matches) {
            List <FriendMatch> friendMatches = friendMatchRepositoryDao.findAllByMatch(match);
            allFriendMatches.addAll(friendMatches);
        }
        model.addAttribute("friendMatches", allFriendMatches);

        List<Friend> friends = friendRepositoryDao.findAll();
        model.addAttribute("friends", friends);


        return "index";
    }
}
