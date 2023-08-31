package com.gagejackson.lof.Controllers.SaveData;

import com.gagejackson.lof.Models.MatchEvent.*;
import com.gagejackson.lof.Models.MatchOverview.*;
//import jdk.nashorn.internal.runtime.ListAdapter;
import com.gagejackson.lof.Repositories.MatchEvent.*;
import com.gagejackson.lof.Repositories.MatchOverview.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SaveMatchController {
    private final MatchRepository matchRepositoryDao;
    private final BanRepository banRepositoryDao;
    private final EventRepository eventRepositoryDao;
    private final BuildingKillRepository buildingKillRepositoryDao;
    private final ChampKillRepository champKillRepositoryDao;
    private final DamageDealtRepository damageDealtRepositoryDao;
    private final DamageReceivedRepository damageReceivedRepositoryDao;
    private final SpecialKillRepository specialKillRepositoryDao;
    private final MonsterKillRepository monsterKillRepositoryDao;
    private final EventItemRepository eventItemRepositoryDao;
    private final LevelUpRepository levelUpRepositoryDao;
    private final SkillUpRepository skillUpRepositoryDao;
    private final ObjectiveRepository objectiveRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;
    private final ParticipantFrameRepository participantFrameRepositoryDao;
    private final ParticipantFrameChampRepository participantFrameChampRepositoryDao;
    private final ParticipantFrameDamageRepository participantFrameDamageRepositoryDao;
    private final PerkRepository perkRepositoryDao;
    private final TeamRepository teamRepositoryDao;

    /*
    Batch Save Arrays
     */
//    private List<Event> events = new ArrayList<>();
//    private List<EventItem> eventItems = new ArrayList<>();
//    private List<ParticipantFrame> participantFrames = new ArrayList<>();
//    private List<ParticipantFrameChamp> participantFrameChamps = new ArrayList<>();
//    private List<ParticipantFrameDamage> participantFrameDamages = new ArrayList<>();
//    private List<SkillUp> skillUps = new ArrayList<>();
//    private List<LevelUp> levelUps = new ArrayList<>();
//    private List<BuildingKill> buildingKills = new ArrayList<>();
//    private List<MonsterKill> monsterKills = new ArrayList<>();
//    private List<ChampKill> champKills = new ArrayList<>();
//    private List<DamageDealt> damageDealtList = new ArrayList<>();
//    private List<DamageReceived> damageReceivedList = new ArrayList<>();
//    private List<SpecialKill> specialKills = new ArrayList<>();
//    private List<Participant> participants = new ArrayList<>();
//    private List<Perk> perks = new ArrayList<>();
//    private List<Team> teams = new ArrayList<>();
//    private List<Ban> bans = new ArrayList<>();
//    private List<Objective> objectives = new ArrayList<>();

    /*
       Other Local Variables
    */
    private Map<Integer, Participant> participantsData = new HashMap<>();



    public SaveMatchController(
            MatchRepository matchRepositoryDao,
            BanRepository banRepositoryDao,
            EventRepository eventRepositoryDao,
            BuildingKillRepository buildingKillRepositoryDao,
            ChampKillRepository champKillRepositoryDao,
            DamageDealtRepository damageDealtRepositoryDao,
            DamageReceivedRepository damageReceivedRepositoryDao,
            SpecialKillRepository specialKillRepositoryDao,
            MonsterKillRepository monsterKillRepositoryDao,
            EventItemRepository eventItemRepositoryDao,
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
        this.matchRepositoryDao = matchRepositoryDao;
        this.banRepositoryDao = banRepositoryDao;
        this.eventRepositoryDao = eventRepositoryDao;
        this.buildingKillRepositoryDao = buildingKillRepositoryDao;
        this.champKillRepositoryDao = champKillRepositoryDao;
        this.damageDealtRepositoryDao = damageDealtRepositoryDao;
        this.damageReceivedRepositoryDao = damageReceivedRepositoryDao;
        this.specialKillRepositoryDao = specialKillRepositoryDao;
        this.monsterKillRepositoryDao = monsterKillRepositoryDao;
        this.eventItemRepositoryDao = eventItemRepositoryDao;
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
        
        if (newMatch != null){
            Match match1 = saveMatchOverview(overviewData, newMatch);
            Match match2 = saveMatchTimeline(timelineFrames, match1);
            matchRepositoryDao.save(match2);
        }
    }

    private Match saveMatchData(Map<String, Object> matchData){
        long gameId = (long) matchData.get("gameId");
        Match match = matchRepositoryDao.findByGameId(gameId);

        if(!match.isSaved()){
            match.setSaved(true);
            match.setGameCreation((long) matchData.get("gameCreation"));
            match.setGameDuration((long) (int) matchData.get("gameDuration"));
            match.setGameEnd((long) matchData.get("gameEndTimestamp"));
            match.setGameId(gameId);
            match.setGameMode((String) matchData.get("gameMode"));
            match.setGameName((String) matchData.get("gameName"));
            match.setGameStart((long) matchData.get("gameStartTimestamp"));
            match.setGameType((String) matchData.get("gameType"));
            match.setGameVersion((String) matchData.get("gameVersion"));
            match.setMapId((int) matchData.get("mapId"));
            match.setPlatformId((String) matchData.get("platformId"));
            match.setQueueId((int) matchData.get("queueId"));
            match.setTournamentCode((String) matchData.get("tournamentCode"));

//            return matchRepositoryDao.save(match);
            return match;
        } else {
            return null;
        }
    }
    /*//////////////////////////////////////////////////////////////////////////////////
        Section - Match Overview
    //////////////////////////////////////////////////////////////////////////////////*/
    protected Match saveMatchOverview(Map<String, Object> overviewData, Match newMatch){
        newMatch.setParticipant(saveParticipantData(overviewData, newMatch));
        newMatch.setTeam(saveTeamData(overviewData, newMatch));
        return newMatch;
//        insertBatchOverviewData(newMatch);
    }
    private List<Participant> saveParticipantData(Map<String, Object> matchData, Match newMatch) {
        List<Map<String, Object>> participantsData = (List<Map<String, Object>>) matchData.get("participants");
        List<Participant> participants = new ArrayList<>();

        for (Map<String, Object> participantData : participantsData) {
            Participant participant = new Participant();

            participant.setAssists((int) participantData.get("assists"));
            participant.setBaronKills((int) participantData.get("baronKills"));
            participant.setBountyLevel((int) participantData.get("bountyLevel"));
            participant.setChampExperience((int) participantData.get("champExperience"));
            participant.setChampLevel((int) participantData.get("champLevel"));
            participant.setChampionId((int) participantData.get("championId"));
            participant.setChampionName((String) participantData.get("championName"));
            participant.setChampionTransform((int) participantData.get("championTransform"));
            participant.setConsumablesPurchased((int) participantData.get("consumablesPurchased"));
            participant.setDamageDealtToBuildings((int) participantData.get("damageDealtToBuildings"));
            participant.setDamageDealtToObjectives((int) participantData.get("damageDealtToObjectives"));
            participant.setDamageDealtToTurrets((int) participantData.get("damageDealtToTurrets"));
            participant.setDamageSelfMitigated((int) participantData.get("damageSelfMitigated"));
            participant.setDeaths((int) participantData.get("deaths"));
            participant.setDetectorWardsPlaced((int) participantData.get("detectorWardsPlaced"));
            participant.setDoubleKills((int) participantData.get("doubleKills"));
            participant.setDragonKills((int) participantData.get("dragonKills"));
            participant.setFirstBloodAssist((boolean) participantData.get("firstBloodAssist"));
            participant.setFirstBloodKill((boolean) participantData.get("firstBloodKill"));
            participant.setFirstTowerAssist((boolean) participantData.get("firstTowerAssist"));
            participant.setFirstTowerKill((boolean) participantData.get("firstTowerKill"));
            participant.setGameEndedInEarlySurrender((boolean) participantData.get("gameEndedInEarlySurrender"));
            participant.setGameEndedInSurrender((boolean) participantData.get("gameEndedInSurrender"));
            participant.setGoldEarned((int) participantData.get("goldEarned"));
            participant.setGoldSpent((int) participantData.get("goldSpent"));
            participant.setIndividualPosition((String) participantData.get("individualPosition"));
            participant.setInhibitorKills((int) participantData.get("inhibitorKills"));
            participant.setInhibitorTakedowns((int) participantData.get("inhibitorTakedowns"));
            participant.setInhibitorsLost((int) participantData.get("inhibitorsLost"));
            participant.setItem0((int) participantData.get("item0"));
            participant.setItem1((int) participantData.get("item1"));
            participant.setItem2((int) participantData.get("item2"));
            participant.setItem3((int) participantData.get("item3"));
            participant.setItem4((int) participantData.get("item4"));
            participant.setItem5((int) participantData.get("item5"));
            participant.setItem6((int) participantData.get("item6"));
            participant.setItemsPurchased((int) participantData.get("itemsPurchased"));
            participant.setKillingSprees((int) participantData.get("killingSprees"));
            participant.setKills((int) participantData.get("kills"));
            participant.setLane((String) participantData.get("lane"));
            participant.setLargestCriticalStrike((int) participantData.get("largestCriticalStrike"));
            participant.setLargestKillingSpree((int) participantData.get("largestKillingSpree"));
            participant.setLargestMultiKill((int) participantData.get("largestMultiKill"));
            participant.setLongestTimeSpentLiving((int) participantData.get("longestTimeSpentLiving"));
            participant.setMagicDamageDealt((int) participantData.get("magicDamageDealt"));
            participant.setMagicDamageDealtToChampions((int) participantData.get("magicDamageDealtToChampions"));
            participant.setMagicDamageTaken((int) participantData.get("magicDamageTaken"));
            participant.setNeutralMinionsKilled((int) participantData.get("neutralMinionsKilled"));
            participant.setNexusKills((int) participantData.get("nexusKills"));
            participant.setNexusTakedowns((int) participantData.get("nexusTakedowns"));
            participant.setNexusLost((int) participantData.get("nexusLost"));
            participant.setObjectivesStolen((int) participantData.get("objectivesStolen"));
            participant.setObjectivesStolenAssists((int) participantData.get("objectivesStolenAssists"));
            participant.setParticipantNum((int) participantData.get("participantId"));
            participant.setPentaKills((int) participantData.get("pentaKills"));
            participant.setPhysicalDamageDealt((int) participantData.get("physicalDamageDealt"));
            participant.setPhysicalDamageDealtToChampions((int) participantData.get("physicalDamageDealtToChampions"));
            participant.setPhysicalDamageTaken((int) participantData.get("physicalDamageTaken"));
            participant.setProfileIcon((int) participantData.get("profileIcon"));
            participant.setPuuid((String) participantData.get("puuid"));
            participant.setQuadraKills((int) participantData.get("quadraKills"));
            participant.setRiotIdName((String) participantData.get("riotIdName"));
            participant.setRiotIdTagline((String) participantData.get("riotIdTagline"));
            participant.setRole((String) participantData.get("role"));
            participant.setSightWardsBoughtInGame((int) participantData.get("sightWardsBoughtInGame"));
            participant.setSpell1Casts((int) participantData.get("spell1Casts"));
            participant.setSpell2Casts((int) participantData.get("spell2Casts"));
            participant.setSpell3Casts((int) participantData.get("spell3Casts"));
            participant.setSpell4Casts((int) participantData.get("spell4Casts"));
            participant.setSummoner1Casts((int) participantData.get("summoner1Casts"));
            participant.setSummoner1Id((int) participantData.get("summoner1Id"));
            participant.setSummoner2Casts((int) participantData.get("summoner2Casts"));
            participant.setSummoner2Id((int) participantData.get("summoner2Id"));
            participant.setSummonerId((String) participantData.get("summonerId"));
            participant.setSummonerLevel((int) participantData.get("summonerLevel"));
            participant.setSummonerName((String) participantData.get("summonerName"));
            participant.setTeamEarlySurrendered((boolean) participantData.get("teamEarlySurrendered"));
            participant.setTeamId((int) participantData.get("teamId"));
            participant.setTeamPosition((String) participantData.get("teamPosition"));
            participant.setTimeCCingOthers((int) participantData.get("timeCCingOthers"));
            participant.setTimePlayed((int) participantData.get("timePlayed"));
            participant.setTotalDamageDealt((int) participantData.get("totalDamageDealt"));
            participant.setTotalDamageDealtToChampions((int) participantData.get("totalDamageDealtToChampions"));
            participant.setTotalDamageShieldedOnTeammates((int) participantData.get("totalDamageShieldedOnTeammates"));
            participant.setTotalDamageTaken((int) participantData.get("totalDamageTaken"));
            participant.setTotalHeal((int) participantData.get("totalHeal"));
            participant.setTotalHealsOnTeammates((int) participantData.get("totalHealsOnTeammates"));
            participant.setTotalMinionsKilled((int) participantData.get("totalMinionsKilled"));
            participant.setTotalTimeCCDealt((int) participantData.get("totalTimeCCDealt"));
            participant.setTotalTimeSpentDead((int) participantData.get("totalTimeSpentDead"));
            participant.setTotalUnitsHealed((int) participantData.get("totalUnitsHealed"));
            participant.setTripleKills((int) participantData.get("tripleKills"));
            participant.setTrueDamageDealt((int) participantData.get("trueDamageDealt"));
            participant.setTrueDamageDealtToChampions((int) participantData.get("trueDamageDealtToChampions"));
            participant.setTrueDamageTaken((int) participantData.get("trueDamageTaken"));
            participant.setTurretKills((int) participantData.get("turretKills"));
            participant.setTurretTakedowns((int) participantData.get("turretTakedowns"));
            participant.setTurretsLost((int) participantData.get("turretsLost"));
            participant.setUnrealKills((int) participantData.get("unrealKills"));
            participant.setVisionScore((int) participantData.get("visionScore"));
            participant.setVisionWardsBoughtInGame((int) participantData.get("visionWardsBoughtInGame"));
            participant.setWardsKilled((int) participantData.get("wardsKilled"));
            participant.setWardsPlaced((int) participantData.get("wardsPlaced"));
            participant.setWin((boolean) participantData.get("win"));
            participant.setMatch(newMatch);

            participant.setPerk(savePerkData(participantData, participant));

            participants.add(participant);
        }
        return participants;
    }

    private List<Perk> savePerkData(Map<String, Object> info, Participant newParticipant) {
        Map<String, Object> perksData = (Map<String, Object>) info.get("perks");
        List<Map<String, Object>> stylesData = (List<Map<String, Object>>) perksData.get("styles");
        List<Perk> perks = new ArrayList<>();

        for (Map<String, Object> styleData : stylesData) {
            List<Map<String, Object>> selectionsData = (List<Map<String, Object>>) styleData.get("selections");
            String description = (String) styleData.get("description");

            for (Map<String, Object> selectionData : selectionsData) {
                Perk perk = new Perk();
                perk.setPerkNum((int) selectionData.get("perk"));
                perk.setStyle((int) styleData.get("style"));
                perk.setPerkStat1((int) selectionData.get("var1"));
                perk.setPerkStat2((int) selectionData.get("var2"));
                perk.setPerkStat3((int) selectionData.get("var3"));
                perk.setParticipant(newParticipant);

                if (description.equals("primaryStyle")){
                    perk.setPrimary(true);
                } else {
                    perk.setPrimary(false);
                }

                perks.add(perk);
            }
        }

        return perks;
    }

    private List<Team> saveTeamData(Map<String, Object> info, Match newMatch) {
        List<Map<String, Object>> teamsData = (List<Map<String, Object>>) info.get("teams");
        List<Team> teams = new ArrayList<>();
        for (Map<String, Object> teamData : teamsData) {
            Team team = new Team();

            int teamId = (int) teamData.get("teamId");
            if ((teamData.get("win")).equals("true") || (teamData.get("win")).equals(true)) {
                team.setWin(true);
            } else {
                team.setWin(false);
            }

            int teamKills = 0;
            int teamDeaths = 0;
            int teamAssists = 0;
            int teamGold = 0;
            int teamTotalDamage = 0;
            int teamMagicDamage = 0;
            int teamPhysicalDamage = 0;
            int teamTrueDamage = 0;

            for (Participant participant : newMatch.getParticipant()) {
                int participantTeamId = participant.getTeamId();

                if (participantTeamId == teamId && participant.getMatch() == newMatch) {
                    teamKills += participant.getKills();
                    teamDeaths += participant.getDeaths();
                    teamAssists += participant.getAssists();
                    teamGold += participant.getGoldEarned();
                    teamTotalDamage += participant.getTotalDamageDealtToChampions();
                    teamMagicDamage += participant.getMagicDamageDealtToChampions();
                    teamPhysicalDamage += participant.getPhysicalDamageDealtToChampions();
                    teamTrueDamage += participant.getTrueDamageDealtToChampions();
                }
            }

            team.setKills(teamKills);
            team.setDeaths(teamDeaths);
            team.setAssists(teamAssists);
            team.setGold(teamGold);
            team.setTotalDamage(teamTotalDamage);
            team.setMagicDamage(teamMagicDamage);
            team.setPhysicalDamage(teamPhysicalDamage);
            team.setTrueDamage(teamTrueDamage);
            team.setTeamNum(teamId);
            team.setMatch(newMatch);

            team.setBan(saveBanData(teamData, team));;
            team.setObjective(saveObjectiveData(teamData, team));;

            teams.add(team);
        }
        return teams;
    }

    private List<Ban> saveBanData(Map<String, Object> info, Team newTeam) {
        List<Map<String, Object>> bansData = (List<Map<String, Object>>) info.get("bans");
        List<Ban> bans = new ArrayList<>();
        if(bansData.isEmpty()){
            return null;
        }

        for (Map<String, Object> banData : bansData) {
            Ban ban = new Ban();

            ban.setChampId((int) banData.get("championId"));
            ban.setPickTurn((int) banData.get("pickTurn"));
            ban.setTeam(newTeam);

           bans.add(ban);
        }
        return bans;
    }

    private List<Objective> saveObjectiveData(Map<String, Object> info, Team newTeam) {
        Map<String, Object> objectivesData = (Map<String, Object>) info.get("objectives");
        List<Objective> objectives = new ArrayList<>();

        if(objectivesData.size() == 0){
            System.out.println("empty");
            return null;
        }

        objectives.add(saveObjective("baron", objectivesData, newTeam));
        objectives.add(saveObjective("champion", objectivesData, newTeam));
        objectives.add(saveObjective("dragon", objectivesData, newTeam));
        objectives.add(saveObjective("inhibitor", objectivesData, newTeam));
        objectives.add(saveObjective("riftHerald", objectivesData, newTeam));
        objectives.add(saveObjective("tower", objectivesData, newTeam));

        return objectives;
    }

    private Objective saveObjective (String objectiveName, Map<String, Object> objectives, Team newTeam){
        Map<String, Object> objectiveData = (Map<String, Object>) objectives.get(objectiveName);
        Objective objective = new Objective();

        objective.setName(objectiveName);
        objective.setFirst((boolean) objectiveData.get("first"));
        objective.setKills((int) objectiveData.get("kills"));
        objective.setTeam(newTeam);

        return objective;
    }


    /*//////////////////////////////////////////////////////////////////////////////////
    Section - Match TimeLine
     //////////////////////////////////////////////////////////////////////////////////*/
    protected Match saveMatchTimeline(List<Map<String, Object>> framesData, Match newMatch){
        int currentFrame = 0;
        participantsData = getParticipantsByMatch(newMatch);
        List<List<Event>> eventCollection = new ArrayList<>();
        List<List<ParticipantFrame>> participantFrameCollection = new ArrayList<>();

        List<Event> events = new ArrayList<>();
        List<ParticipantFrame> participantFrames = new ArrayList<>();

        for (Map<String, Object> frameData : framesData) {
            eventCollection.add(saveEvents(frameData));
            participantFrameCollection.add(saveParticipantFrames(frameData, currentFrame));

            currentFrame++;
        }

//        insertBatchTimelineData();
        return newMatch;
    }

    private Map<Integer, Participant> getParticipantsByMatch(Match newMatch) {
        List<Participant> participants = newMatch.getParticipant();
        Map<Integer, Participant> participantMap = new HashMap<>();
        for (Participant participant : participants) {
            participantMap.put(participant.getParticipantNum(), participant);
        }
        return participantMap;
    }

    private List<ParticipantFrame> saveParticipantFrames(Map<String, Object> frameData, int currentFrame){
        Map<String, Object> participantFramesData = (Map<String, Object>) frameData.get("participantFrames");
        List<ParticipantFrame> participantFrames = new ArrayList<>();

        for (int i = 1; i <= participantFramesData.size(); i++) {
            Participant participant = participantsData.get(i);
            participantFrames.add(saveParticipantFrame(participantFramesData, currentFrame, i, participant));
        }
        return participantFrames;
    }

    private List<Event> saveEvents(Map<String, Object> frameData){
        List<Map<String, Object>> eventsData = (List<Map<String, Object>>) frameData.get("events");
        List<Event> events = new ArrayList<>();

        for (Map<String, Object> eventData : eventsData) {
            events.add(saveEvent(eventData));
        }
        return events;
    }

    private Event createEvent(Map<String, Object> eventData, Participant participant){
        Event event = new Event();
        event.setTimestamp((int)eventData.get("timestamp"));
        event.setParticipant(participant);
//        events.add(event);
        return event;
    }

    private ParticipantFrame saveParticipantFrame(Map<String, Object> participantFramesData, int currentFrame, int i, Participant participant){
        Map<String, Object> participantFrameData = (Map<String, Object>) participantFramesData.get(String.valueOf(i));
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

//        participantFrames.add(participantFrame);

        participantFrame.setParticipantFrameChamp(saveChampData(participantFrameData, participantFrame));
        participantFrame.setParticipantFrameDamage(saveDamageData(participantFrameData, participantFrame));

        return participantFrame;
    }

    private ParticipantFrameChamp saveChampData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameChamp participantFrameChamp = new ParticipantFrameChamp();
        Map<String, Object> champData = (Map<String, Object>) participantFrameData.get("championStats");

        participantFrameChamp.setAbilityHaste((int) champData.get("abilityHaste"));
        participantFrameChamp.setAbilityPower((int) champData.get("abilityPower"));
        participantFrameChamp.setArmor((int) champData.get("armor"));
        participantFrameChamp.setArmorPen((int) champData.get("armorPen"));
        participantFrameChamp.setArmorPenPercent((int) champData.get("armorPenPercent"));
        participantFrameChamp.setAttackDamage((int) champData.get("attackDamage"));
        participantFrameChamp.setAttackSpeed((int) champData.get("attackSpeed"));
        participantFrameChamp.setBonusArmorPenPercent((int) champData.get("bonusArmorPenPercent"));
        participantFrameChamp.setBonusMagicPenPercent((int) champData.get("bonusMagicPenPercent"));
        participantFrameChamp.setCcReduction((int) champData.get("ccReduction"));
        participantFrameChamp.setCooldownReduction((int) champData.get("cooldownReduction"));
        participantFrameChamp.setHealth((int) champData.get("health"));
        participantFrameChamp.setHealthMax((int) champData.get("healthMax"));
        participantFrameChamp.setHealthRegen((int) champData.get("healthRegen"));
        participantFrameChamp.setLifesteal((int) champData.get("lifesteal"));
        participantFrameChamp.setMagicPen((int) champData.get("magicPen"));
        participantFrameChamp.setMagicPenPercent((int) champData.get("magicPenPercent"));
        participantFrameChamp.setMagicResist((int) champData.get("magicResist"));
        participantFrameChamp.setMovementSpeed((int) champData.get("movementSpeed"));
        participantFrameChamp.setOmnivamp((int) champData.get("omnivamp"));
        participantFrameChamp.setPhysicalVamp((int) champData.get("physicalVamp"));
        participantFrameChamp.setPower((int) champData.get("power"));
        participantFrameChamp.setPowerMax((int) champData.get("powerMax"));
        participantFrameChamp.setPowerRegen((int) champData.get("powerRegen"));
        participantFrameChamp.setSpellVamp((int) champData.get("spellVamp"));
        participantFrameChamp.setParticipantFrame(newParticipantFrame);

//        participantFrameChamps.add(participantFrameChamp);

        return participantFrameChamp;
    }

    private ParticipantFrameDamage saveDamageData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameDamage participantFrameDamage = new ParticipantFrameDamage();
        Map<String, Object> damageData = (Map<String, Object>) participantFrameData.get("damageStats");

        participantFrameDamage.setMagicDamageDone((int) damageData.get("magicDamageDone"));
        participantFrameDamage.setMagicDamageDone((int) damageData.get("magicDamageDone"));
        participantFrameDamage.setMagicDamageDoneToChamps((int) damageData.get("magicDamageDoneToChampions"));
        participantFrameDamage.setMagicDamageTaken((int) damageData.get("magicDamageTaken"));
        participantFrameDamage.setPhysicalDamageDone((int) damageData.get("physicalDamageDone"));
        participantFrameDamage.setPhysicalDamageDoneToChamps((int) damageData.get("physicalDamageDoneToChampions"));
        participantFrameDamage.setPhysicalDamageTaken((int) damageData.get("physicalDamageTaken"));
        participantFrameDamage.setTotalDamageDone((int) damageData.get("totalDamageDone"));
        participantFrameDamage.setTotalDamageDoneToChamps((int) damageData.get("totalDamageDoneToChampions"));
        participantFrameDamage.setTotalDamageTaken((int) damageData.get("totalDamageTaken"));
        participantFrameDamage.setTrueDamageDone((int) damageData.get("trueDamageDone"));
        participantFrameDamage.setTrueDamageDoneToChamps((int) damageData.get("trueDamageDoneToChampions"));
        participantFrameDamage.setTrueDamageTaken((int) damageData.get("trueDamageTaken"));
        participantFrameDamage.setParticipantFrame(newParticipantFrame);

