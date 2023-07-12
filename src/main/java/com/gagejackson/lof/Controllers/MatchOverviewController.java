package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.*;
import com.gagejackson.lof.Repositories.*;
//import jdk.nashorn.internal.runtime.ListAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MatchOverviewController {
    private final FriendRepository friendRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final BanRepository banRepositoryDao;
    private final EventRepository eventRepositoryDao;
    private final BuildingKillRepository buildingKillRepositoryDao;
    private final ChampKillRepository champKillRepositoryDao;
    private final DamageDealtRepository damageDealtRepositoryDao;
    private final DamageReceivedRepository damageReceivedRepositoryDao;
    private final SpecialKillRepository specialKillRepositoryDao;
    private final MonsterKillRepository monsterKillRepositoryDao;
    private final ItemRepository itemRepositoryDao;
    private final LevelUpRepository levelUpRepositoryDao;
    private final SkillUpRepository skillUpRepositoryDao;
    private final ObjectiveRepository objectiveRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;
    private final ParticipantFrameRepository participantFrameRepositoryDao;
    private final ParticipantFrameChampRepository participantFrameChampRepositoryDao;
    private final ParticipantFrameDamageRepository participantFrameDamageRepositoryDao;
    private final PerkRepository perkRepositoryDao;
    private final TeamRepository teamRepositoryDao;

    private List<Event> events = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<ParticipantFrame> participantFrames = new ArrayList<>();
    private List<ParticipantFrameChamp> participantFrameChamps = new ArrayList<>();
    private List<ParticipantFrameDamage> participantFrameDamages = new ArrayList<>();
    private List<SkillUp> skillUps = new ArrayList<>();
    private List<LevelUp> levelUps = new ArrayList<>();
    private List<BuildingKill> buildingKills = new ArrayList<>();
    private List<MonsterKill> monsterKills = new ArrayList<>();
    private List<ChampKill> champKills = new ArrayList<>();
    private List<DamageDealt> damageDealtList = new ArrayList<>();
    private List<DamageReceived> damageReceivedList = new ArrayList<>();
    private List<SpecialKill> specialKills = new ArrayList<>();

    //private Map<Integer, Participant> participantCache = new HashMap<>();

    private Map<Integer, Participant> participants = new HashMap<>();



    public MatchOverviewController(
            FriendRepository friendRepositoryDao,
            MatchRepository matchRepositoryDao,
            FriendMatchRepository friendMatchRepositoryDao,
            BanRepository banRepositoryDao,
            EventRepository eventRepositoryDao,
            BuildingKillRepository buildingKillRepositoryDao,
            ChampKillRepository champKillRepositoryDao,
            DamageDealtRepository damageDealtRepositoryDao,
            DamageReceivedRepository damageReceivedRepositoryDao,
            SpecialKillRepository specialKillRepositoryDao,
            MonsterKillRepository monsterKillRepositoryDao,
            ItemRepository itemRepositoryDao,
            LevelUpRepository levelUpRepositoryDao,
            SkillUpRepository skillUpRepositoryDao,
            ObjectiveRepository objectiveRepositoryDao,
            ParticipantRepository participantRepositoryDao,
            ParticipantFrameRepository participantFrameRepositoryDao,
            ParticipantFrameChampRepository participantFrameChampRepositoryDao,
            ParticipantFrameDamageRepository participantFrameDamageRepositoryDao,
            PerkRepository perkRepositoryDao,
            TeamRepository teamRepositoryDao
    ) {
        this.friendRepositoryDao = friendRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
        this.friendMatchRepositoryDao = friendMatchRepositoryDao;
        this.banRepositoryDao = banRepositoryDao;
        this.eventRepositoryDao = eventRepositoryDao;
        this.buildingKillRepositoryDao = buildingKillRepositoryDao;
        this.champKillRepositoryDao = champKillRepositoryDao;
        this.damageDealtRepositoryDao = damageDealtRepositoryDao;
        this.damageReceivedRepositoryDao = damageReceivedRepositoryDao;
        this.specialKillRepositoryDao = specialKillRepositoryDao;
        this.monsterKillRepositoryDao = monsterKillRepositoryDao;
        this.itemRepositoryDao = itemRepositoryDao;
        this.levelUpRepositoryDao = levelUpRepositoryDao;
        this.skillUpRepositoryDao = skillUpRepositoryDao;
        this.objectiveRepositoryDao = objectiveRepositoryDao;
        this.participantRepositoryDao = participantRepositoryDao;
        this.participantFrameRepositoryDao = participantFrameRepositoryDao;
        this.participantFrameChampRepositoryDao = participantFrameChampRepositoryDao;
        this.participantFrameDamageRepositoryDao = participantFrameDamageRepositoryDao;
        this.perkRepositoryDao = perkRepositoryDao;
        this.teamRepositoryDao = teamRepositoryDao;
    }

    @PostMapping("/saveMatchData")
    public void saveMatchData(@RequestBody List<Map<String, Object>> matches) {
        Map<String, Object> overviewMatch = (Map<String, Object>) matches.get(0);
        Map<String, Object> overviewData = (Map<String, Object>) overviewMatch.get("info");

        Map<String, Object> timelineMatch = (Map<String, Object>) matches.get(1);
        Map<String, Object> timelineData = (Map<String, Object>) timelineMatch.get("info");
        List<Map<String, Object>> timelineFrames = (List<Map<String, Object>>) timelineData.get("frames");

        Match newMatch = saveMatchData(overviewData);
        saveParticipantData(overviewData, newMatch);
        saveTeamData(overviewData, newMatch);
        saveMatchTimeline(timelineFrames, newMatch);
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
            return matchRepositoryDao.save(match);
        }

       return matchRepositoryDao.findByGameId(gameId);
    }

    private void saveParticipantData(Map<String, Object> info, Match newMatch) {
        List<Map<String, Object>> participants = (List<Map<String, Object>>) info.get("participants");
        List<Participant> participantsToSave = new ArrayList<>();

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
            participantObject.setParticipantNum((int) participant.get("participantId"));
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

            participantsToSave.add(participantObject);
        }

        List<Participant> newParticipants = participantRepositoryDao.saveAll(participantsToSave);

        for (int i = 0; i < newParticipants.size(); i++) {
            savePerkData(participants.get(i), newParticipants.get(i));
        }
    }

    private void savePerkData(Map<String, Object> info, Participant newParticipant) {
        Map<String, Object> perks = (Map<String, Object>) info.get("perks");
        List<Map<String, Object>> styles = (List<Map<String, Object>>) perks.get("styles");
        List<Perk> perksToSave = new ArrayList<>();

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
                perkData.setPerkNum(perkNumber);
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

                perksToSave.add(perkData);
            }
        }
        perkRepositoryDao.saveAll(perksToSave);
    }

    private void saveTeamData(Map<String, Object> info, Match newMatch) {
        List<Map<String, Object>> teams = (List<Map<String, Object>>) info.get("teams");
        List<Team> teamObjects = new ArrayList<>();

        for (Map<String, Object> team : teams) {
            Team teamObject = new Team();

            int teamId = (int) team.get("teamId");

            if ((team.get("win")).equals("true")) {
                teamObject.setWin(true);
            } else {
                teamObject.setWin(false);
            }

            teamObject.setTeamNum(teamId);
            teamObject.setMatch(newMatch);

            teamObjects.add(teamObject);
        }

        List<Team> newTeams = teamRepositoryDao.saveAll(teamObjects);

        for (int i = 0; i < newTeams.size(); i++) {
            saveBanData(teams.get(i), newTeams.get(i));
            saveObjectiveData(teams.get(i), newTeams.get(i));
        }
    }

    private void saveBanData(Map<String, Object> info, Team newTeam) {
        List<Map<String, Object>> bans = (List<Map<String, Object>>) info.get("bans");
        List<Ban> bansToSave = new ArrayList<>();
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

           bansToSave.add(banData);
        }
        banRepositoryDao.saveAll(bansToSave);
    }

    private void saveObjectiveData(Map<String, Object> info, Team newTeam) {
        Map<String, Object> objectives = (Map<String, Object>) info.get("objectives");
        List<Objective> objectivesToSave = new ArrayList<>();

        if(objectives.size() == 0){
            System.out.println("empty");
            return;
        }

        objectivesToSave.add(saveObjective("baron", objectives, newTeam));
        objectivesToSave.add(saveObjective("champion", objectives, newTeam));
        objectivesToSave.add(saveObjective("dragon", objectives, newTeam));
        objectivesToSave.add(saveObjective("inhibitor", objectives, newTeam));
        objectivesToSave.add(saveObjective("riftHerald", objectives, newTeam));
        objectivesToSave.add(saveObjective("tower", objectives, newTeam));

        objectiveRepositoryDao.saveAll(objectivesToSave);
    }

    private Objective saveObjective (String objectiveName, Map<String, Object> objectives, Team newTeam){
        Map<String, Object> objective = (Map<String, Object>) objectives.get(objectiveName);
        Objective objectiveData = new Objective();

        objectiveData.setName(objectiveName);
        objectiveData.setFirst((boolean) objective.get("first"));
        objectiveData.setKills((int) objective.get("kills"));
        objectiveData.setTeam(newTeam);

        return objectiveData;
    }

    private void saveMatchTimeline(List<Map<String, Object>> frames, Match newMatch){
        int currentFrame = 0;
        participants = getParticipantsByMatch(newMatch);

        for (Map<String, Object> frame : frames) {
            Map<String, Object> participantFrames = (Map<String, Object>) frame.get("participantFrames");

            List<Map<String, Object>> events = (List<Map<String, Object>>) frame.get("events");
            for (Map<String, Object> event : events) {
//                getEventInfo(event); //useful for probing for information about events
                saveEvent(event, newMatch);
            }

            for (int i = 1; i <= participantFrames.size(); i++) {
                Participant participant = participants.get(i);
                saveParticipantFrame(participantFrames, newMatch, currentFrame, i, participant);
            }
            currentFrame++;
        }

        insertBatchData();
    }

    private void saveParticipantFrame(Map<String, Object> participantFrames, Match newMatch, int currentFrame, int i, Participant participant){
        Map<String, Object> participantFrameData = (Map<String, Object>) participantFrames.get(String.valueOf(i));
        ParticipantFrame participantFrame = new ParticipantFrame();

        participantFrame.setCurrentGold((int) participantFrameData.get("currentGold"));
        participantFrame.setJungleMinionsKilled((int) participantFrameData.get("jungleMinionsKilled"));
        participantFrame.setLevel((int) participantFrameData.get("level"));
        participantFrame.setMinionsKilled((int) participantFrameData.get("minionsKilled"));
        participantFrame.setTimeEnemySpentControlled((int) participantFrameData.get("timeEnemySpentControlled"));
        participantFrame.setTotalGold((int) participantFrameData.get("totalGold"));
        participantFrame.setXp((int) participantFrameData.get("xp"));
        participantFrame.setFrame(currentFrame);
        participantFrame.setParticipant(participant);

        ParticipantFrame newParticipantFrame = participantFrameRepositoryDao.save(participantFrame);

        saveParticipantFrameChampData(participantFrameData, newParticipantFrame);
        saveParticipantFrameDamageData(participantFrameData, newParticipantFrame);
    }

    private Map<Integer, Participant> getParticipantsByMatch(Match newMatch) {
        List<Participant> participants = participantRepositoryDao.findByMatch(newMatch);
        Map<Integer, Participant> participantMap = new HashMap<>();
        for (Participant participant : participants) {
            participantMap.put(participant.getParticipantNum(), participant);
        }
        return participantMap;
    }

    private void saveParticipantFrameChampData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameChamp participantFrameChamp = new ParticipantFrameChamp();
        Map<String, Object> participantFrameChampData = (Map<String, Object>) participantFrameData.get("championStats");


        participantFrameChamp.setAbilityHaste((int) participantFrameChampData.get("abilityHaste"));
        participantFrameChamp.setAbilityPower((int) participantFrameChampData.get("abilityPower"));
        participantFrameChamp.setArmor((int) participantFrameChampData.get("armor"));
        participantFrameChamp.setArmorPen((int) participantFrameChampData.get("armorPen"));
        participantFrameChamp.setArmorPenPercent((int) participantFrameChampData.get("armorPenPercent"));
        participantFrameChamp.setAttackDamage((int) participantFrameChampData.get("attackDamage"));
        participantFrameChamp.setAttackSpeed((int) participantFrameChampData.get("attackSpeed"));
        participantFrameChamp.setBonusArmorPenPercent((int) participantFrameChampData.get("bonusArmorPenPercent"));
        participantFrameChamp.setBonusMagicPenPercent((int) participantFrameChampData.get("bonusMagicPenPercent"));
        participantFrameChamp.setCcReduction((int) participantFrameChampData.get("ccReduction"));
        participantFrameChamp.setCooldownReduction((int) participantFrameChampData.get("cooldownReduction"));
        participantFrameChamp.setHealth((int) participantFrameChampData.get("health"));
        participantFrameChamp.setHealthMax((int) participantFrameChampData.get("healthMax"));
        participantFrameChamp.setHealthRegen((int) participantFrameChampData.get("healthRegen"));
        participantFrameChamp.setLifesteal((int) participantFrameChampData.get("lifesteal"));
        participantFrameChamp.setMagicPen((int) participantFrameChampData.get("magicPen"));
        participantFrameChamp.setMagicPenPercent((int) participantFrameChampData.get("magicPenPercent"));
        participantFrameChamp.setMagicResist((int) participantFrameChampData.get("magicResist"));
        participantFrameChamp.setMovementSpeed((int) participantFrameChampData.get("movementSpeed"));
        participantFrameChamp.setOmnivamp((int) participantFrameChampData.get("omnivamp"));
        participantFrameChamp.setPhysicalVamp((int) participantFrameChampData.get("physicalVamp"));
        participantFrameChamp.setPower((int) participantFrameChampData.get("power"));
        participantFrameChamp.setPowerMax((int) participantFrameChampData.get("powerMax"));
        participantFrameChamp.setPowerRegen((int) participantFrameChampData.get("powerRegen"));
        participantFrameChamp.setSpellVamp((int) participantFrameChampData.get("spellVamp"));
        participantFrameChamp.setParticipantFrame(newParticipantFrame);

        participantFrameChamps.add(participantFrameChamp);
    }

    private void saveParticipantFrameDamageData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameDamage participantFrameDamage = new ParticipantFrameDamage();
        Map<String, Object> participantFrameDamageData = (Map<String, Object>) participantFrameData.get("damageStats");

        participantFrameDamage.setMagicDamageDone((int) participantFrameDamageData.get("magicDamageDone"));
        participantFrameDamage.setMagicDamageDone((int) participantFrameDamageData.get("magicDamageDone"));
        participantFrameDamage.setMagicDamageDoneToChamps((int) participantFrameDamageData.get("magicDamageDoneToChampions"));
        participantFrameDamage.setMagicDamageTaken((int) participantFrameDamageData.get("magicDamageTaken"));
        participantFrameDamage.setPhysicalDamageDone((int) participantFrameDamageData.get("physicalDamageDone"));
        participantFrameDamage.setPhysicalDamageDoneToChamps((int) participantFrameDamageData.get("physicalDamageDoneToChampions"));
        participantFrameDamage.setPhysicalDamageTaken((int) participantFrameDamageData.get("physicalDamageTaken"));
        participantFrameDamage.setTotalDamageDone((int) participantFrameDamageData.get("totalDamageDone"));
        participantFrameDamage.setTotalDamageDoneToChamps((int) participantFrameDamageData.get("totalDamageDoneToChampions"));
        participantFrameDamage.setTotalDamageTaken((int) participantFrameDamageData.get("totalDamageTaken"));
        participantFrameDamage.setTrueDamageDone((int) participantFrameDamageData.get("trueDamageDone"));
        participantFrameDamage.setTrueDamageDoneToChamps((int) participantFrameDamageData.get("trueDamageDoneToChampions"));
        participantFrameDamage.setTrueDamageTaken((int) participantFrameDamageData.get("trueDamageTaken"));
        participantFrameDamage.setParticipantFrame(newParticipantFrame);

        participantFrameDamages.add(participantFrameDamage);
    }

    private void saveEvent(Map<String, Object> event, Match newMatch){
        findEventType((String)event.get("type"), event, newMatch);
    }

    private void findEventType(String eventType, Map<String, Object> event, Match newMatch){
        switch (eventType){
            case "ITEM_PURCHASE":
                saveItemEvent("ITEM_PURCHASE", event, newMatch);
                break;

            case "ITEM_DESTROYED":
                saveItemEvent("ITEM_DESTROYED", event, newMatch);
                break;

            case "ITEM_SOLD":
                saveItemEvent("ITEM_SOLD", event, newMatch);
                break;

            case "SKILL_LEVEL_UP":
                saveSkillUp(event, newMatch);
                break;

            case "LEVEL_UP":
                saveLevelUp(event, newMatch);
                break;

            case "BUILDING_KILL":
                saveBuildingKill("BUILDING_KILL", event, newMatch);
                break;

            case "TURRET_PLATE_DESTROYED":
                saveBuildingKill("TURRET_PLATE_DESTROYED", event, newMatch);
                break;

            case "ELITE_MONSTER_KILL":
                saveMonsterKill(event, newMatch);
                break;

            case "CHAMPION_KILL":
                saveChampionKill(event, newMatch);
                break;

            case "CHAMPION_SPECIAL_KILL":
                saveSpecialKill(event, newMatch);
                break;

            default:
                break;
        }

    }

    private Event getNewEvent(Map<String, Object> eventData, Participant participant){
        Event event = new Event();
        event.setTimestamp((int)eventData.get("timestamp"));
        event.setParticipant(participant);

        return eventRepositoryDao.save(event);
    }

    private void saveItemEvent(String itemType, Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("participantId"));
        Event event = getNewEvent(eventData, participant);

        Item item = new Item();
        item.setItemType(itemType);
        item.setItemNum((int)eventData.get("itemId"));
        item.setEvent(event);

        items.add(item);
    }

    private void saveSkillUp(Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("participantId"));
        Event event = getNewEvent(eventData, participant);

        SkillUp skillUp = new SkillUp();
        skillUp.setLevelUpType((String) eventData.get("levelUpType"));
        skillUp.setSkillSlot((int) eventData.get("skillSlot"));
        skillUp.setEvent(event);

        skillUps.add(skillUp);
    }

    private void saveLevelUp(Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("participantId"));
        Event event = getNewEvent(eventData, participant);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel((int) eventData.get("level"));
        levelUp.setEvent(event);

        levelUps.add(levelUp);
    }

    private void saveBuildingKill(String buildingType, Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("killerId"));
        Event event = getNewEvent(eventData, participant);

        BuildingKill buildingKill = new BuildingKill();
        buildingKill.setBounty((int)eventData.getOrDefault("bounty", 0));
        buildingKill.setBuildingType((String)eventData.getOrDefault("buildingType", buildingType));
        buildingKill.setLaneType((String) eventData.get("laneType"));

        Map<String, Object> position = (Map<String, Object>) eventData.get("position");
        buildingKill.setPositionX((int)position.get("x"));
        buildingKill.setPositionY((int)position.get("y"));

        buildingKill.setEvent(event);

        buildingKills.add(buildingKill);
    }

    private void saveMonsterKill(Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("killerId"));
        Event event = getNewEvent(eventData, participant);

        MonsterKill monsterKill = new MonsterKill();

        monsterKill.setBounty((int)eventData.get("bounty"));
        monsterKill.setMonsterType((String) eventData.get("monsterType"));
        monsterKill.setMonsterSubtype((String) eventData.get("monsterSubType"));

        Map<String, Object> position = (Map<String, Object>) eventData.get("position");
        monsterKill.setPositionX((int)position.get("x"));
        monsterKill.setPositionY((int)position.get("y"));

        monsterKill.setEvent(event);

        monsterKills.add(monsterKill);
    }

    private void saveChampionKill(Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("killerId"));
        Participant victim = participants.get((int)eventData.get("victimId"));
        Event event = getNewEvent(eventData, participant);

        ChampKill champKill = new ChampKill();

        champKill.setBounty((int)eventData.get("bounty"));
        champKill.setKillStreakLength((int)eventData.get("killStreakLength"));
        champKill.setShutdownBounty((int)eventData.get("shutdownBounty"));

        Map<String, Object> position = (Map<String, Object>) eventData.get("position");
        champKill.setPositionX((int)position.get("x"));
        champKill.setPositionY((int)position.get("y"));

        champKill.setVictim(victim);
        champKill.setEvent(event);

        ChampKill champKillData = champKillRepositoryDao.save(champKill);

        if((((List<Map<String, Object>>)eventData.get("victimDamageDealt")) != null )){
            saveDamageDealt(champKillData, (List<Map<String, Object>>)eventData.get("victimDamageDealt"));
        }

        if(((List<Map<String, Object>>)eventData.get("victimDamageReceived") != null )){
            saveDamageReceived(champKillData, (List<Map<String, Object>>)eventData.get("victimDamageReceived"), newMatch);
        }

    }

    private void saveDamageDealt(ChampKill champKillData, List<Map<String, Object>> attacks) {
        for (Map<String,Object> attack : attacks) {
            DamageDealt damageDealt = new DamageDealt();
            damageDealt.setBasic((boolean) attack.get("basic"));
            damageDealt.setType((String) attack.get("type"));
            damageDealt.setSpellName((String) attack.get("spellName"));
            damageDealt.setSpellSlot((int) attack.get("spellSlot"));
            damageDealt.setMagicDamage((int) attack.get("magicDamage"));
            damageDealt.setPhysicalDamage((int) attack.get("physicalDamage"));
            damageDealt.setTrueDamage((int) attack.get("trueDamage"));
            damageDealt.setEventChampKill(champKillData);

            damageDealtList.add(damageDealt);
        }
    }

    private void saveDamageReceived(ChampKill champKillData, List<Map<String, Object>> attacks, Match newMatch) {
        for (Map<String,Object> attack : attacks) {
            DamageReceived damageReceived = new DamageReceived();

            Participant participant = participants.get((int)attack.get("participantId"));

            //Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)attack.get("participantId")));

            damageReceived.setBasic((boolean) attack.get("basic"));
            damageReceived.setType((String) attack.get("type"));
            damageReceived.setSpellName((String) attack.get("spellName"));
            damageReceived.setSpellSlot((int) attack.get("spellSlot"));
            damageReceived.setMagicDamage((int) attack.get("magicDamage"));
            damageReceived.setPhysicalDamage((int) attack.get("physicalDamage"));
            damageReceived.setTrueDamage((int) attack.get("trueDamage"));
            damageReceived.setParticipant(participant);
            damageReceived.setEventChampKill(champKillData);

            damageReceivedList.add(damageReceived);
        }
    }

    private void saveSpecialKill(Map<String, Object> eventData, Match newMatch){
        Participant participant = participants.get((int)eventData.get("killerId"));
        //Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("killerId")));
        Event event = getNewEvent(eventData, participant);

        SpecialKill specialKill = new SpecialKill();

        specialKill.setKillType((String)eventData.get("killType"));

        if(((eventData.get("multiKillLength")) != null )){
            specialKill.setMultiKillLength((int)eventData.get("multiKillLength"));
        }

        Map<String, Object> position = (Map<String, Object>) eventData.get("position");
        specialKill.setPositionX((int)position.get("x"));
        specialKill.setPositionY((int)position.get("y"));

        specialKill.setEvent(event);

        specialKills.add(specialKill);
    }

    private void insertBatchData(){
        //eventRepositoryDao.saveAll(events);
        itemRepositoryDao.saveAll(items);
        //participantFrameRepositoryDao.saveAll(participantFrames);
        participantFrameChampRepositoryDao.saveAll(participantFrameChamps);
        participantFrameDamageRepositoryDao.saveAll(participantFrameDamages);
        skillUpRepositoryDao.saveAll(skillUps);
        levelUpRepositoryDao.saveAll(levelUps);
        buildingKillRepositoryDao.saveAll(buildingKills);
        monsterKillRepositoryDao.saveAll(monsterKills);
        //champKillRepositoryDao.saveAll(champKills);
        damageDealtRepositoryDao.saveAll(damageDealtList);
        damageReceivedRepositoryDao.saveAll(damageReceivedList);
        specialKillRepositoryDao.saveAll(specialKills);
    }

    private void getEventInfo(Map<String, Object> event){
        List<String> eventTypes = new ArrayList<>();

        //finds unique event types
//                if(!eventTypes.contains(event.get("type"))){
//                    eventTypes.add((String) event.get("type"));
//                } else {
//                    System.out.println("(String) frame.get(\"type\") = " + (String) event.get("type"));
//                }

        //finds unique kill types
//                if((event.get("type")).equals("CHAMPION_SPECIAL_KILL")){
//                    eventTypes.add((String) event.get("killType"));
//                } else {
//                }

        //finds unique kill types
//                if((event.get("type")).equals("SKILL_LEVEL_UP")){
//                    eventTypes.add((String) event.get("levelUpType"));
//                } else {
//                }

        //finds timestamps for .equals(XXX)
        if((event.get("type")).equals("ELITE_MONSTER_KILL")){
            eventTypes.add(Integer.toString(((int) event.get("timestamp"))));
        } else {
        }

        System.out.println("eventTypes = " + eventTypes);
    }
}
