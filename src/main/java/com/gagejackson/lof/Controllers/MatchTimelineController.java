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

        saveParticipantFramesData(frames, newMatch);
    }

    private void saveParticipantFramesData(List<Map<String, Object>> frames, Match newMatch){
        int currentFrame = 0;
        List<String> eventTypes = new ArrayList<>();

        for (Map<String, Object> frame : frames) {
            Map<String, Object> participantFrames = (Map<String, Object>) frame.get("participantFrames");

            List<Map<String, Object>> events = (List<Map<String, Object>>) frame.get("events");
            for (Map<String, Object> event : events) {

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
                if((event.get("type")).equals("SKILL_LEVEL_UP")){
                    eventTypes.add((String) event.get("levelUpType"));
                } else {
                }

                //finds timestamps for .equals(XXX)
//                if((event.get("type")).equals("ITEM_UNDO")){
//                    eventTypes.add(Integer.toString(((int) event.get("timestamp"))));
//                } else {
//                }
            }

            for (int i = 1; i <= participantFrames.size(); i++) {
                saveParticipantFrame(participantFrames, newMatch, currentFrame, i);
            }
            currentFrame++;
        }
        System.out.println("eventTypes = " + eventTypes);
    }

    private void saveParticipantFrame(Map<String, Object> participantFrames, Match newMatch, int currentFrame, int i){
        Map<String, Object> participantFrameData = (Map<String, Object>) participantFrames.get(String.valueOf(i));
        ParticipantFrame participantFrame = new ParticipantFrame();

        Participant participant = participantRepositoryDao.findByMatchAndParticipantId(newMatch, i);

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


    }
