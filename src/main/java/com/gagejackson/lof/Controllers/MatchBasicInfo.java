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
public class MatchBasicInfo {
    private final MatchRepository matchRepositoryDao;
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

    /*
    Batch Save Arrays
     */
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
    private List<Participant> participants = new ArrayList<>();
    private List<Perk> perks = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Ban> bans = new ArrayList<>();
    private List<Objective> objectives = new ArrayList<>();

    /*
       Other Local Variables
    */
    private Map<Integer, Participant> participantsData = new HashMap<>();



    public MatchBasicInfo(
            MatchRepository matchRepositoryDao,
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
        this.matchRepositoryDao = matchRepositoryDao;
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

    @PostMapping("/burgle")
    public void saveMatchData(@RequestBody List<Map<String, Object>> matches) {
        Map<String, Object> overviewMatch = (Map<String, Object>) matches.get(0);
        Map<String, Object> overviewData = (Map<String, Object>) overviewMatch.get("info");

        Map<String, Object> timelineMatch = (Map<String, Object>) matches.get(1);
        Map<String, Object> timelineData = (Map<String, Object>) timelineMatch.get("info");
        List<Map<String, Object>> timelineFrames = (List<Map<String, Object>>) timelineData.get("frames");

        Match newMatch = saveMatchData(overviewData);
        if (newMatch != null){
            saveParticipantData(overviewData, newMatch);
            saveTeamData(overviewData, newMatch);
            insertBatchOverviewData();
            saveMatchTimeline(timelineFrames, newMatch);
        } else{
            return;
        }
    }

    private Match saveMatchData(Map<String, Object> matchData){
        Match match = new Match();

        long gameId = (long) matchData.get("gameId");
        match.setGameId(gameId);

        if(matchRepositoryDao.findByGameId(gameId) == null){
            return matchRepositoryDao.save(match);
        }

        return null;
    }

    private void saveParticipantData(Map<String, Object> matchData, Match newMatch) {
        List<Map<String, Object>> participantsData = (List<Map<String, Object>>) matchData.get("participants");

        for (Map<String, Object> participantData : participantsData) {
            Participant participant = new Participant();

            participant.setAssists((int) participantData.get("assists"));

            participant.setMatch(newMatch);

            participants.add(participant);

            savePerkData(participantData, participant);
        }

    }

    private void savePerkData(Map<String, Object> info, Participant newParticipant) {
        Map<String, Object> perksData = (Map<String, Object>) info.get("perks");
        List<Map<String, Object>> stylesData = (List<Map<String, Object>>) perksData.get("styles");

        for (Map<String, Object> styleData : stylesData) {
            List<Map<String, Object>> selectionsData = (List<Map<String, Object>>) styleData.get("selections");
            String description = (String) styleData.get("description");

            for (Map<String, Object> selectionData : selectionsData) {
                Perk perk = new Perk();
                perk.setPerkNum((int) selectionData.get("perk"));
                perk.setParticipant(newParticipant);

                perks.add(perk);
            }
        }
    }

    private void saveTeamData(Map<String, Object> info, Match newMatch) {
        List<Map<String, Object>> teamsData = (List<Map<String, Object>>) info.get("teams");
        for (Map<String, Object> teamData : teamsData) {
            Team team = new Team();

            int teamId = (int) teamData.get("teamId");
            if ((teamData.get("win")).equals("true")) {
                team.setWin(true);
            } else {
                team.setWin(false);
            }

            team.setTeamNum(teamId);
            team.setMatch(newMatch);

            teams.add(team);

            saveBanData(teamData, team);
            saveObjectiveData(teamData, team);
        }
    }

    private void saveBanData(Map<String, Object> info, Team newTeam) {
        List<Map<String, Object>> bansData = (List<Map<String, Object>>) info.get("bans");
        if(bansData.isEmpty()){
            return;
        }
        for (Map<String, Object> banData : bansData) {
            int champId = (int) banData.get("championId");
            int pickTurn = (int) banData.get("pickTurn");

            Ban ban = new Ban();
            ban.setChampId(champId);
            ban.setPickTurn(pickTurn);
            ban.setTeam(newTeam);

            bans.add(ban);
        }
    }

    private void saveObjectiveData(Map<String, Object> info, Team newTeam) {
        Map<String, Object> objectivesData = (Map<String, Object>) info.get("objectives");

        if(objectivesData.size() == 0){
            System.out.println("empty");
            return;
        }

        objectives.add(saveObjective("baron", objectivesData, newTeam));
        objectives.add(saveObjective("champion", objectivesData, newTeam));
        objectives.add(saveObjective("dragon", objectivesData, newTeam));
        objectives.add(saveObjective("inhibitor", objectivesData, newTeam));
        objectives.add(saveObjective("riftHerald", objectivesData, newTeam));
        objectives.add(saveObjective("tower", objectivesData, newTeam));
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

    private void saveMatchTimeline(List<Map<String, Object>> framesData, Match newMatch){
        int currentFrame = 0;
        participantsData = getParticipantsByMatch(newMatch);

        for (Map<String, Object> frameData : framesData) {

            saveEvents(frameData);
            saveParticipantFrames(frameData, currentFrame);

            currentFrame++;
        }

        insertBatchTimelineData();
    }

    private Map<Integer, Participant> getParticipantsByMatch(Match newMatch) {
        List<Participant> participants = participantRepositoryDao.findByMatch(newMatch);
        Map<Integer, Participant> participantMap = new HashMap<>();
        for (Participant participant : participants) {
            participantMap.put(participant.getParticipantNum(), participant);
        }
        return participantMap;
    }

    private void saveParticipantFrames(Map<String, Object> frameData, int currentFrame){
        Map<String, Object> participantFramesData = (Map<String, Object>) frameData.get("participantFrames");
        for (int i = 1; i <= participantFramesData.size(); i++) {
            Participant participant = participantsData.get(i);
            saveParticipantFrame(participantFramesData, currentFrame, i, participant);
        }
    }

    private void saveEvents(Map<String, Object> frameData){
        List<Map<String, Object>> eventsData = (List<Map<String, Object>>) frameData.get("events");

        for (Map<String, Object> eventData : eventsData) {
            saveEvent(eventData);
        }
    }

    private Event createEvent(Map<String, Object> eventData, Participant participant){
        Event event = new Event();
        event.setTimestamp((int)eventData.get("timestamp"));
        event.setParticipant(participant);
        events.add(event);
        return event;
    }

    private void saveParticipantFrame(Map<String, Object> participantFramesData, int currentFrame, int i, Participant participant){
        Map<String, Object> participantFrameData = (Map<String, Object>) participantFramesData.get(String.valueOf(i));
        ParticipantFrame participantFrame = new ParticipantFrame();

        System.out.println("currentFrame = " + currentFrame + " i = " + i + " line 409");

        participantFrame.setCurrentGold((int) participantFrameData.get("currentGold"));

        participantFrame.setFrame(currentFrame);
        participantFrame.setParticipant(participant);

        participantFrames.add(participantFrame);

        saveChampData(participantFrameData, participantFrame);
        saveDamageData(participantFrameData, participantFrame);
    }

    private void saveChampData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameChamp participantFrameChamp = new ParticipantFrameChamp();
        Map<String, Object> champData = (Map<String, Object>) participantFrameData.get("championStats");


        participantFrameChamp.setAbilityHaste((int) champData.get("abilityHaste"));

        participantFrameChamp.setParticipantFrame(newParticipantFrame);

        participantFrameChamps.add(participantFrameChamp);
    }

    private void saveDamageData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameDamage participantFrameDamage = new ParticipantFrameDamage();
        Map<String, Object> damageData = (Map<String, Object>) participantFrameData.get("damageStats");

        participantFrameDamage.setMagicDamageDone((int) damageData.get("magicDamageDone"));
        participantFrameDamage.setParticipantFrame(newParticipantFrame);

        participantFrameDamages.add(participantFrameDamage);
    }

