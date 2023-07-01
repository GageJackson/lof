package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.FriendRank;
import com.gagejackson.lof.Repositories.FriendChampsRepository;
import com.gagejackson.lof.Repositories.FriendRankRepository;
import com.gagejackson.lof.Repositories.FriendRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class IndexController {

    private final FriendRepository friendRepositoryDao;
    private final FriendChampsRepository friendChampsRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;

    public IndexController(
            FriendRepository friendRepositoryDao,
            FriendChampsRepository friendChampsRepositoryDao,
            FriendRankRepository friendRankRepositoryDao
    ){
            this.friendRepositoryDao = friendRepositoryDao;
            this.friendChampsRepositoryDao = friendChampsRepositoryDao;
            this.friendRankRepositoryDao = friendRankRepositoryDao;
    }

    @GetMapping("/")
    public String goHome(Model model) {

        return "index";
    }
}
