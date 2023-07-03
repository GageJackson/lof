package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.*;
import com.gagejackson.lof.Repositories.*;
import jakarta.persistence.Column;
import jakarta.persistence.TemporalType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

        Match newMatch = saveMatchData(info);
        saveParticipantData(info, newMatch);
        saveTeamData(info, newMatch);

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

    private Match saveMatchData(Map<String, Object> info){
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

        if(matchRepositoryDao.findByGameId(gameId) == null){
            Match savedMatch = matchRepositoryDao.save(match);
            return savedMatch;
        }

       return matchRepositoryDao.findByGameId(gameId);
    }

    private void saveParticipantData(Map<String, Object> info, Match newMatch) {
        List<Map<String, Object>> participants = (List<Map<String, Object>>) info.get("participants");

        for (Map<String, Object> participant : participants) {
            Participant participantObject = new Participant();

            participantObject.setAssists((int) participant.get("assists"));
            participantObject.setBaronKills((int) participant.get("baronKills"));
            participantObject.setBountyLevel((int) participant.get("bountyLevel"));
            participantObject.setChampExperience((int) participant.get("champExperience"));
            participantObject.setChampLevel((int) participant.get("champLevel"));
            participantObject.setChampionId((int) participant.get("championId"));
            participantObject.setChampionName((String) participant.get("championName"));
            participantObject.setChampionTransform((int) participant.get("championTransform"));
            participantObject.setConsumablesPurchased((int) participant.get("consumablesPurchased"));
            participantObject.setDamageDealtToBuildings((int) participant.get("damageDealtToBuildings"));
            participantObject.setDamageDealtToObjectives((int) participant.get("damageDealtToObjectives"));
            participantObject.setDamageDealtToTurrets((int) participant.get("damageDealtToTurrets"));
            participantObject.setDamageSelfMitigated((int) participant.get("damageSelfMitigated"));
            participantObject.setDeaths((int) participant.get("deaths"));
            participantObject.setDetectorWardsPlaced((int) participant.get("detectorWardsPlaced"));
            participantObject.setDoubleKills((int) participant.get("doubleKills"));
            participantObject.setDragonKills((int) participant.get("dragonKills"));
            participantObject.setFirstBloodAssist((boolean) participant.get("firstBloodAssist"));
            participantObject.setFirstBloodKill((boolean) participant.get("firstBloodKill"));
            participantObject.setFirstTowerAssist((boolean) participant.get("firstTowerAssist"));
            participantObject.setFirstTowerKill((boolean) participant.get("firstTowerKill"));
            participantObject.setGameEndedInEarlySurrender((boolean) participant.get("gameEndedInEarlySurrender"));
            participantObject.setGameEndedInSurrender((boolean) participant.get("gameEndedInSurrender"));
            participantObject.setGoldEarned((int) participant.get("goldEarned"));
            participantObject.setGoldSpent((int) participant.get("goldSpent"));
            participantObject.setIndividualPosition((String) participant.get("individualPosition"));
            participantObject.setInhibitorKills((int) participant.get("inhibitorKills"));
            participantObject.setInhibitorTakedowns((int) participant.get("inhibitorTakedowns"));
            participantObject.setInhibitorsLost((int) participant.get("inhibitorsLost"));
            participantObject.setItem0((int) participant.get("item0"));
            participantObject.setItem1((int) participant.get("item1"));
            participantObject.setItem2((int) participant.get("item2"));
            participantObject.setItem3((int) participant.get("item3"));
            participantObject.setItem4((int) participant.get("item4"));
            participantObject.setItem5((int) participant.get("item5"));
            participantObject.setItem6((int) participant.get("item6"));
            participantObject.setItemsPurchased((int) participant.get("itemsPurchased"));
            participantObject.setKillingSprees((int) participant.get("killingSprees"));
            participantObject.setKills((int) participant.get("kills"));
            participantObject.setLane((String) participant.get("lane"));
            participantObject.setLargestCriticalStrike((int) participant.get("largestCriticalStrike"));
            participantObject.setLargestKillingSpree((int) participant.get("largestKillingSpree"));
            participantObject.setLargestMultiKill((int) participant.get("largestMultiKill"));
            participantObject.setLongestTimeSpentLiving((int) participant.get("longestTimeSpentLiving"));
            participantObject.setMagicDamageDealt((int) participant.get("magicDamageDealt"));
            participantObject.setMagicDamageDealtToChampions((int) participant.get("magicDamageDealtToChampions"));
            participantObject.setMagicDamageTaken((int) participant.get("magicDamageTaken"));
            participantObject.setNeutralMinionsKilled((int) participant.get("neutralMinionsKilled"));
            participantObject.setNexusKills((int) participant.get("nexusKills"));
            participantObject.setNexusTakedowns((int) participant.get("nexusTakedowns"));
            participantObject.setNexusLost((int) participant.get("nexusLost"));
            participantObject.setObjectivesStolen((int) participant.get("objectivesStolen"));
            participantObject.setObjectivesStolenAssists((int) participant.get("objectivesStolenAssists"));
            participantObject.setParticipantId((int) participant.get("participantId"));
            participantObject.setPentaKills((int) participant.get("pentaKills"));
            participantObject.setPhysicalDamageDealt((int) participant.get("physicalDamageDealt"));
            participantObject.setPhysicalDamageDealtToChampions((int) participant.get("physicalDamageDealtToChampions"));
            participantObject.setPhysicalDamageTaken((int) participant.get("physicalDamageTaken"));
            participantObject.setProfileIcon((int) participant.get("profileIcon"));
            participantObject.setPuuid((String) participant.get("puuid"));
            participantObject.setQuadraKills((int) participant.get("quadraKills"));
            participantObject.setRiotIdName((String) participant.get("riotIdName"));
            participantObject.setRiotIdTagline((String) participant.get("riotIdTagline"));
            participantObject.setRole((String) participant.get("role"));
            participantObject.setSightWardsBoughtInGame((int) participant.get("sightWardsBoughtInGame"));
            participantObject.setSpell1Casts((int) participant.get("spell1Casts"));
            participantObject.setSpell2Casts((int) participant.get("spell2Casts"));
            participantObject.setSpell3Casts((int) participant.get("spell3Casts"));
            participantObject.setSpell4Casts((int) participant.get("spell4Casts"));
            participantObject.setSummoner1Casts((int) participant.get("summoner1Casts"));
            participantObject.setSummoner1Id((int) participant.get("summoner1Id"));
            participantObject.setSummoner2Casts((int) participant.get("summoner2Casts"));
            participantObject.setSummoner2Id((int) participant.get("summoner2Id"));
            participantObject.setSummonerId((String) participant.get("summonerId"));
            participantObject.setSummonerLevel((int) participant.get("summonerLevel"));
            participantObject.setSummonerName((String) participant.get("summonerName"));
            participantObject.setTeamEarlySurrendered((boolean) participant.get("teamEarlySurrendered"));
            participantObject.setTeamId((int) participant.get("teamId"));
            participantObject.setTeamPosition((String) participant.get("teamPosition"));
            participantObject.setTimeCCingOthers((int) participant.get("timeCCingOthers"));
            participantObject.setTimePlayed((int) participant.get("timePlayed"));
            participantObject.setTotalDamageDealt((int) participant.get("totalDamageDealt"));
            participantObject.setTotalDamageDealtToChampions((int) participant.get("totalDamageDealtToChampions"));
            participantObject.setTotalDamageShieldedOnTeammates((int) participant.get("totalDamageShieldedOnTeammates"));
            participantObject.setTotalDamageTaken((int) participant.get("totalDamageTaken"));
            participantObject.setTotalHeal((int) participant.get("totalHeal"));
            participantObject.setTotalHealsOnTeammates((int) participant.get("totalHealsOnTeammates"));
            participantObject.setTotalMinionsKilled((int) participant.get("totalMinionsKilled"));
            participantObject.setTotalTimeCCDealt((int) participant.get("totalTimeCCDealt"));
            participantObject.setTotalTimeSpentDead((int) participant.get("totalTimeSpentDead"));
            participantObject.setTotalUnitsHealed((int) participant.get("totalUnitsHealed"));
            participantObject.setTripleKills((int) participant.get("tripleKills"));
            participantObject.setTrueDamageDealt((int) participant.get("trueDamageDealt"));
            participantObject.setTrueDamageDealtToChampions((int) participant.get("trueDamageDealtToChampions"));
            participantObject.setTrueDamageTaken((int) participant.get("trueDamageTaken"));
            participantObject.setTurretKills((int) participant.get("turretKills"));
            participantObject.setTurretTakedowns((int) participant.get("turretTakedowns"));
            participantObject.setTurretsLost((int) participant.get("turretsLost"));
            participantObject.setUnrealKills((int) participant.get("unrealKills"));
            participantObject.setVisionScore((int) participant.get("visionScore"));
            participantObject.setVisionWardsBoughtInGame((int) participant.get("visionWardsBoughtInGame"));
            participantObject.setWardsKilled((int) participant.get("wardsKilled"));
            participantObject.setWardsPlaced((int) participant.get("wardsPlaced"));
            participantObject.setWin((boolean) participant.get("win"));
            participantObject.setMatch(newMatch);

            Participant newParticipant = participantRepositoryDao.save(participantObject);
            savePerkData(participant, newParticipant);
        }
    }

    private void savePerkData(Map<String, Object> info, Participant newParticipant) {
        Map<String, Object> perks = (Map<String, Object>) info.get("perks");
        List<Map<String, Object>> styles = (List<Map<String, Object>>) perks.get("styles");
        System.out.println("styles = " + styles);

        for (Map<String, Object> style : styles) {
            String description = (String) style.get("description");
            List<Map<String, Object>> selections = (List<Map<String, Object>>) style.get("selections");
            int styleNumber = (int) style.get("style");

            for (Map<String, Object> selection : selections) {
                int perkNumber = (int) selection.get("perk");
                int var1 = (int) selection.get("var1");
                int var2 = (int) selection.get("var2");
                int var3 = (int) selection.get("var3");

                Perk perkData = new Perk();
                perkData.setPerkId(perkNumber);
                perkData.setStyle(styleNumber);
                perkData.setPerkStat1(var1);
                perkData.setPerkStat2(var2);
                perkData.setPerkStat3(var3);
                perkData.setParticipant(newParticipant);

                if (description.equals("primaryStyle")){
                    perkData.setPrimary(true);
                } else {
                    perkData.setPrimary(false);
                }

                perkRepositoryDao.save(perkData);
            }
        }
    }

    private void saveTeamData(Map<String, Object> info, Match newMatch) {
        List<Map<String, Object>> teams = (List<Map<String, Object>>) info.get("teams");

        for (Map<String, Object> team : teams) {
            Team teamObject = new Team();

            int teamId = (int) team.get("teamId");

            if ((team.get("win")).equals("true")){
                teamObject.setWin(true);
            } else {
                teamObject.setWin(false);
            }

            teamObject.setTeamId(teamId);
            teamObject.setMatch(newMatch);

            Team newTeam = teamRepositoryDao.save(teamObject);
            saveBanData(team, newTeam);
            saveObjectiveData(team, newTeam);
        }
    }

    private void saveBanData(Map<String, Object> info, Team newTeam) {
        List<Map<String, Object>> bans = (List<Map<String, Object>>) info.get("bans");
        if(bans.isEmpty()){
            return;
        }
        for (Map<String, Object> ban : bans) {
            int champId = (int) ban.get("championId");
            int pickTurn = (int) ban.get("pickTurn");

            Ban banData = new Ban();
            banData.setChampId(champId);
            banData.setPickTurn(pickTurn);
            banData.setTeam(newTeam);

            banRepositoryDao.save(banData);
        }
    }

    private void saveObjectiveData(Map<String, Object> info, Team newTeam) {
        Map<String, Object> objectives = (Map<String, Object>) info.get("objectives");
        if(objectives.size() == 0){
            System.out.println("empty");
            return;
        }

        Map<String, Object> baron = (Map<String, Object>) objectives.get("baron");
        Objective baronObjectiveData = new Objective();
        baronObjectiveData.setName("baron");
        baronObjectiveData.setFirst((boolean) baron.get("first"));
        baronObjectiveData.setKills((int) baron.get("kills"));
        baronObjectiveData.setTeam(newTeam);
        objectiveRepositoryDao.save(baronObjectiveData);

        Map<String, Object> champion = (Map<String, Object>) objectives.get("champion");
        Objective championObjectiveData = new Objective();
        championObjectiveData.setName("champion");
        championObjectiveData.setFirst((boolean) champion.get("first"));
        championObjectiveData.setKills((int) champion.get("kills"));
        championObjectiveData.setTeam(newTeam);
        objectiveRepositoryDao.save(championObjectiveData);

        Map<String, Object> dragon = (Map<String, Object>) objectives.get("dragon");
        Objective dragonObjectiveData = new Objective();
        dragonObjectiveData.setName("dragon");
        dragonObjectiveData.setFirst((boolean) dragon.get("first"));
        dragonObjectiveData.setKills((int) dragon.get("kills"));
        dragonObjectiveData.setTeam(newTeam);
        objectiveRepositoryDao.save(dragonObjectiveData);

        Map<String, Object> inhibitor = (Map<String, Object>) objectives.get("inhibitor");
        Objective inhibitorObjectiveData = new Objective();
        inhibitorObjectiveData.setName("inhibitor");
        inhibitorObjectiveData.setFirst((boolean) inhibitor.get("first"));
        inhibitorObjectiveData.setKills((int) inhibitor.get("kills"));
        inhibitorObjectiveData.setTeam(newTeam);
        objectiveRepositoryDao.save(inhibitorObjectiveData);

        Map<String, Object> riftHerald = (Map<String, Object>) objectives.get("riftHerald");
        Objective riftHeraldObjectiveData = new Objective();
        riftHeraldObjectiveData.setName("riftHerald");
        riftHeraldObjectiveData.setFirst((boolean) riftHerald.get("first"));
        riftHeraldObjectiveData.setKills((int) riftHerald.get("kills"));
        riftHeraldObjectiveData.setTeam(newTeam);
        objectiveRepositoryDao.save(riftHeraldObjectiveData);

        Map<String, Object> tower = (Map<String, Object>) objectives.get("tower");
        Objective towerObjectiveData = new Objective();
        towerObjectiveData.setName("tower");
        towerObjectiveData.setFirst((boolean) tower.get("first"));
        towerObjectiveData.setKills((int) tower.get("kills"));
        towerObjectiveData.setTeam(newTeam);
        objectiveRepositoryDao.save(towerObjectiveData);

    }
}
