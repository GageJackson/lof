package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.Friend;
import com.gagejackson.lof.Models.Match;
import com.gagejackson.lof.Models.Participant;
import com.gagejackson.lof.Repositories.*;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MatchController {
    private final FriendRepository friendRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final BanRepository banRepositoryDao;
    private final ObjectiveRepository objectiveRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;
    private final PerkRepository perkRepositoryDao;
    private final TeamRepository teamRepositoryDao;


    public MatchController(
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

    @PostMapping("/saveMatchData")
    public void getMatchData(@RequestBody Map<String, Object> data) {
        Map<String, Object> info = (Map<String, Object>) data.get("info");

//        saveMatchData(info);
        saveParticipantData(info);

//        String summonerId = (String) data.get("summonerId");
//        Friend friend = friendRepositoryDao.findBySummonerId(summonerId);
//
//        int icon = (int) data.get("icon");
//        int summonerLevel = (int) data.get("summonerLevel");
//
//        friend.setIcon(icon);
//        friend.setSummonerLevel(summonerLevel);
//
//        System.out.println("icon = " + icon);
//        System.out.println("summonerLevel = " + summonerLevel);

        //friendRepositoryDao.save(friend);
    }

    private void saveMatchData(Map<String, Object> info){
        Match match = new Match();

        long gameCreation = (long) info.get("gameCreation");
        long gameDuration = (long) (int) info.get("gameDuration");
        long gameEnd = (long) info.get("gameEndTimestamp");
        long gameId = (long) info.get("gameId");
        String gameMode = (String) info.get("gameMode");
        String gameName = (String) info.get("gameName");
        long gameStart = (long) info.get("gameStartTimestamp");
        String gameType = (String) info.get("gameType");
        String gameVersion = (String) info.get("gameVersion");
        int mapId = (int) info.get("mapId");
        String platformId = (String) info.get("platformId");
        int queueId = (int) info.get("queueId");
        String tournamentCode = (String) info.get("tournamentCode");

        match.setGameCreation(gameCreation);
        match.setGameDuration(gameDuration);
        match.setGameEnd(gameEnd);
        match.setGameId(gameId);
        match.setGameMode(gameMode);
        match.setGameName(gameName);
        match.setGameStart(gameStart);
        match.setGameType(gameType);
        match.setGameVersion(gameVersion);
        match.setMapId(mapId);
        match.setPlatformId(platformId);
        match.setQueueId(queueId);
        match.setTournamentCode(tournamentCode);

        System.out.println("gameMode = " + gameMode);
    }

    private void saveParticipantData(Map<String, Object> info) {
        List<Map<String, Object>> participants = (List<Map<String, Object>>) info.get("participants");

        for (Map<String, Object> participant : participants) {
            System.out.println("participant.get(\"assists\") = " + participant.get("assists"));
        }
    }


}