//        participantFrameDamages.add(participantFrameDamage);
        return participantFrameDamage;
    }

    private Event saveEvent(Map<String, Object> eventData){
        String eventType = (String) eventData.get("type");
        switch (eventType) {
            case "ITEM_PURCHASED" -> {
                return saveItemEvent("ITEM_PURCHASED", eventData);
            }
            case "ITEM_DESTROYED" -> {
                return saveItemEvent("ITEM_DESTROYED", eventData);
            }
            case "ITEM_SOLD" -> {
                return saveItemEvent("ITEM_SOLD", eventData);
            }
            case "SKILL_LEVEL_UP" -> {
                return saveSkillUp(eventData);
            }
            case "LEVEL_UP" -> {
                return saveLevelUp(eventData);
            }
            case "BUILDING_KILL" -> {
                return saveBuildingKill("BUILDING_KILL", eventData);
            }
            case "TURRET_PLATE_DESTROYED" -> {
                return saveBuildingKill("TURRET_PLATE_DESTROYED", eventData);
            }
            case "ELITE_MONSTER_KILL" -> {
                return saveMonsterKill(eventData);
            }
            case "CHAMPION_KILL" -> {
                return saveChampionKill(eventData);
            }
            case "CHAMPION_SPECIAL_KILL" -> {
                return saveSpecialKill(eventData);
            }
            default -> {
                return null;
            }
        }
    }

    private Event saveItemEvent(String itemType, Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("participantId"));
        Event event = createEvent(eventData, participant);

        EventItem eventItem = new EventItem();
        eventItem.setItemType(itemType);
        eventItem.setItemNum((int)eventData.get("itemId"));
        eventItem.setEvent(event);

