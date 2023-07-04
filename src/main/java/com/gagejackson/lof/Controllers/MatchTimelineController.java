package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.*;
import com.gagejackson.lof.Repositories.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MatchTimelineController {
    private final FriendRepository friendRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final BanRepository banRepositoryDao;
    private final ObjectiveRepository objectiveRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;
    private final PerkRepository perkRepositoryDao;
    private final TeamRepository teamRepositoryDao;


    public MatchTimelineController(
        FriendRepository friendRepositoryDao,
        MatchRepository matchRepositoryDao,
        FriendMatchRepository friendMatchRepositoryDao,
        BanRepository banRepositoryDao,
        ObjectiveRepository objectiveRepositoryDao,
        ParticipantRepository participantRepositoryDao,
        PerkRepository perkRepositoryDao,
        TeamRepository teamRepositoryDao
    ) {
        this.friendRepositoryDao = friendRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
        this.friendMatchRepositoryDao = friendMatchRepositoryDao;
        this.banRepositoryDao = banRepositoryDao;
        this.objectiveRepositoryDao = objectiveRepositoryDao;
        this.participantRepositoryDao = participantRepositoryDao;
        this.perkRepositoryDao = perkRepositoryDao;
        this.teamRepositoryDao = teamRepositoryDao;
    }

    @PostMapping("/saveMatchTimelineData")
    public void getMatchData(@RequestBody Map<String, Object> data) {
        Map<String, Object> info = (Map<String, Object>) data.get("info");
        List<Map<String, Object>> frames = (List<Map<String, Object>>) info.get("frames");


//        Match newMatch = saveMatchData(info);
        saveParticipantFramesData(frames);
//        saveTeamData(info, newMatch);
    }

    private void saveParticipantFramesData(List<Map<String, Object>> frames){
        for (Map<String, Object> frame:frames) {
            Map<String, Object> participantFrames = (Map<String, Object>) frame.get("participantFrames");

        }
    }
}
