package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.*;
import com.gagejackson.lof.Repositories.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MatchTimelineController {
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


    public MatchTimelineController(
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

    @PostMapping("/saveMatchTimelineData")
    public void getMatchData(@RequestBody Map<String, Object> data) {
        Map<String, Object> info = (Map<String, Object>) data.get("info");
        List<Map<String, Object>> frames = (List<Map<String, Object>>) info.get("frames");

        String matchIdString = (String) data.get("matchId");
        long matchId = Long.parseLong(matchIdString.substring(matchIdString.indexOf("_") + 1));
        Match newMatch = matchRepositoryDao.findByGameId(matchId);

        saveMatchTimeline(frames, newMatch);
    }

    private void saveMatchTimeline(List<Map<String, Object>> frames, Match newMatch){
        int currentFrame = 0;

        for (Map<String, Object> frame : frames) {
            Map<String, Object> participantFrames = (Map<String, Object>) frame.get("participantFrames");

            List<Map<String, Object>> events = (List<Map<String, Object>>) frame.get("events");
            for (Map<String, Object> event : events) {
//                getEventInfo(event); //useful for probing for information about events
                saveEvent(event, newMatch);
            }

            for (int i = 1; i <= participantFrames.size(); i++) {
                saveParticipantFrame(participantFrames, newMatch, currentFrame, i);
            }
            currentFrame++;
        }
    }

    private void saveParticipantFrame(Map<String, Object> participantFrames, Match newMatch, int currentFrame, int i){
        Map<String, Object> participantFrameData = (Map<String, Object>) participantFrames.get(String.valueOf(i));
        ParticipantFrame participantFrame = new ParticipantFrame();

        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, i);

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

        participantFrameChampRepositoryDao.save(participantFrameChamp);
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

        participantFrameDamageRepositoryDao.save(participantFrameDamage);
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

//            case "ITEM_UNDO":
//                saveItemEvent("ITEM_UNDO", event, newMatch);
//                break;

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
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("participantId")));
        Event event = getNewEvent(eventData, participant);

        Item item = new Item();
        item.setItemType(itemType);
        item.setItemNum((int)eventData.get("itemId"));
        item.setEvent(event);

        itemRepositoryDao.save(item);
    }

    private void saveSkillUp(Map<String, Object> eventData, Match newMatch){
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("participantId")));
        Event event = getNewEvent(eventData, participant);

        SkillUp skillUp = new SkillUp();
        skillUp.setLevelUpType((String) eventData.get("levelUpType"));
        skillUp.setSkillSlot((int) eventData.get("skillSlot"));
        skillUp.setEvent(event);

        skillUpRepositoryDao.save(skillUp);
    }

    private void saveLevelUp(Map<String, Object> eventData, Match newMatch){
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("participantId")));
        Event event = getNewEvent(eventData, participant);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel((int) eventData.get("level"));
        levelUp.setEvent(event);

        levelUpRepositoryDao.save(levelUp);
    }

    private void saveBuildingKill(String buildingType, Map<String, Object> eventData, Match newMatch){
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("killerId")));
        Event event = getNewEvent(eventData, participant);

        BuildingKill buildingKill = new BuildingKill();
        buildingKill.setBounty((int)eventData.getOrDefault("bounty", 0));
        buildingKill.setBuildingType((String)eventData.getOrDefault("buildingType", buildingType));
        buildingKill.setLaneType((String) eventData.get("laneType"));

        Map<String, Object> position = (Map<String, Object>) eventData.get("position");
        buildingKill.setPositionX((int)position.get("x"));
        buildingKill.setPositionY((int)position.get("y"));

        buildingKill.setEvent(event);

        buildingKillRepositoryDao.save(buildingKill);
    }

    private void saveMonsterKill(Map<String, Object> eventData, Match newMatch){
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("killerId")));
        Event event = getNewEvent(eventData, participant);

        MonsterKill monsterKill = new MonsterKill();

        monsterKill.setBounty((int)eventData.get("bounty"));
        monsterKill.setMonsterType((String) eventData.get("monsterType"));
        monsterKill.setMonsterSubtype((String) eventData.get("monsterSubType"));

        Map<String, Object> position = (Map<String, Object>) eventData.get("position");
        monsterKill.setPositionX((int)position.get("x"));
        monsterKill.setPositionY((int)position.get("y"));

        monsterKill.setEvent(event);

        monsterKillRepositoryDao.save(monsterKill);
    }

    private void saveChampionKill(Map<String, Object> eventData, Match newMatch){
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("killerId")));
        Participant victim = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("victimId")));
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

            damageDealtRepositoryDao.save(damageDealt);
        }
    }

    private void saveDamageReceived(ChampKill champKillData, List<Map<String, Object>> attacks, Match newMatch) {
        for (Map<String,Object> attack : attacks) {
            DamageReceived damageReceived = new DamageReceived();

            Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)attack.get("participantId")));

            damageReceived.setBasic((boolean) attack.get("basic"));
            damageReceived.setType((String) attack.get("type"));
            damageReceived.setSpellName((String) attack.get("spellName"));
            damageReceived.setSpellSlot((int) attack.get("spellSlot"));
            damageReceived.setMagicDamage((int) attack.get("magicDamage"));
            damageReceived.setPhysicalDamage((int) attack.get("physicalDamage"));
            damageReceived.setTrueDamage((int) attack.get("trueDamage"));
            damageReceived.setParticipant(participant);
            damageReceived.setEventChampKill(champKillData);

            damageReceivedRepositoryDao.save(damageReceived);
        }
    }

    private void saveSpecialKill(Map<String, Object> eventData, Match newMatch){
        Participant participant = participantRepositoryDao.findByMatchAndParticipantNum(newMatch, ((int)eventData.get("killerId")));
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

        specialKillRepositoryDao.save(specialKill);
    }


}