//        eventItems.add(eventItem);
        event.setEventItem(eventItem);
        return event;
    }

    private Event saveSkillUp(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("participantId"));
        Event event = createEvent(eventData, participant);

        SkillUp skillUp = new SkillUp();
        skillUp.setLevelUpType((String) eventData.get("levelUpType"));
        skillUp.setSkillSlot((int) eventData.get("skillSlot"));
        skillUp.setEvent(event);

//        skillUps.add(skillUp);
        event.setSkillUp(skillUp);
        return event;
    }

    private Event saveLevelUp(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("participantId"));
        Event event = createEvent(eventData, participant);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel((int) eventData.get("level"));
        levelUp.setEvent(event);

//        levelUps.add(levelUp);
        event.setLevelUp(levelUp);
        return event;
    }

    private Event saveBuildingKill(String buildingType, Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Event event = createEvent(eventData, participant);

        BuildingKill buildingKill = new BuildingKill();
        buildingKill.setBounty((int)eventData.getOrDefault("bounty", 0));
        buildingKill.setBuildingType((String)eventData.getOrDefault("buildingType", buildingType));
        buildingKill.setLaneType((String) eventData.get("laneType"));

        Map<String, Object> positionData = (Map<String, Object>) eventData.get("position");
        buildingKill.setPositionX((int)positionData.get("x"));
        buildingKill.setPositionY((int)positionData.get("y"));

        buildingKill.setEvent(event);

//        buildingKills.add(buildingKill);
        event.setBuildingKill(buildingKill);
        return event;
    }

    private Event saveMonsterKill(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Event event = createEvent(eventData, participant);

        MonsterKill monsterKill = new MonsterKill();

        monsterKill.setBounty((int)eventData.get("bounty"));
        monsterKill.setMonsterType((String) eventData.get("monsterType"));
        monsterKill.setMonsterSubtype((String) eventData.get("monsterSubType"));

        Map<String, Object> positionData = (Map<String, Object>) eventData.get("position");
        monsterKill.setPositionX((int)positionData.get("x"));
        monsterKill.setPositionY((int)positionData.get("y"));

        monsterKill.setEvent(event);

//        monsterKills.add(monsterKill);
        event.setMonsterKill(monsterKill);
        return event;
    }

    private Event saveChampionKill(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Participant victim = participantsData.get((int)eventData.get("victimId"));
        Event event = createEvent(eventData, participant);

        ChampKill champKill = new ChampKill();

        champKill.setBounty((int)eventData.get("bounty"));
        champKill.setKillStreakLength((int)eventData.get("killStreakLength"));
        champKill.setShutdownBounty((int)eventData.get("shutdownBounty"));

        Map<String, Object> positionData = (Map<String, Object>) eventData.get("position");
        champKill.setPositionX((int)positionData.get("x"));
        champKill.setPositionY((int)positionData.get("y"));

        champKill.setVictim(victim);
        champKill.setEvent(event);

//        champKills.add(champKill);

        if((((List<Map<String, Object>>)eventData.get("victimDamageDealt")) != null )){
            champKill.setEventChampKillVictimDamageDealt(saveDamageDealt(champKill, (List<Map<String, Object>>)eventData.get("victimDamageDealt")));
        }

        if(((List<Map<String, Object>>)eventData.get("victimDamageReceived") != null )){
            champKill.setEventChampKillVictimDamageReceived(saveDamageReceived(champKill, (List<Map<String, Object>>)eventData.get("victimDamageReceived")));
        }
        event.setChampKill(champKill);
        return event;
    }

    private List<DamageDealt> saveDamageDealt(ChampKill champKillData, List<Map<String, Object>> attacksData) {
        List<DamageDealt> damageDealtList = new ArrayList<>();
        for (Map<String,Object> attackData : attacksData) {
            DamageDealt damageDealt = new DamageDealt();

            damageDealt.setBasic((boolean) attackData.get("basic"));
            damageDealt.setType((String) attackData.get("type"));
            damageDealt.setSpellName((String) attackData.get("spellName"));
            damageDealt.setSpellSlot((int) attackData.get("spellSlot"));
            damageDealt.setMagicDamage((int) attackData.get("magicDamage"));
            damageDealt.setPhysicalDamage((int) attackData.get("physicalDamage"));
            damageDealt.setTrueDamage((int) attackData.get("trueDamage"));
            damageDealt.setEventChampKill(champKillData);

//            damageDealtList.add(damageDealt);
            damageDealtList.add(damageDealt);
        }
        return damageDealtList;
    }

    private List<DamageReceived> saveDamageReceived(ChampKill champKillData, List<Map<String, Object>> attacksData) {
        List<DamageReceived> damageReceivedList = new ArrayList<>();
        for (Map<String,Object> attackData : attacksData) {
            DamageReceived damageReceived = new DamageReceived();

            Participant participant = participantsData.get((int)attackData.get("participantId"));

            damageReceived.setBasic((boolean) attackData.get("basic"));
            damageReceived.setType((String) attackData.get("type"));
            damageReceived.setSpellName((String) attackData.get("spellName"));
            damageReceived.setSpellSlot((int) attackData.get("spellSlot"));
            damageReceived.setMagicDamage((int) attackData.get("magicDamage"));
            damageReceived.setPhysicalDamage((int) attackData.get("physicalDamage"));
            damageReceived.setTrueDamage((int) attackData.get("trueDamage"));
            damageReceived.setParticipant(participant);
            damageReceived.setEventChampKill(champKillData);

//            damageReceivedList.add(damageReceived);
            damageReceivedList.add(damageReceived);
        }
        return damageReceivedList;
    }

    private Event saveSpecialKill(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Event event = createEvent(eventData, participant);

        SpecialKill specialKill = new SpecialKill();

        specialKill.setKillType((String)eventData.get("killType"));
        if(((eventData.get("multiKillLength")) != null )){
            specialKill.setMultiKillLength((int)eventData.get("multiKillLength"));
        }

        Map<String, Object> positionData = (Map<String, Object>) eventData.get("position");
        specialKill.setPositionX((int)positionData.get("x"));
        specialKill.setPositionY((int)positionData.get("y"));

        specialKill.setEvent(event);

//        specialKills.add(specialKill);
        event.setSpecialKill(specialKill);
        return event;
    }

    /*//////////////////////////////////////////////////////////////////////////////////
    Section - Batch Inserts
    //////////////////////////////////////////////////////////////////////////////////*/

