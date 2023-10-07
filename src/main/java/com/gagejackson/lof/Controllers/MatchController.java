package com.gagejackson.lof.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagejackson.lof.DTOs.*;
import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendMatch;
import com.gagejackson.lof.Models.MatchEvent.ChampKill;
import com.gagejackson.lof.Models.MatchEvent.Event;
import com.gagejackson.lof.Models.MatchEvent.EventItem;
import com.gagejackson.lof.Models.MatchEvent.SkillUp;
import com.gagejackson.lof.Models.MatchOverview.*;
import com.gagejackson.lof.Repositories.Friend.FriendChampsRepository;
import com.gagejackson.lof.Repositories.Friend.FriendMatchRepository;
import com.gagejackson.lof.Repositories.Friend.FriendRankRepository;
import com.gagejackson.lof.Repositories.Friend.FriendRepository;
import com.gagejackson.lof.Repositories.MatchEvent.EventRepository;
import com.gagejackson.lof.Repositories.MatchEvent.MatchRepository;
import com.gagejackson.lof.Repositories.MatchOverview.ParticipantFrameChampRepository;
import com.gagejackson.lof.Repositories.MatchOverview.ParticipantFrameDamageRepository;
import com.gagejackson.lof.Repositories.MatchOverview.ParticipantFrameRepository;
import com.gagejackson.lof.Repositories.MatchOverview.ParticipantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MatchController {
    private final FriendRepository friendRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    public MatchController(
            FriendRepository friendRepositoryDao,
            MatchRepository matchRepositoryDao
    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
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

        String gameMode = match.getGameMode();

        List<ParticipantDTO> participants = getParticipants(match);
        List<PerformanceSet> performanceSets = getPerformanceSets(participants);
        List<EventKill> killEvents = getKillEvents(participants, gameMode);

        model.addAttribute("matchInfo", matchInfo);
        model.addAttribute("friends", friends);
        model.addAttribute("participants", participants);
        model.addAttribute("performanceSets", performanceSets);
        model.addAttribute("killEvents", killEvents);

        return "detailed-match";
    }

    private List<EventKill> getKillEvents(List<ParticipantDTO> participants, String gameMode){
        List<EventKill> killEvents = new ArrayList<>();
        int id = 0;

        for (ParticipantDTO participant : participants) {
            List<Event> events = participant.getParticipant().getEvent();
            for (Event event : events) {
                if (event.getChampKill() != null){
                    ChampKill champKill = event.getChampKill();
                    EventKill eventKill = new EventKill();
                    eventKill.setKiller(event.getParticipant());
                    eventKill.setKilled(champKill.getVictim());
                    eventKill.setTimestamp(event.getTimestamp());

                    if (Objects.equals(gameMode, "CLASSIC")) {
                        eventKill.setPosX((champKill.getPositionX() / 150));
                        eventKill.setPosY((champKill.getPositionY() / 150));
                    } else if (Objects.equals(gameMode, "ARAM")) {
                        eventKill.setPosX((champKill.getPositionX() / 130));
                        eventKill.setPosY((champKill.getPositionY() / 130));
                    }

                    eventKill.setId(id);
                    id++;

                    killEvents.add(eventKill);
                }
            }
        }

        List<EventKill> sortedEvents = killEvents.stream()
                .sorted(Comparator.comparing(EventKill::getTimestamp))
                .collect(Collectors.toList());

        return sortedEvents;
    }

    private List<PerformanceSet> getPerformanceSets(List<ParticipantDTO> participants){
        List<PerformanceSet> performanceSets = new ArrayList<>();

        PerformanceStat gold = new PerformanceStat("Total Gold");
        PerformanceStat cs = new PerformanceStat("Total CS");
        PerformanceStat bounty = new PerformanceStat("Bounty Gold Gained");

        PerformanceStat shields = new PerformanceStat("Team Shielding");
        PerformanceStat heals = new PerformanceStat("Team Healing");
        PerformanceStat cc = new PerformanceStat("Time CC'ing Others");

        PerformanceStat kills = new PerformanceStat("Kills");
        PerformanceStat damageDealt = new PerformanceStat("Damage to Champions");
        PerformanceStat assists = new PerformanceStat("Assists");

        PerformanceStat structureTakedowns = new PerformanceStat("Structure Takedowns");
        PerformanceStat eliteMonsterTakedowns = new PerformanceStat("Elite Monster Takedowns");
        PerformanceStat objectiveDamage = new PerformanceStat("Damage to Objectives");

        PerformanceStat visionScore = new PerformanceStat("Vision Score");
        PerformanceStat wardsPlaced = new PerformanceStat("Wards Placed");
        PerformanceStat wardsKilled = new PerformanceStat("Wards Destroyed");

        PerformanceStat deaths = new PerformanceStat("Deaths");
        PerformanceStat timeSpentDead = new PerformanceStat("Time Spent Dead");
        PerformanceStat damageTaken = new PerformanceStat("Damage Taken");

        PerformanceStat damageSelfMitigated = new PerformanceStat("Damage Mitigated");
        PerformanceStat totalHeal = new PerformanceStat("Total Self Heals");
        PerformanceStat skillShotsDodged = new PerformanceStat("Skill Shots Dodged");


        for ( ParticipantDTO participantInfo : participants) {
            Participant participant = participantInfo.getParticipant();
            ParticipantChallenges challenges = participant.getParticipantChallenges();

            int participantTeam = participant.getTeamId();
            gold = setPerformanceStat(gold, participant.getGoldEarned(), participantTeam);
            cs = setPerformanceStat(cs, (participant.getNeutralMinionsKilled() + participant.getTotalMinionsKilled()), participantTeam);
            bounty = setPerformanceStat(bounty, challenges.getBountyGold(), participantTeam);

            shields = setPerformanceStat(shields, participant.getTotalDamageShieldedOnTeammates(), participantTeam);
            heals = setPerformanceStat(heals, participant.getTotalHealsOnTeammates(), participantTeam);
            cc = setPerformanceStat(cc, participant.getTimeCCingOthers(), participantTeam);

            kills = setPerformanceStat(kills, participant.getKills(), participantTeam);
            damageDealt = setPerformanceStat(damageDealt, participant.getTotalDamageDealtToChampions(), participantTeam);
            assists = setPerformanceStat(assists, participant.getAssists(), participantTeam);

            int buildingTakedowns = participant.getInhibitorTakedowns() + participant.getInhibitorTakedowns() + participant.getNexusTakedowns();
            int monsterTakedowns = challenges.getBaronTakedowns() + challenges.getRiftHeraldTakedowns() + challenges.getDragonTakedowns();

            structureTakedowns = setPerformanceStat(structureTakedowns, buildingTakedowns, participantTeam);
            eliteMonsterTakedowns = setPerformanceStat(eliteMonsterTakedowns, monsterTakedowns, participantTeam);
            objectiveDamage = setPerformanceStat(objectiveDamage, participant.getDamageDealtToObjectives(), participantTeam);

            visionScore = setPerformanceStat(visionScore, participant.getVisionScore(), participantTeam);
            wardsPlaced = setPerformanceStat(wardsPlaced, participant.getWardsPlaced(), participantTeam);
            wardsKilled = setPerformanceStat(wardsKilled, participant.getWardsKilled(), participantTeam);

            deaths = setPerformanceStat(deaths, participant.getDeaths(), participantTeam);
            timeSpentDead = setPerformanceStat(timeSpentDead, participant.getTotalTimeSpentDead(), participantTeam);
            damageTaken = setPerformanceStat(damageTaken, participant.getTotalDamageTaken(), participantTeam);

            damageSelfMitigated = setPerformanceStat(damageSelfMitigated, participant.getDamageSelfMitigated(), participantTeam);
            totalHeal = setPerformanceStat(totalHeal, participant.getTotalHeal(), participantTeam);
            skillShotsDodged = setPerformanceStat(skillShotsDodged, challenges.getSkillshotsDodged(), participantTeam);
        }

        performanceSets.add(setPerformanceSets("Economy", cs, gold, bounty));
        performanceSets.add(setPerformanceSets("Support", shields, heals, cc));
        performanceSets.add(setPerformanceSets("Combat", kills, damageDealt, assists));
        performanceSets.add(setPerformanceSets("Objectives", structureTakedowns, eliteMonsterTakedowns, objectiveDamage));
        performanceSets.add(setPerformanceSets("Vision", visionScore, wardsPlaced, wardsKilled));
        performanceSets.add(setPerformanceSets("Demise", deaths, timeSpentDead, damageTaken));
        performanceSets.add(setPerformanceSets("Durability", damageSelfMitigated, totalHeal, skillShotsDodged));

        return performanceSets;
    }

    private PerformanceSet setPerformanceSets(String setName, PerformanceStat stat1, PerformanceStat stat2, PerformanceStat stat3){
        PerformanceSet performanceSet = new PerformanceSet(setName);

        List<PerformanceStat> performanceStats = new ArrayList<>();
        performanceStats.add(stat1);
        performanceStats.add(stat2);
        performanceStats.add(stat3);
        performanceSet.setPerformanceStats(performanceStats);

        return performanceSet;
    }

    private PerformanceStat setPerformanceStat(PerformanceStat performanceStat, int participantData, int participantTeam){
        int highestPlayerTotal = performanceStat.getHighestPlayerTotal();

        if (participantData > highestPlayerTotal){
            performanceStat.setHighestPlayerTotal(participantData);
        }

        if (participantTeam == 100) {
            int blueTeamTotal = performanceStat.getBlueTeamTotal();
            blueTeamTotal += participantData;
            performanceStat.setBlueTeamTotal(blueTeamTotal);
        } else {
            int redTeamTotal = performanceStat.getRedTeamTotal();
            redTeamTotal += participantData;
            performanceStat.setRedTeamTotal(redTeamTotal);
        }

        if (performanceStat.getPlayerTotals() == null){
            List<Integer> playerTotals = new ArrayList<>();
            playerTotals.add(participantData);
            performanceStat.setPlayerTotals(playerTotals);
        } else {
            List<Integer> playerTotals = performanceStat.getPlayerTotals();
            playerTotals.add(participantData);
            performanceStat.setPlayerTotals(playerTotals);
        }

        return performanceStat;
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
        boolean friendsWon = false;
        if(match.isSaved()){
//            Participant participant = participantRepositoryDao.findByMatchAndPuuid(match,friend.getPuuId());
            List<Participant> participants = match.getParticipant();
            for (Participant participant : participants) {
                if (participant.getPuuid().equals(friend.getPuuId())){
                    friendsWon = participant.isWin();
                }
            }
        }
        return friendsWon;
    }

    private List<ParticipantDTO> getParticipants(Match match){
        List<ParticipantDTO> participantDTOs = new ArrayList<>();
        List<Participant> participants = match.getParticipant();

        for (Participant participant : participants) {
            ParticipantDTO participantDTO = new ParticipantDTO();
            List<Event> events = participant.getEvent();

            participantDTO.setParticipant(participant);
            participantDTO.setEventItems(getItemList(events));
            participantDTO.setSkillUps(getSkillUps(events));
            participantDTO.setParticipantFrames(participant.getParticipantFrame());
            participantDTO.setParticipantSets(getParticipantSets(participant));
            participantDTO.setParticipantPerks(getParticipantPerks(participant));

            participantDTOs.add(participantDTO);
        }

        return participantDTOs;
    }

    private List<ParticipantPerk> getParticipantPerks(Participant participant){
        List<ParticipantPerk> participantPerks = new ArrayList<>();
        List<Perk> perks = participant.getPerk();

        for (Perk perk : perks) {
            ParticipantPerk participantPerk = new ParticipantPerk();

            participantPerk.setPrimary(perk.isPrimary());
            participantPerk.setPerkNum(perk.getPerkNum());
            participantPerk.setStyle(perk.getStyle());
            participantPerk.setPerkStats(getPerksStats(perk));

            participantPerks.add(participantPerk);
        }

        return participantPerks;
    }

    private List<String> getPerksStats(Perk perk){
        List<String> perkStats = new ArrayList<>();
        String perkStat1 = String.valueOf(perk.getPerkStat1());
        String perkStat2 = String.valueOf(perk.getPerkStat2());
        String perkStat3 = String.valueOf(perk.getPerkStat3());

        List<String> perkDescriptions = getPerkDescriptions(perk.getPerkNum());

        for (String perkDescription : perkDescriptions) {
            if (perkDescription.contains("@eogvar1@")){
                perkDescription = perkDescription.replace("@eogvar1@", perkStat1);
            }
            if (perkDescription.contains("@eogvar2@")){
                perkDescription = perkDescription.replace("@eogvar2@", perkStat2);
            }
            if (perkDescription.contains("@eogvar3@")){
                perkDescription = perkDescription.replace("@eogvar3@", perkStat3);
            }
            perkStats.add(perkDescription);
        }
        return perkStats;
    }

    private List<String> getPerkDescriptions(int perkNum){
        List<String> perkDescriptions = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Get Json file
            JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/json/perks.json"));

            // Iterate through the JSON array
            for (JsonNode itemNode : jsonNode) {
                int itemId = itemNode.get("id").asInt();

                if (itemId == perkNum) {
                    // Found the item with the desired ID, extract endOfGameStatDescs
                    JsonNode endOfGameStatDescsNode = itemNode.get("endOfGameStatDescs");

                    // Extract and print the endOfGameStatDescs
                    if (endOfGameStatDescsNode != null && endOfGameStatDescsNode.isArray()) {
                        perkDescriptions = objectMapper.convertValue(endOfGameStatDescsNode, List.class);

                    } else {
                        System.out.println("End Of Game Stat Descriptions not found for item ID: " + perkNum);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return perkDescriptions;
    }

    private List<ParticipantSet> getParticipantSets(Participant participant){
        List<ParticipantSet> participantSets = new ArrayList<>();
        ParticipantChallenges participantChallenges = participant.getParticipantChallenges();

        List<ParticipantStat> setStats1A = getGoldStats(participant, participantChallenges);
        List<ParticipantStat> setStats1B = getCsStats(participant, participantChallenges);
        List<ParticipantStat> setStats2A = getKdaStats(participant, participantChallenges);
        List<ParticipantStat> setStats2B = getNotorietyStats(participant, participantChallenges);
        List<ParticipantStat> setStats3A = getDealtStats(participant, participantChallenges);
        List<ParticipantStat> setStats3B = getTakenStats(participant, participantChallenges);
        List<ParticipantStat> setStats4A = getBuildingStats(participant, participantChallenges);
        List<ParticipantStat> setStats4B = getMonsterStats(participant, participantChallenges);
        List<ParticipantStat> setStats5A = getSupportStats(participant, participantChallenges);
        List<ParticipantStat> setStats5B = getJungleStats(participant, participantChallenges);
        List<ParticipantStat> setStats6A = getVisionStats(participant, participantChallenges);
        List<ParticipantStat> setStats6B = getMiscStats(participant, participantChallenges);

        participantSets.add(getParticipantSet("Economy", "Gold", "CS", setStats1A, setStats1B));
        participantSets.add(getParticipantSet("Combat", "KDA", "Notoriety", setStats2A, setStats2B));
        participantSets.add(getParticipantSet("Damage", "Dealt", "Taken", setStats3A, setStats3B));
        participantSets.add(getParticipantSet("Objectives", "Buildings", "Monsters", setStats4A, setStats4B));
        participantSets.add(getParticipantSet("Positions", "Support", "Jungle", setStats5A, setStats5B));
        participantSets.add(getParticipantSet("Other", "Vision", "Misc", setStats6A, setStats6B));

        return participantSets;
    }
    private ParticipantSet getParticipantSet(String setName, String section1Name, String section2Name,
                                             List<ParticipantStat> section1Stats, List<ParticipantStat> section2Stats)
    {
        ParticipantSet participantSet = new ParticipantSet(setName, section1Name, section2Name);

        participantSet.setSection1IconName(section1Name);
        participantSet.setSection2IconName(section2Name);

        participantSet.setSection1Stats(section1Stats);
        participantSet.setSection2Stats(section2Stats);

        return participantSet;
    }

    private List<ParticipantStat> getGoldStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getGoldEarned();
        double data2 = challenges.getGoldPerMinute();
        int data3 = challenges.getBountyGold();
        int data4 = participant.getGoldSpent();
        int data5 = challenges.getMaxLevelLeadLaneOpponent();

        sectionStats.add(getParticipantStat("Gold Earned", data1));
        sectionStats.add(getParticipantStat("Gold/min", data2));
        sectionStats.add(getParticipantStat("Bounty Gold Taken", data3));
        sectionStats.add(getParticipantStat("Gold Spent", data4));
        sectionStats.add(getParticipantStat("Max Level Lead", data5));

        return sectionStats;
    }

    private List<ParticipantStat> getCsStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getTotalMinionsKilled() + participant.getNeutralMinionsKilled();
        double data2 = (double) (data1 * 60) / participant.getTimePlayed();
        int data3A = participant.getTotalMinionsKilled();
        int data3B = challenges.getLaneMinionsFirst10Minutes();
        int data4A = participant.getNeutralMinionsKilled();
        int data4B = challenges.getJungleCsBefore10Minutes();

        int data5 = challenges.getMaxCsAdvantageOnLaneOpponent();

        sectionStats.add(getParticipantStat("CS", data1));
        sectionStats.add(getParticipantStat("CS/min", data2));
        sectionStats.add(getParticipantStat("Lane CS (total / @10m)", data3A, data3B));
        sectionStats.add(getParticipantStat("Jungle CS (total / @10m)", data4A, data4B));
        sectionStats.add(getParticipantStat("Max CS Adv.", data5));

        return sectionStats;
    }

    private List<ParticipantStat> getKdaStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getKills();
        double data2 = challenges.getKillParticipation() * 100;
        int data3 = participant.getAssists();
        double data4 = challenges.getKda();
        int data5 = challenges.getKillsUnderOwnTurret();
        int data6 = challenges.getKillsNearEnemyTurret();

        sectionStats.add(getParticipantStat("Kills", data1));
        sectionStats.add(getParticipantStat("Kill Participation", data2));
        sectionStats.add(getParticipantStat("Assists", data3));
        sectionStats.add(getParticipantStat("KDA", data4));
        sectionStats.add(getParticipantStat("Kills Under Ally Turret", data5));
        sectionStats.add(getParticipantStat("Kills Under Enemy Turret", data6));

        return sectionStats;
    }

    private List<ParticipantStat> getNotorietyStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getBountyLevel();
        int data2 = participant.getKillingSprees();
        int data3 = challenges.getMultikills();
        int data4 = participant.getLargestKillingSpree();
        int data5A = participant.getDoubleKills();
        int data5B = participant.getTripleKills();
        int data6A = participant.getQuadraKills();
        int data6B = participant.getPentaKills();

        sectionStats.add(getParticipantStat("Bounty Level", data1));
        sectionStats.add(getParticipantStat("Killing Sprees", data2));
        sectionStats.add(getParticipantStat("MultiKills", data3));
        sectionStats.add(getParticipantStat("Largest Spree", data4));
        sectionStats.add(getParticipantStat("Double / Triple", data5A, data5B));
        sectionStats.add(getParticipantStat("Quadra / Penta", data6A, data6B));

        return sectionStats;
    }

    private List<ParticipantStat> getDealtStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getTotalDamageDealtToChampions();
        double data2 = challenges.getDamagePerMinute();
        double data3 = challenges.getTeamDamagePercentage() * 100;
        int data4 = participant.getLargestCriticalStrike();
        int data5A = participant.getMagicDamageDealtToChampions();
        int data5B = participant.getPhysicalDamageDealtToChampions();
        int data5C = participant.getTrueDamageDealtToChampions();

        sectionStats.add(getParticipantStat("Damage Dealt", data1));
        sectionStats.add(getParticipantStat("Damage/min", data2));
        sectionStats.add(getParticipantStat("% of Team Damage", data3));
        sectionStats.add(getParticipantStat("Highest Critical Hit", data4));
        sectionStats.add(getParticipantStat("Magic / Physical / True", data5A, data5B, data5C));

        return sectionStats;
    }

    private List<ParticipantStat> getTakenStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getTotalDamageTaken();
        int data2 = participant.getDamageSelfMitigated();
        double data3 = challenges.getDamageTakenOnTeamPercentage() * 100;
        int data4 = participant.getTotalHeal();
        int data5A = participant.getMagicDamageTaken();
        int data5B = participant.getPhysicalDamageTaken();
        int data5C = participant.getTrueDamageTaken();

        sectionStats.add(getParticipantStat("Damage Taken", data1));
        sectionStats.add(getParticipantStat("Damage Mitigated", data2));
        sectionStats.add(getParticipantStat("% of Team Damage", data3));
        sectionStats.add(getParticipantStat("Self Healing", data4));
        sectionStats.add(getParticipantStat("Magic / Physical / True", data5A, data5B, data5C));

        return sectionStats;
    }

    private List<ParticipantStat> getBuildingStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = challenges.getTurretPlatesTaken();
        int data2A = participant.getInhibitorKills();
        int data2B = participant.getInhibitorTakedowns();
        int data3A = participant.getTurretKills();
        int data3B = participant.getTurretTakedowns();
        int data4A = participant.getNexusKills();
        int data4B = participant.getNexusTakedowns();
        int data5 = participant.getDamageDealtToBuildings();

        sectionStats.add(getParticipantStat("Turret Plates Destroyed", data1));
        sectionStats.add(getParticipantStat("Inhibitors (Kill/TD)", data2A, data2B));
        sectionStats.add(getParticipantStat("Turrets (Kill/TD)", data3A, data3B));
        sectionStats.add(getParticipantStat("Nexus (Kill/TD)", data4A, data4B));
        sectionStats.add(getParticipantStat("Damage To Objectives", data5));

        return sectionStats;
    }

    private List<ParticipantStat> getMonsterStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1A = challenges.getRiftHeraldTakedowns();
        int data1B = challenges.getRiftHeraldTakedowns();
        int data2A = participant.getDragonKills();
        int data2B = challenges.getDragonTakedowns();
        int data3A = participant.getBaronKills();
        int data3B = challenges.getBaronTakedowns();
        int data4A = participant.getObjectivesStolen();
        int data4B = participant.getObjectivesStolenAssists();
        int data5 = participant.getDamageDealtToObjectives() - participant.getDamageDealtToBuildings();

        sectionStats.add(getParticipantStat("Rift Heralds (Kill/TD)", data1A, data1B));
        sectionStats.add(getParticipantStat("Dragons (Kill/TD)", data2A, data2B));
        sectionStats.add(getParticipantStat("Barons (Kill/TD)", data3A, data3B));
        sectionStats.add(getParticipantStat("Stolen (Kill/TD)", data4A, data4B));
        sectionStats.add(getParticipantStat("Damage to Objectives", data5));

        return sectionStats;
    }

    private List<ParticipantStat> getSupportStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getTotalDamageShieldedOnTeammates();
        int data2 = participant.getTotalHealsOnTeammates();
        int data3 = participant.getTimeCCingOthers();
        int data4 = challenges.getEnemyChampionImmobilizations();
        int data5 = challenges.getEffectiveHealAndShielding();

        sectionStats.add(getParticipantStat("Shields for Allies", data1));
        sectionStats.add(getParticipantStat("Heals for Allies", data2));
        sectionStats.add(getParticipantStat("Crowd Control", data3));
        sectionStats.add(getParticipantStat("Immobilize Enemies", data4));
        sectionStats.add(getParticipantStat("Effective Heals & Shields", data5));

        return sectionStats;
    }

    private List<ParticipantStat> getJungleStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = challenges.getKillsOnOtherLanesEarlyJungleAsLaner();
        int data2 = challenges.getScuttleCrabKills();
        int data3 = challenges.getAlliedJungleMonsterKills();
        int data4 = challenges.getEnemyJungleMonsterKills();
        int data5 = challenges.getBuffsStolen();
        int data6 = challenges.getMoreEnemyJungleThanOpponent();

        sectionStats.add(getParticipantStat("Early Ganks", data1));
        sectionStats.add(getParticipantStat("Skuttle Crabs", data2));
        sectionStats.add(getParticipantStat("Allied Jungle CS", data3));
        sectionStats.add(getParticipantStat("Enemy Jungle CS", data4));
        sectionStats.add(getParticipantStat("Buffs Stolen", data5));
        sectionStats.add(getParticipantStat("Jungle Diff", data6));

        return sectionStats;
    }

    private List<ParticipantStat> getVisionStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getVisionScore();
        double data2 = challenges.getVisionScorePerMinute();
        int data3 = participant.getWardsPlaced();
        int data4 = participant.getWardsKilled();
        int data5 = challenges.getWardsGuarded();
        int data6 = challenges.getVisionScoreAdvantageLaneOpponent();

        sectionStats.add(getParticipantStat("Vision Score", data1));
        sectionStats.add(getParticipantStat("Vision/min", data2));
        sectionStats.add(getParticipantStat("Wards Placed", data3));
        sectionStats.add(getParticipantStat("Wards Killed", data4));
        sectionStats.add(getParticipantStat("Wards Guarded", data5));
        sectionStats.add(getParticipantStat("Vision Score Adv.", data6));

        return sectionStats;
    }

    private List<ParticipantStat> getMiscStats(Participant participant, ParticipantChallenges challenges){
        List<ParticipantStat> sectionStats = new ArrayList<>();

        int data1 = participant.getDeaths();
        int data2 = participant.getTotalTimeSpentDead();
        int data3 = challenges.getSkillshotsHit();
        int data4 = challenges.getSkillshotsDodged();
        int data5 = challenges.getSnowballsHit();
        int data6 = challenges.getMultiKillOneSpell();

        sectionStats.add(getParticipantStat("Deaths", data1));
        sectionStats.add(getParticipantStat("Time Spent Dead", data2));
        sectionStats.add(getParticipantStat("Skillshots Hit", data3));
        sectionStats.add(getParticipantStat("Skillshots Dodged", data4));
        sectionStats.add(getParticipantStat("Snowballs Hit", data5));
        sectionStats.add(getParticipantStat("One Spell Multikills", data6));

        return sectionStats;
    }

    private ParticipantStat getParticipantStat(String statName, Object statValue) {
        ParticipantStat participantStat = new ParticipantStat(statName);
        List<String> statList = new ArrayList<>();

        // Convert statValue to a string and add it to the statList
        statList.add(formatStatValue(statValue));

        participantStat.setStats(statList);
        return participantStat;
    }

    private ParticipantStat getParticipantStat(String statName, Object statValue1, Object statValue2) {
        ParticipantStat participantStat = new ParticipantStat(statName);
        List<String> statList = new ArrayList<>();

        // Convert statValue to a string and add it to the statList
        statList.add(formatStatValue(statValue1));
        statList.add(formatStatValue(statValue2));

        participantStat.setStats(statList);
        return participantStat;
    }

    private ParticipantStat getParticipantStat(String statName, Object statValue1, Object statValue2, Object statValue3) {
        ParticipantStat participantStat = new ParticipantStat(statName);
        List<String> statList = new ArrayList<>();

        // Convert statValue to a string and add it to the statList
        statList.add(formatStatValue(statValue1));
        statList.add(formatStatValue(statValue2));
        statList.add(formatStatValue(statValue3));

        participantStat.setStats(statList);
        return participantStat;
    }

    private String formatStatValue(Object statValue){
        if (statValue instanceof Integer){
            return numberFormat.format(statValue);
        } else if(statValue instanceof Double){
            return decimalFormat.format(statValue);
        } else {
            return "?";
        }
    }

    private List<EventItem> getAllItems(List<Event> events){
        List<EventItem> allItems = new ArrayList<>();
        for (Event event : events) {
            if (event.getEventItem() != null) {
                if (event.getEventItem().getItemType().equals("ITEM_PURCHASED")){
                    allItems.add(event.getEventItem());
                }
            }
        }
        return allItems;
    }

    private List<SkillUp> getSkillUps( List<Event> events){
        List<SkillUp> skillUps = new ArrayList<>();
        for (Event event : events) {
            if (event.getSkillUp() != null){
                skillUps.add(event.getSkillUp());
            }
        }
        return skillUps;
    }

    private List<List<EventItem>> getItemList(List<Event> events){
        List<List<EventItem>> eventItems = new ArrayList<>();
        List<EventItem> allItems = getAllItems(events);

        List<EventItem> currentGroup = new ArrayList<>();
        int interval = 30;
        for ( int i = 0; i < allItems.size(); i++) {
            EventItem currentItem = allItems.get(i);

            if (currentGroup.isEmpty()) {
                currentGroup.add(currentItem);
            } else {
                EventItem lastGroupedItem = currentGroup.get(currentGroup.size() - 1);
                if (currentItem.getEvent().getTimestamp() - lastGroupedItem.getEvent().getTimestamp() <= interval * 1000) {
                    currentGroup.add(currentItem);
                } else {
                    eventItems.add(currentGroup);
                    currentGroup = new ArrayList<>();
                    currentGroup.add(currentItem);
                }
            }

            if((i + 1) == allItems.size()){
                eventItems.add(currentGroup);
            }
        }
        return eventItems;
    }

    @GetMapping("/participant-stats-chart")
    @ResponseBody
    public List<List<ParticipantFrame>> participantStats(Model model, @RequestParam("match") long matchId) {
        Optional<Match> matchObject = matchRepositoryDao.findById(matchId);
        Match match = matchObject.get();

        List<Participant> participants = match.getParticipant();

        List<List<ParticipantFrame>> matchParticipantFrames = new ArrayList<>();
        for (Participant participant : participants) {
            List<ParticipantFrame> participantFrames = participant.getParticipantFrame();
            matchParticipantFrames.add(participantFrames);
        }
        return matchParticipantFrames;
    }

    @GetMapping("/ng")
    @ResponseBody
    public List<Friend> test(Model model) {
        return friendRepositoryDao.findAll();
    }
}
