package com.gagejackson.lof.Controllers;

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

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MatchController {
    private final FriendRepository friendRepositoryDao;
    private final FriendChampsRepository friendChampsRepositoryDao;
    private final FriendRankRepository friendRankRepositoryDao;
    private final FriendMatchRepository friendMatchRepositoryDao;
    private final MatchRepository matchRepositoryDao;
    private final ParticipantRepository participantRepositoryDao;
    private final ParticipantFrameRepository participantFrameRepositoryDao;
    private final ParticipantFrameDamageRepository participantFrameDamageRepositoryDao;
    private final ParticipantFrameChampRepository participantFrameChampRepositoryDao;
    private final EventRepository eventDao;

    public MatchController(
            FriendRepository friendRepositoryDao,
            FriendChampsRepository friendChampsRepositoryDao,
            FriendRankRepository friendRankRepositoryDao,
            FriendMatchRepository friendMatchRepositoryDao,
            MatchRepository matchRepositoryDao,
            ParticipantRepository participantRepositoryDao,
            ParticipantFrameRepository participantFrameRepositoryDao,
            ParticipantFrameDamageRepository participantFrameDamageRepositoryDao,
            ParticipantFrameChampRepository participantFrameChampRepositoryDao,
            EventRepository eventDao

    ){
        this.friendRepositoryDao = friendRepositoryDao;
        this.friendChampsRepositoryDao = friendChampsRepositoryDao;
        this.friendRankRepositoryDao = friendRankRepositoryDao;
        this.friendMatchRepositoryDao = friendMatchRepositoryDao;
        this.matchRepositoryDao = matchRepositoryDao;
        this.participantRepositoryDao = participantRepositoryDao;
        this.participantFrameRepositoryDao = participantFrameRepositoryDao;
        this.participantFrameDamageRepositoryDao = participantFrameDamageRepositoryDao;
        this.participantFrameChampRepositoryDao = participantFrameChampRepositoryDao;
        this.eventDao = eventDao;
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

        List<ParticipantDTO> participants = getParticipants(match);

        List<PerformanceStat> performanceStats = getPerformanceStats(participants);

        List<EventKill> killEvents = getKillEvents(participants);

        model.addAttribute("matchInfo", matchInfo);
        model.addAttribute("friends", friends);
        model.addAttribute("participants", participants);
        model.addAttribute("performanceStats", performanceStats);
        model.addAttribute("killEvents", killEvents);

        return "detailed-match";
    }

    private List<EventKill> getKillEvents(List<ParticipantDTO> participants){
        List<EventKill> killEvents = new ArrayList<>();

        for (ParticipantDTO participant : participants) {
            List<Event> events = participant.getParticipant().getEvent();
            for (Event event : events) {
                if (event.getChampKill() != null){
                    ChampKill champKill = event.getChampKill();
                    EventKill eventKill = new EventKill();
                    eventKill.setKiller(event.getParticipant());
                    eventKill.setKilled(champKill.getVictim());
                    eventKill.setTimestamp(event.getTimestamp());

                    System.out.println("champKill.getPositionX() = " + ((champKill.getPositionX() / 100)));
                    System.out.println("champKill.getPositionY() = " + ((champKill.getPositionY() / 100)));

                    eventKill.setPosX((champKill.getPositionX() / 150) + 0);
                    eventKill.setPosY((champKill.getPositionY() / 150) + 0);

                    killEvents.add(eventKill);
                }
            }
        }

        List<EventKill> sortedEvents = killEvents.stream()
                .sorted(Comparator.comparing(EventKill::getTimestamp))
                .collect(Collectors.toList());

        return sortedEvents;
    }

    private List<PerformanceStat> getPerformanceStats(List<ParticipantDTO> participants){
        List<PerformanceStat> performanceStats = new ArrayList<>();

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

        performanceStats.add(gold);
        performanceStats.add(cs);
        performanceStats.add(bounty);

        performanceStats.add(shields);
        performanceStats.add(heals);
        performanceStats.add(cc);

        performanceStats.add(kills);
        performanceStats.add(damageDealt);
        performanceStats.add(assists);

        performanceStats.add(structureTakedowns);
        performanceStats.add(eliteMonsterTakedowns);
        performanceStats.add(objectiveDamage);

        performanceStats.add(visionScore);
        performanceStats.add(wardsPlaced);
        performanceStats.add(wardsKilled);

        performanceStats.add(deaths);
        performanceStats.add(timeSpentDead);
        performanceStats.add(damageTaken);

        performanceStats.add(damageSelfMitigated);
        performanceStats.add(totalHeal);
        performanceStats.add(skillShotsDodged);

        return performanceStats;
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
            List<SkillUp> skillUps = new ArrayList<>();
            List<List<EventItem>> eventItems = new ArrayList<>();
            List<EventItem> allItems = new ArrayList<>();

            List<Event> events = participant.getEvent();
            for (Event event : events) {
                if (event.getEventItem() != null) {
                    if (event.getEventItem().getItemType().equals("ITEM_PURCHASED")){
                        allItems.add(event.getEventItem());
                    }
                }

                if (event.getSkillUp() != null){
                    skillUps.add(event.getSkillUp());
                }
            }

            List<EventItem> currentGroup = new ArrayList<>();
            int interval = 30;
            System.out.println("allItems = " + allItems.size());
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

            List<ParticipantFrame> participantFrames = participant.getParticipantFrame();

            System.out.println("eventItems = " + eventItems.size());

            participantDTO.setParticipant(participant);
            participantDTO.setEventItems(eventItems);
            participantDTO.setSkillUps(skillUps);
            participantDTO.setParticipantFrames(participantFrames);

            participantDTOs.add(participantDTO);
        }

        return participantDTOs;
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
