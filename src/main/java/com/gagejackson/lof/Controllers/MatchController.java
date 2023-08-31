package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.DTOs.FriendSelection;
import com.gagejackson.lof.DTOs.MatchInfo;
import com.gagejackson.lof.DTOs.ParticipantDTO;
import com.gagejackson.lof.Models.Friend.Friend;
import com.gagejackson.lof.Models.Friend.FriendMatch;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

        model.addAttribute("matchInfo", matchInfo);
        model.addAttribute("friends", friends);
        model.addAttribute("participants", participants);

        return "detailed-match";
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
            }

            List<ParticipantFrame> participantFrames = participant.getParticipantFrame();

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
    public List<Participant> participantStats(Model model, @RequestParam("match") long matchId) {
        Optional<Match> matchObject = matchRepositoryDao.findById(matchId);
        Match match = matchObject.get();

        List<Participant> participants = match.getParticipant();

//        List<ParticipantDTO> participants = getParticipants(match);

//        for (Participant participant : participants) {
//            participant.getParticipantFrame();
//        }

        System.out.println("match id = " + match.getParticipant().get(0).getSummonerName());
        return participants;
    }

    @GetMapping("/ng")
    @ResponseBody
    public List<Friend> test(Model model) {
        return friendRepositoryDao.findAll();
    }
}