//    private void insertBatchOverviewData(Match newMatch){
//        participantRepositoryDao.saveAll(participants);
//
//        perkRepositoryDao.saveAll(perks);
//        teamRepositoryDao.saveAll(teams);
//        banRepositoryDao.saveAll(bans);
//        objectiveRepositoryDao.saveAll(objectives);
//    }

//    private void insertBatchTimelineData(){
//        eventRepositoryDao.saveAll(events);
//
//        eventItemRepositoryDao.saveAll(eventItems);
//        skillUpRepositoryDao.saveAll(skillUps);
//        levelUpRepositoryDao.saveAll(levelUps);
//        buildingKillRepositoryDao.saveAll(buildingKills);
//        monsterKillRepositoryDao.saveAll(monsterKills);
//        specialKillRepositoryDao.saveAll(specialKills);
//
//        champKillRepositoryDao.saveAll(champKills);
//        damageDealtRepositoryDao.saveAll(damageDealtList);
//        damageReceivedRepositoryDao.saveAll(damageReceivedList);
//
//        participantFrameRepositoryDao.saveAll(participantFrames);
//        participantFrameChampRepositoryDao.saveAll(participantFrameChamps);
//        participantFrameDamageRepositoryDao.saveAll(participantFrameDamages);
//    }

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
//        if((event.get("type")).equals("ELITE_MONSTER_KILL")){
//            eventTypes.add(Integer.toString(((int) event.get("timestamp"))));
//        } else {
//        }
//
//        System.out.println("eventTypes = " + eventTypes);
    }
}
