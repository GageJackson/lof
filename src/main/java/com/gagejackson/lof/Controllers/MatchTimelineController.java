package com.gagejackson.lof.Controllers;

import com.gagejackson.lof.Models.*;
import com.gagejackson.lof.Repositories.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        for (Map<String, Object> frame:frames) {
            Map<String, Object> participantFrames = (Map<String, Object>) frame.get("participantFrames");

            for (int i = 1; i <= participantFrames.size(); i++) {
                saveParticipantFrame(participantFrames, newMatch, i);
            }
        }
    }

    private void saveParticipantFrame(Map<String, Object> participantFrames, Match newMatch, int i){
        Map<String, Object> participantFrameData = (Map<String, Object>) participantFrames.get(i);
        ParticipantFrame participantFrame = new ParticipantFrame();

        Participant participant = participantRepositoryDao.findByMatchAndParticipantId(newMatch, i);

        participantFrame.setCurrentGold((int) participantFrameData.get("currentGold"));
        participantFrame.setJungleMinionsKilled((int) participantFrameData.get("jungleMinionsKilled"));
        participantFrame.setLevel((int) participantFrameData.get("level"));
        participantFrame.setMinionsKilled((int) participantFrameData.get("minionsKilled"));
        participantFrame.setTimeEnemySpentControlled((int) participantFrameData.get("timeEnemySpentControlled"));
        participantFrame.setTotalGold((int) participantFrameData.get("totalGold"));
        participantFrame.setXp((int) participantFrameData.get("xp"));
        participantFrame.setParticipant(participant);

        ParticipantFrame newParticipantFrame = participantFrameRepositoryDao.save(participantFrame);

        saveParticipantFrameChampData(participantFrameData, newParticipantFrame);
        saveParticipantFrameDamageData(participantFrameData, newParticipantFrame);
    }

    private void saveParticipantFrameChampData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameChamp participantFrameChamp = new ParticipantFrameChamp();

        participantFrameChamp.setAbilityHaste((int) participantFrameData.get("abilityHaste"));
        participantFrameChamp.setAbilityPower((int) participantFrameData.get("abilityPower"));
        participantFrameChamp.setArmor((int) participantFrameData.get("armor"));
        participantFrameChamp.setArmorPen((int) participantFrameData.get("armorPen"));
        participantFrameChamp.setArmorPenPercent((int) participantFrameData.get("armorPenPercent"));
        participantFrameChamp.setAttackDamage((int) participantFrameData.get("attackDamage"));
        participantFrameChamp.setAttackSpeed((int) participantFrameData.get("attackSpeed"));
        participantFrameChamp.setBonusArmorPenPercent((int) participantFrameData.get("bonusArmorPenPercent"));
        participantFrameChamp.setBonusMagicPenPercent((int) participantFrameData.get("bonusMagicPenPercent"));
        participantFrameChamp.setCcReduction((int) participantFrameData.get("ccReduction"));
        participantFrameChamp.setCooldownReduction((int) participantFrameData.get("cooldownReduction"));
        participantFrameChamp.setHealth((int) participantFrameData.get("health"));
        participantFrameChamp.setHealthMax((int) participantFrameData.get("healthMax"));
        participantFrameChamp.setHealthRegen((int) participantFrameData.get("healthRegen"));
        participantFrameChamp.setLifesteal((int) participantFrameData.get("lifesteal"));
        participantFrameChamp.setMagicPen((int) participantFrameData.get("magicPen"));
        participantFrameChamp.setMagicPenPercent((int) participantFrameData.get("magicPenPercent"));
        participantFrameChamp.setMagicResist((int) participantFrameData.get("magicResist"));
        participantFrameChamp.setMovementSpeed((int) participantFrameData.get("movementSpeed"));
        participantFrameChamp.setOmnivamp((int) participantFrameData.get("omnivamp"));
        participantFrameChamp.setPhysicalVamp((int) participantFrameData.get("physicalVamp"));
        participantFrameChamp.setPower((int) participantFrameData.get("power"));
        participantFrameChamp.setPowerMax((int) participantFrameData.get("powerMax"));
        participantFrameChamp.setPowerRegen((int) participantFrameData.get("powerRegen"));
        participantFrameChamp.setSpellVamp((int) participantFrameData.get("spellVamp"));
        participantFrameChamp.setParticipantFrame(newParticipantFrame);

        participantFrameChampRepositoryDao.save(participantFrameChamp);
    }

    private void saveParticipantFrameDamageData(Map<String, Object> participantFrameData, ParticipantFrame newParticipantFrame){
        ParticipantFrameDamage participantFrameDamage = new ParticipantFrameDamage();

        participantFrameDamage.setMagicDamageDone((int) participantFrameData.get("magicDamageDone"));
        participantFrameDamage.setMagicDamageDone((int) participantFrameData.get("magicDamageDone"));
        participantFrameDamage.setMagicDamageDoneToChamps((int) participantFrameData.get("magicDamageDoneToChamps"));
        participantFrameDamage.setMagicDamageTaken((int) participantFrameData.get("magicDamageTaken"));
        participantFrameDamage.setPhysicalDamageDone((int) participantFrameData.get("physicalDamageDone"));
        participantFrameDamage.setPhysicalDamageDoneToChamps((int) participantFrameData.get("physicalDamageDoneToChamps"));
        participantFrameDamage.setPhysicalDamageTaken((int) participantFrameData.get("physicalDamageTaken"));
        participantFrameDamage.setTotalDamageDone((int) participantFrameData.get("totalDamageDone"));
        participantFrameDamage.setTotalDamageDoneToChamps((int) participantFrameData.get("totalDamageDoneToChamps"));
        participantFrameDamage.setTotalDamageTaken((int) participantFrameData.get("totalDamageTaken"));
        participantFrameDamage.setTrueDamageDone((int) participantFrameData.get("trueDamageDone"));
        participantFrameDamage.setTrueDamageDoneToChamps((int) participantFrameData.get("trueDamageDoneToChamps"));
        participantFrameDamage.setTrueDamageTaken((int) participantFrameData.get("trueDamageTaken"));
        participantFrameDamage.setParticipantFrame(newParticipantFrame);

        participantFrameDamageRepositoryDao.save(participantFrameDamage);
    }


    }