    private void saveEvent(Map<String, Object> eventData){
        String eventType = (String) eventData.get("type");
        switch (eventType) {
            case "ITEM_PURCHASE" -> saveItemEvent("ITEM_PURCHASE", eventData);
            case "ITEM_DESTROYED" -> saveItemEvent("ITEM_DESTROYED", eventData);
            case "ITEM_SOLD" -> saveItemEvent("ITEM_SOLD", eventData);
            case "SKILL_LEVEL_UP" -> saveSkillUp(eventData);
            case "LEVEL_UP" -> saveLevelUp(eventData);
            case "BUILDING_KILL" -> saveBuildingKill("BUILDING_KILL", eventData);
            case "TURRET_PLATE_DESTROYED" -> saveBuildingKill("TURRET_PLATE_DESTROYED", eventData);
            case "ELITE_MONSTER_KILL" -> saveMonsterKill(eventData);
            case "CHAMPION_KILL" -> saveChampionKill(eventData);
            case "CHAMPION_SPECIAL_KILL" -> saveSpecialKill(eventData);
            default -> {
            }
        }
    }

    private void saveItemEvent(String itemType, Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("participantId"));
        Event event = createEvent(eventData, participant);

        Item item = new Item();
        item.setItemType(itemType);
        item.setItemNum((int)eventData.get("itemId"));
        item.setEvent(event);

        items.add(item);
    }

    private void saveSkillUp(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("participantId"));
        Event event = createEvent(eventData, participant);

        SkillUp skillUp = new SkillUp();
        skillUp.setLevelUpType((String) eventData.get("levelUpType"));
        skillUp.setSkillSlot((int) eventData.get("skillSlot"));
        skillUp.setEvent(event);

        skillUps.add(skillUp);
    }

    private void saveLevelUp(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("participantId"));
        Event event = createEvent(eventData, participant);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel((int) eventData.get("level"));
        levelUp.setEvent(event);

        levelUps.add(levelUp);
    }

    private void saveBuildingKill(String buildingType, Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Event event = createEvent(eventData, participant);

        BuildingKill buildingKill = new BuildingKill();

        buildingKill.setEvent(event);

        buildingKills.add(buildingKill);
    }

    private void saveMonsterKill(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Event event = createEvent(eventData, participant);

        MonsterKill monsterKill = new MonsterKill();

        monsterKill.setEvent(event);

        monsterKills.add(monsterKill);
    }

    private void saveChampionKill(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Participant victim = participantsData.get((int)eventData.get("victimId"));
        Event event = createEvent(eventData, participant);

        ChampKill champKill = new ChampKill();

        champKill.setVictim(victim);
        champKill.setEvent(event);

        champKills.add(champKill);
    }

    private void saveDamageDealt(ChampKill champKillData, List<Map<String, Object>> attacksData) {
        for (Map<String,Object> attackData : attacksData) {
            DamageDealt damageDealt = new DamageDealt();

            damageDealt.setEventChampKill(champKillData);

            damageDealtList.add(damageDealt);
        }
    }

    private void saveDamageReceived(ChampKill champKillData, List<Map<String, Object>> attacksData) {
        for (Map<String,Object> attackData : attacksData) {
            DamageReceived damageReceived = new DamageReceived();

            Participant participant = participantsData.get((int)attackData.get("participantId"));

            damageReceived.setParticipant(participant);
            damageReceived.setEventChampKill(champKillData);

            damageReceivedList.add(damageReceived);
        }
    }

    private void saveSpecialKill(Map<String, Object> eventData){
        Participant participant = participantsData.get((int)eventData.get("killerId"));
        Event event = createEvent(eventData, participant);

        SpecialKill specialKill = new SpecialKill();

        specialKill.setKillType((String)eventData.get("killType"));
        specialKill.setEvent(event);

        specialKills.add(specialKill);
    }

    private void insertBatchOverviewData(){
        participantRepositoryDao.saveAll(participants);

        perkRepositoryDao.saveAll(perks);
        teamRepositoryDao.saveAll(teams);
        banRepositoryDao.saveAll(bans);
        objectiveRepositoryDao.saveAll(objectives);
    }

    private void insertBatchTimelineData(){
        eventRepositoryDao.saveAll(events);

        itemRepositoryDao.saveAll(items);
        skillUpRepositoryDao.saveAll(skillUps);
        levelUpRepositoryDao.saveAll(levelUps);
        buildingKillRepositoryDao.saveAll(buildingKills);
        monsterKillRepositoryDao.saveAll(monsterKills);
        specialKillRepositoryDao.saveAll(specialKills);

        champKillRepositoryDao.saveAll(champKills);
        damageDealtRepositoryDao.saveAll(damageDealtList);
        damageReceivedRepositoryDao.saveAll(damageReceivedList);

        participantFrameRepositoryDao.saveAll(participantFrames);
        participantFrameChampRepositoryDao.saveAll(participantFrameChamps);
        participantFrameDamageRepositoryDao.saveAll(participantFrameDamages);
    }
}
