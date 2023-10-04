package com.gagejackson.lof.Models.MatchOverview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gagejackson.lof.Models.MatchEvent.Event;
import jakarta.persistence.*;

@Entity
@Table(name = "participant_challenges")
public class ParticipantChallenges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column(name = "12_assist_streak_count")
    private int _12AssistStreakCount;

    @Column(name = "ability_uses")
    private int abilityUses;

    @Column(name = "aces_before_15_minutes")
    private int acesBefore15Minutes;

    @Column(name = "allied_jungle_monster_kills")
    private int alliedJungleMonsterKills;

    @Column(name = "baron_takedowns")
    private int baronTakedowns;

    @Column(name = "blast_cone_opposite_opponent_count")
    private int blastConeOppositeOpponentCount;

    @Column(name = "bounty_gold")
    private int bountyGold;

    @Column(name = "buffs_stolen")
    private int buffsStolen;

    @Column(name = "complete_support_quest_in_time")
    private int completeSupportQuestInTime;

    @Column(name = "control_wards_placed")
    private int controlWardsPlaced;

    @Column(name = "damage_per_minute")
    private double damagePerMinute;

    @Column(name = "damage_taken_on_team_percentage")
    private double damageTakenOnTeamPercentage;

    @Column(name = "danced_with_rift_herald")
    private int dancedWithRiftHerald;

    @Column(name = "deaths_by_enemy_champs")
    private int deathsByEnemyChamps;

    @Column(name = "dodge_skill_shots_small_window")
    private int dodgeSkillShotsSmallWindow;

    @Column(name = "double_aces")
    private int doubleAces;

    @Column(name = "dragon_takedowns")
    private int dragonTakedowns;

    @Column(name = "early_laning_phase_gold_exp_advantage")
    private int earlyLaningPhaseGoldExpAdvantage;

    @Column(name = "effective_heal_and_shielding")
    private int effectiveHealAndShielding;

    @Column(name = "elder_dragon_kills_with_opposing_soul")
    private int elderDragonKillsWithOpposingSoul;

    @Column(name = "elder_dragon_multikills")
    private int elderDragonMultikills;

    @Column(name = "enemy_champion_immobilizations")
    private int enemyChampionImmobilizations;

    @Column(name = "enemy_jungle_monster_kills")
    private int enemyJungleMonsterKills;

    @Column(name = "epic_monster_kills_near_enemy_jungler")
    private int epicMonsterKillsNearEnemyJungler;

    @Column(name = "epic_monster_kills_within_30_seconds_of_spawn")
    private int epicMonsterKillsWithin30SecondsOfSpawn;

    @Column(name = "epic_monster_steals")
    private int epicMonsterSteals;

    @Column(name = "epic_monster_stolen_without_smite")
    private int epicMonsterStolenWithoutSmite;

    @Column(name = "first_turret_killed")
    private int firstTurretKilled;

    @Column(name = "first_turret_killed_time")
    private double firstTurretKilledTime;

    @Column(name = "flawless_aces")
    private int flawlessAces;

    @Column(name = "full_team_takedown")
    private int fullTeamTakedown;

    @Column(name = "game_length")
    private double gameLength;

    @Column(name = "get_takedowns_in_all_lanes_early_jungle_as_laner")
    private int getTakedownsInAllLanesEarlyJungleAsLaner;

    @Column(name = "gold_per_minute")
    private double goldPerMinute;

    @Column(name = "had_open_nexus")
    private int hadOpenNexus;

    @Column(name = "immobilize_and_kill_with_ally")
    private int immobilizeAndKillWithAlly;

    @Column(name = "initial_buff_count")
    private int initialBuffCount;

    @Column(name = "initial_crab_count")
    private int initialCrabCount;

    @Column(name = "jungle_cs_before_10_minutes")
    private int jungleCsBefore10Minutes;

    @Column(name = "jungler_takedowns_near_damaged_epic_monster")
    private int junglerTakedownsNearDamagedEpicMonster;

    @Column(name = "k_turrets_destroyed_before_plates_fall")
    private int kTurretsDestroyedBeforePlatesFall;

    @Column(name = "kda")
    private double kda;

    @Column(name = "kill_after_hidden_with_ally")
    private int killAfterHiddenWithAlly;

    @Column(name = "kill_participation")
    private double killParticipation;

    @Column(name = "killed_champ_took_full_team_damage_survived")
    private int killedChampTookFullTeamDamageSurvived;

    @Column(name = "killing_sprees")
    private int killingSprees;

    @Column(name = "kills_near_enemy_turret")
    private int killsNearEnemyTurret;

    @Column(name = "kills_on_other_lanes_early_jungle_as_laner")
    private int killsOnOtherLanesEarlyJungleAsLaner;

    @Column(name = "kills_on_recently_healed_by_aram_pack")
    private int killsOnRecentlyHealedByAramPack;

    @Column(name = "kills_under_own_turret")
    private int killsUnderOwnTurret;

    @Column(name = "kills_with_help_from_epic_monster")
    private int killsWithHelpFromEpicMonster;

    @Column(name = "knock_enemy_into_team_and_kill")
    private int knockEnemyIntoTeamAndKill;

    @Column(name = "land_skill_shots_early_game")
    private int landSkillShotsEarlyGame;

    @Column(name = "lane_minions_first_10_minutes")
    private int laneMinionsFirst10Minutes;

    @Column(name = "laning_phase_gold_exp_advantage")
    private int laningPhaseGoldExpAdvantage;

    @Column(name = "legendary_count")
    private int legendaryCount;

    @Column(name = "lost_an_inhibitor")
    private int lostAnInhibitor;

    @Column(name = "max_cs_advantage_on_lane_opponent")
    private int maxCsAdvantageOnLaneOpponent;

    @Column(name = "max_kill_deficit")
    private int maxKillDeficit;

    @Column(name = "max_level_lead_lane_opponent")
    private int maxLevelLeadLaneOpponent;

    @Column(name = "mejais_full_stack_in_time")
    private int mejaisFullStackInTime;

    @Column(name = "more_enemy_jungle_than_opponent")
    private int moreEnemyJungleThanOpponent;

    @Column(name = "multi_kill_one_spell")
    private int multiKillOneSpell;

    @Column(name = "multi_turret_rift_herald_count")
    private int multiTurretRiftHeraldCount;

    @Column(name = "multikills")
    private int multikills;

    @Column(name = "multikills_after_aggressive_flash")
    private int multikillsAfterAggressiveFlash;

    @Column(name = "mythic_item_used")
    private int mythicItemUsed;

    @Column(name = "outer_turret_executes_before_10_minutes")
    private int outerTurretExecutesBefore10Minutes;

    @Column(name = "outnumbered_kills")
    private int outnumberedKills;

    @Column(name = "outnumbered_nexus_kill")
    private int outnumberedNexusKill;

    @Column(name = "perfect_dragon_souls_taken")
    private int perfectDragonSoulsTaken;

    @Column(name = "perfect_game")
    private int perfectGame;

    @Column(name = "pick_kill_with_ally")
    private int pickKillWithAlly;

    @Column(name = "poro_explosions")
    private int poroExplosions;

    @Column(name = "quick_cleanse")
    private int quickCleanse;

    @Column(name = "quick_first_turret")
    private int quickFirstTurret;

    @Column(name = "quick_solo_kills")
    private int quickSoloKills;

    @Column(name = "rift_herald_takedowns")
    private int riftHeraldTakedowns;

    @Column(name = "save_ally_from_death")
    private int saveAllyFromDeath;

    @Column(name = "scuttle_crab_kills")
    private int scuttleCrabKills;

    @Column(name = "shortest_time_to_ace_from_first_takedown")
    private double shortestTimeToAceFromFirstTakedown;

    @Column(name = "skillshots_dodged")
    private int skillshotsDodged;

    @Column(name = "skillshots_hit")
    private int skillshotsHit;

    @Column(name = "snowballs_hit")
    private int snowballsHit;

    @Column(name = "solo_baron_kills")
    private int soloBaronKills;

    @Column(name = "solo_kills")
    private int soloKills;

    @Column(name = "stealth_wards_placed")
    private int stealthWardsPlaced;

    @Column(name = "survived_single_digit_hp_count")
    private int survivedSingleDigitHpCount;

    @Column(name = "survived_three_immobilizes_in_fight")
    private int survivedThreeImmobilizesInFight;

    @Column(name = "takedown_on_first_turret")
    private int takedownOnFirstTurret;

    @Column(name = "takedowns")
    private int takedowns;

    @Column(name = "takedowns_after_gaining_level_advantage")
    private int takedownsAfterGainingLevelAdvantage;

    @Column(name = "takedowns_before_jungle_minion_spawn")
    private int takedownsBeforeJungleMinionSpawn;

    @Column(name = "takedowns_first_x_minutes")
    private int takedownsFirstXMinutes;

    @Column(name = "takedowns_in_alcove")
    private int takedownsInAlcove;

    @Column(name = "takedowns_in_enemy_fountain")
    private int takedownsInEnemyFountain;

    @Column(name = "team_baron_kills")
    private int teamBaronKills;

    @Column(name = "team_damage_percentage")
    private double teamDamagePercentage;

    @Column(name = "team_elder_dragon_kills")
    private int teamElderDragonKills;

    @Column(name = "team_rift_herald_kills")
    private int teamRiftHeraldKills;

    @Column(name = "took_large_damage_survived")
    private int tookLargeDamageSurvived;

    @Column(name = "turret_plates_taken")
    private int turretPlatesTaken;

    @Column(name = "turret_takedowns")
    private int turretTakedowns;

    @Column(name = "turrets_taken_with_rift_herald")
    private int turretsTakenWithRiftHerald;

    @Column(name = "twenty_minions_in_3_seconds_count")
    private int twentyMinionsIn3SecondsCount;

    @Column(name = "two_wards_one_sweeper_count")
    private int twoWardsOneSweeperCount;

    @Column(name = "unseen_recalls")
    private int unseenRecalls;

    @Column(name = "vision_score_advantage_lane_opponent")
    private int visionScoreAdvantageLaneOpponent;

    @Column(name = "vision_score_per_minute")
    private double visionScorePerMinute;

    @Column(name = "ward_takedowns")
    private int wardTakedowns;

    @Column(name = "ward_takedowns_before_20m")
    private int wardTakedownsBefore20M;

    @Column(name = "wards_guarded")
    private int wardsGuarded;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/

    public ParticipantChallenges() {
    }

    public ParticipantChallenges(int _12AssistStreakCount, int abilityUses, int acesBefore15Minutes, int alliedJungleMonsterKills, int baronTakedowns, int blastConeOppositeOpponentCount, int bountyGold, int buffsStolen, int completeSupportQuestInTime, int controlWardsPlaced, double damagePerMinute, double damageTakenOnTeamPercentage, int dancedWithRiftHerald, int deathsByEnemyChamps, int dodgeSkillShotsSmallWindow, int doubleAces, int dragonTakedowns, int earlyLaningPhaseGoldExpAdvantage, int effectiveHealAndShielding, int elderDragonKillsWithOpposingSoul, int elderDragonMultikills, int enemyChampionImmobilizations, int enemyJungleMonsterKills, int epicMonsterKillsNearEnemyJungler, int epicMonsterKillsWithin30SecondsOfSpawn, int epicMonsterSteals, int epicMonsterStolenWithoutSmite, int firstTurretKilled, double firstTurretKilledTime, int flawlessAces, int fullTeamTakedown, double gameLength, int getTakedownsInAllLanesEarlyJungleAsLaner, double goldPerMinute, int hadOpenNexus, int immobilizeAndKillWithAlly, int initialBuffCount, int initialCrabCount, int jungleCsBefore10Minutes, int junglerTakedownsNearDamagedEpicMonster, int kTurretsDestroyedBeforePlatesFall, double kda, int killAfterHiddenWithAlly, double killParticipation, int killedChampTookFullTeamDamageSurvived, int killingSprees, int killsNearEnemyTurret, int killsOnOtherLanesEarlyJungleAsLaner, int killsOnRecentlyHealedByAramPack, int killsUnderOwnTurret, int killsWithHelpFromEpicMonster, int knockEnemyIntoTeamAndKill, int landSkillShotsEarlyGame, int laneMinionsFirst10Minutes, int laningPhaseGoldExpAdvantage, int legendaryCount, int lostAnInhibitor, int maxCsAdvantageOnLaneOpponent, int maxKillDeficit, int maxLevelLeadLaneOpponent, int mejaisFullStackInTime, int moreEnemyJungleThanOpponent, int multiKillOneSpell, int multiTurretRiftHeraldCount, int multikills, int multikillsAfterAggressiveFlash, int mythicItemUsed, int outerTurretExecutesBefore10Minutes, int outnumberedKills, int outnumberedNexusKill, int perfectDragonSoulsTaken, int perfectGame, int pickKillWithAlly, int poroExplosions, int quickCleanse, int quickFirstTurret, int quickSoloKills, int riftHeraldTakedowns, int saveAllyFromDeath, int scuttleCrabKills, double shortestTimeToAceFromFirstTakedown, int skillshotsDodged, int skillshotsHit, int snowballsHit, int soloBaronKills, int soloKills, int stealthWardsPlaced, int survivedSingleDigitHpCount, int survivedThreeImmobilizesInFight, int takedownOnFirstTurret, int takedowns, int takedownsAfterGainingLevelAdvantage, int takedownsBeforeJungleMinionSpawn, int takedownsFirstXMinutes, int takedownsInAlcove, int takedownsInEnemyFountain, int teamBaronKills, double teamDamagePercentage, int teamElderDragonKills, int teamRiftHeraldKills, int tookLargeDamageSurvived, int turretPlatesTaken, int turretTakedowns, int turretsTakenWithRiftHerald, int twentyMinionsIn3SecondsCount, int twoWardsOneSweeperCount, int unseenRecalls, int visionScoreAdvantageLaneOpponent, double visionScorePerMinute, int wardTakedowns, int wardTakedownsBefore20M, int wardsGuarded, Participant participant) {
        this._12AssistStreakCount = _12AssistStreakCount;
        this.abilityUses = abilityUses;
        this.acesBefore15Minutes = acesBefore15Minutes;
        this.alliedJungleMonsterKills = alliedJungleMonsterKills;
        this.baronTakedowns = baronTakedowns;
        this.blastConeOppositeOpponentCount = blastConeOppositeOpponentCount;
        this.bountyGold = bountyGold;
        this.buffsStolen = buffsStolen;
        this.completeSupportQuestInTime = completeSupportQuestInTime;
        this.controlWardsPlaced = controlWardsPlaced;
        this.damagePerMinute = damagePerMinute;
        this.damageTakenOnTeamPercentage = damageTakenOnTeamPercentage;
        this.dancedWithRiftHerald = dancedWithRiftHerald;
        this.deathsByEnemyChamps = deathsByEnemyChamps;
        this.dodgeSkillShotsSmallWindow = dodgeSkillShotsSmallWindow;
        this.doubleAces = doubleAces;
        this.dragonTakedowns = dragonTakedowns;
        this.earlyLaningPhaseGoldExpAdvantage = earlyLaningPhaseGoldExpAdvantage;
        this.effectiveHealAndShielding = effectiveHealAndShielding;
        this.elderDragonKillsWithOpposingSoul = elderDragonKillsWithOpposingSoul;
        this.elderDragonMultikills = elderDragonMultikills;
        this.enemyChampionImmobilizations = enemyChampionImmobilizations;
        this.enemyJungleMonsterKills = enemyJungleMonsterKills;
        this.epicMonsterKillsNearEnemyJungler = epicMonsterKillsNearEnemyJungler;
        this.epicMonsterKillsWithin30SecondsOfSpawn = epicMonsterKillsWithin30SecondsOfSpawn;
        this.epicMonsterSteals = epicMonsterSteals;
        this.epicMonsterStolenWithoutSmite = epicMonsterStolenWithoutSmite;
        this.firstTurretKilled = firstTurretKilled;
        this.firstTurretKilledTime = firstTurretKilledTime;
        this.flawlessAces = flawlessAces;
        this.fullTeamTakedown = fullTeamTakedown;
        this.gameLength = gameLength;
        this.getTakedownsInAllLanesEarlyJungleAsLaner = getTakedownsInAllLanesEarlyJungleAsLaner;
        this.goldPerMinute = goldPerMinute;
        this.hadOpenNexus = hadOpenNexus;
        this.immobilizeAndKillWithAlly = immobilizeAndKillWithAlly;
        this.initialBuffCount = initialBuffCount;
        this.initialCrabCount = initialCrabCount;
        this.jungleCsBefore10Minutes = jungleCsBefore10Minutes;
        this.junglerTakedownsNearDamagedEpicMonster = junglerTakedownsNearDamagedEpicMonster;
        this.kTurretsDestroyedBeforePlatesFall = kTurretsDestroyedBeforePlatesFall;
        this.kda = kda;
        this.killAfterHiddenWithAlly = killAfterHiddenWithAlly;
        this.killParticipation = killParticipation;
        this.killedChampTookFullTeamDamageSurvived = killedChampTookFullTeamDamageSurvived;
        this.killingSprees = killingSprees;
        this.killsNearEnemyTurret = killsNearEnemyTurret;
        this.killsOnOtherLanesEarlyJungleAsLaner = killsOnOtherLanesEarlyJungleAsLaner;
        this.killsOnRecentlyHealedByAramPack = killsOnRecentlyHealedByAramPack;
        this.killsUnderOwnTurret = killsUnderOwnTurret;
        this.killsWithHelpFromEpicMonster = killsWithHelpFromEpicMonster;
        this.knockEnemyIntoTeamAndKill = knockEnemyIntoTeamAndKill;
        this.landSkillShotsEarlyGame = landSkillShotsEarlyGame;
        this.laneMinionsFirst10Minutes = laneMinionsFirst10Minutes;
        this.laningPhaseGoldExpAdvantage = laningPhaseGoldExpAdvantage;
        this.legendaryCount = legendaryCount;
        this.lostAnInhibitor = lostAnInhibitor;
        this.maxCsAdvantageOnLaneOpponent = maxCsAdvantageOnLaneOpponent;
        this.maxKillDeficit = maxKillDeficit;
        this.maxLevelLeadLaneOpponent = maxLevelLeadLaneOpponent;
        this.mejaisFullStackInTime = mejaisFullStackInTime;
        this.moreEnemyJungleThanOpponent = moreEnemyJungleThanOpponent;
        this.multiKillOneSpell = multiKillOneSpell;
        this.multiTurretRiftHeraldCount = multiTurretRiftHeraldCount;
        this.multikills = multikills;
        this.multikillsAfterAggressiveFlash = multikillsAfterAggressiveFlash;
        this.mythicItemUsed = mythicItemUsed;
        this.outerTurretExecutesBefore10Minutes = outerTurretExecutesBefore10Minutes;
        this.outnumberedKills = outnumberedKills;
        this.outnumberedNexusKill = outnumberedNexusKill;
        this.perfectDragonSoulsTaken = perfectDragonSoulsTaken;
        this.perfectGame = perfectGame;
        this.pickKillWithAlly = pickKillWithAlly;
        this.poroExplosions = poroExplosions;
        this.quickCleanse = quickCleanse;
        this.quickFirstTurret = quickFirstTurret;
        this.quickSoloKills = quickSoloKills;
        this.riftHeraldTakedowns = riftHeraldTakedowns;
        this.saveAllyFromDeath = saveAllyFromDeath;
        this.scuttleCrabKills = scuttleCrabKills;
        this.shortestTimeToAceFromFirstTakedown = shortestTimeToAceFromFirstTakedown;
        this.skillshotsDodged = skillshotsDodged;
        this.skillshotsHit = skillshotsHit;
        this.snowballsHit = snowballsHit;
        this.soloBaronKills = soloBaronKills;
        this.soloKills = soloKills;
        this.stealthWardsPlaced = stealthWardsPlaced;
        this.survivedSingleDigitHpCount = survivedSingleDigitHpCount;
        this.survivedThreeImmobilizesInFight = survivedThreeImmobilizesInFight;
        this.takedownOnFirstTurret = takedownOnFirstTurret;
        this.takedowns = takedowns;
        this.takedownsAfterGainingLevelAdvantage = takedownsAfterGainingLevelAdvantage;
        this.takedownsBeforeJungleMinionSpawn = takedownsBeforeJungleMinionSpawn;
        this.takedownsFirstXMinutes = takedownsFirstXMinutes;
        this.takedownsInAlcove = takedownsInAlcove;
        this.takedownsInEnemyFountain = takedownsInEnemyFountain;
        this.teamBaronKills = teamBaronKills;
        this.teamDamagePercentage = teamDamagePercentage;
        this.teamElderDragonKills = teamElderDragonKills;
        this.teamRiftHeraldKills = teamRiftHeraldKills;
        this.tookLargeDamageSurvived = tookLargeDamageSurvived;
        this.turretPlatesTaken = turretPlatesTaken;
        this.turretTakedowns = turretTakedowns;
        this.turretsTakenWithRiftHerald = turretsTakenWithRiftHerald;
        this.twentyMinionsIn3SecondsCount = twentyMinionsIn3SecondsCount;
        this.twoWardsOneSweeperCount = twoWardsOneSweeperCount;
        this.unseenRecalls = unseenRecalls;
        this.visionScoreAdvantageLaneOpponent = visionScoreAdvantageLaneOpponent;
        this.visionScorePerMinute = visionScorePerMinute;
        this.wardTakedowns = wardTakedowns;
        this.wardTakedownsBefore20M = wardTakedownsBefore20M;
        this.wardsGuarded = wardsGuarded;
        this.participant = participant;
    }

    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int get_12AssistStreakCount() {
        return _12AssistStreakCount;
    }

    public void set_12AssistStreakCount(int _12AssistStreakCount) {
        this._12AssistStreakCount = _12AssistStreakCount;
    }

    public int getAbilityUses() {
        return abilityUses;
    }

    public void setAbilityUses(int abilityUses) {
        this.abilityUses = abilityUses;
    }

    public int getAcesBefore15Minutes() {
        return acesBefore15Minutes;
    }

    public void setAcesBefore15Minutes(int acesBefore15Minutes) {
        this.acesBefore15Minutes = acesBefore15Minutes;
    }

    public int getAlliedJungleMonsterKills() {
        return alliedJungleMonsterKills;
    }

    public void setAlliedJungleMonsterKills(int alliedJungleMonsterKills) {
        this.alliedJungleMonsterKills = alliedJungleMonsterKills;
    }

    public int getBaronTakedowns() {
        return baronTakedowns;
    }

    public void setBaronTakedowns(int baronTakedowns) {
        this.baronTakedowns = baronTakedowns;
    }

    public int getBlastConeOppositeOpponentCount() {
        return blastConeOppositeOpponentCount;
    }

    public void setBlastConeOppositeOpponentCount(int blastConeOppositeOpponentCount) {
        this.blastConeOppositeOpponentCount = blastConeOppositeOpponentCount;
    }

    public int getBountyGold() {
        return bountyGold;
    }

    public void setBountyGold(int bountyGold) {
        this.bountyGold = bountyGold;
    }

    public int getBuffsStolen() {
        return buffsStolen;
    }

    public void setBuffsStolen(int buffsStolen) {
        this.buffsStolen = buffsStolen;
    }

    public int getCompleteSupportQuestInTime() {
        return completeSupportQuestInTime;
    }

    public void setCompleteSupportQuestInTime(int completeSupportQuestInTime) {
        this.completeSupportQuestInTime = completeSupportQuestInTime;
    }

    public int getControlWardsPlaced() {
        return controlWardsPlaced;
    }

    public void setControlWardsPlaced(int controlWardsPlaced) {
        this.controlWardsPlaced = controlWardsPlaced;
    }

    public double getDamagePerMinute() {
        return damagePerMinute;
    }

    public void setDamagePerMinute(double damagePerMinute) {
        this.damagePerMinute = damagePerMinute;
    }

    public double getDamageTakenOnTeamPercentage() {
        return damageTakenOnTeamPercentage;
    }

    public void setDamageTakenOnTeamPercentage(double damageTakenOnTeamPercentage) {
        this.damageTakenOnTeamPercentage = damageTakenOnTeamPercentage;
    }

    public int getDancedWithRiftHerald() {
        return dancedWithRiftHerald;
    }

    public void setDancedWithRiftHerald(int dancedWithRiftHerald) {
        this.dancedWithRiftHerald = dancedWithRiftHerald;
    }

    public int getDeathsByEnemyChamps() {
        return deathsByEnemyChamps;
    }

    public void setDeathsByEnemyChamps(int deathsByEnemyChamps) {
        this.deathsByEnemyChamps = deathsByEnemyChamps;
    }

    public int getDodgeSkillShotsSmallWindow() {
        return dodgeSkillShotsSmallWindow;
    }

    public void setDodgeSkillShotsSmallWindow(int dodgeSkillShotsSmallWindow) {
        this.dodgeSkillShotsSmallWindow = dodgeSkillShotsSmallWindow;
    }

    public int getDoubleAces() {
        return doubleAces;
    }

    public void setDoubleAces(int doubleAces) {
        this.doubleAces = doubleAces;
    }

    public int getDragonTakedowns() {
        return dragonTakedowns;
    }

    public void setDragonTakedowns(int dragonTakedowns) {
        this.dragonTakedowns = dragonTakedowns;
    }

    public int getEarlyLaningPhaseGoldExpAdvantage() {
        return earlyLaningPhaseGoldExpAdvantage;
    }

    public void setEarlyLaningPhaseGoldExpAdvantage(int earlyLaningPhaseGoldExpAdvantage) {
        this.earlyLaningPhaseGoldExpAdvantage = earlyLaningPhaseGoldExpAdvantage;
    }

    public int getEffectiveHealAndShielding() {
        return effectiveHealAndShielding;
    }

    public void setEffectiveHealAndShielding(int effectiveHealAndShielding) {
        this.effectiveHealAndShielding = effectiveHealAndShielding;
    }

    public int getElderDragonKillsWithOpposingSoul() {
        return elderDragonKillsWithOpposingSoul;
    }

    public void setElderDragonKillsWithOpposingSoul(int elderDragonKillsWithOpposingSoul) {
        this.elderDragonKillsWithOpposingSoul = elderDragonKillsWithOpposingSoul;
    }

    public int getElderDragonMultikills() {
        return elderDragonMultikills;
    }

    public void setElderDragonMultikills(int elderDragonMultikills) {
        this.elderDragonMultikills = elderDragonMultikills;
    }

    public int getEnemyChampionImmobilizations() {
        return enemyChampionImmobilizations;
    }

    public void setEnemyChampionImmobilizations(int enemyChampionImmobilizations) {
        this.enemyChampionImmobilizations = enemyChampionImmobilizations;
    }

    public int getEnemyJungleMonsterKills() {
        return enemyJungleMonsterKills;
    }

    public void setEnemyJungleMonsterKills(int enemyJungleMonsterKills) {
        this.enemyJungleMonsterKills = enemyJungleMonsterKills;
    }

    public int getEpicMonsterKillsNearEnemyJungler() {
        return epicMonsterKillsNearEnemyJungler;
    }

    public void setEpicMonsterKillsNearEnemyJungler(int epicMonsterKillsNearEnemyJungler) {
        this.epicMonsterKillsNearEnemyJungler = epicMonsterKillsNearEnemyJungler;
    }

    public int getEpicMonsterKillsWithin30SecondsOfSpawn() {
        return epicMonsterKillsWithin30SecondsOfSpawn;
    }

    public void setEpicMonsterKillsWithin30SecondsOfSpawn(int epicMonsterKillsWithin30SecondsOfSpawn) {
        this.epicMonsterKillsWithin30SecondsOfSpawn = epicMonsterKillsWithin30SecondsOfSpawn;
    }

    public int getEpicMonsterSteals() {
        return epicMonsterSteals;
    }

    public void setEpicMonsterSteals(int epicMonsterSteals) {
        this.epicMonsterSteals = epicMonsterSteals;
    }

    public int getEpicMonsterStolenWithoutSmite() {
        return epicMonsterStolenWithoutSmite;
    }

    public void setEpicMonsterStolenWithoutSmite(int epicMonsterStolenWithoutSmite) {
        this.epicMonsterStolenWithoutSmite = epicMonsterStolenWithoutSmite;
    }

    public int getFirstTurretKilled() {
        return firstTurretKilled;
    }

    public void setFirstTurretKilled(int firstTurretKilled) {
        this.firstTurretKilled = firstTurretKilled;
    }

    public double getFirstTurretKilledTime() {
        return firstTurretKilledTime;
    }

    public void setFirstTurretKilledTime(double firstTurretKilledTime) {
        this.firstTurretKilledTime = firstTurretKilledTime;
    }

    public int getFlawlessAces() {
        return flawlessAces;
    }

    public void setFlawlessAces(int flawlessAces) {
        this.flawlessAces = flawlessAces;
    }

    public int getFullTeamTakedown() {
        return fullTeamTakedown;
    }

    public void setFullTeamTakedown(int fullTeamTakedown) {
        this.fullTeamTakedown = fullTeamTakedown;
    }

    public double getGameLength() {
        return gameLength;
    }

    public void setGameLength(double gameLength) {
        this.gameLength = gameLength;
    }

    public int getGetTakedownsInAllLanesEarlyJungleAsLaner() {
        return getTakedownsInAllLanesEarlyJungleAsLaner;
    }

    public void setGetTakedownsInAllLanesEarlyJungleAsLaner(int getTakedownsInAllLanesEarlyJungleAsLaner) {
        this.getTakedownsInAllLanesEarlyJungleAsLaner = getTakedownsInAllLanesEarlyJungleAsLaner;
    }

    public double getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(double goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }

    public int getHadOpenNexus() {
        return hadOpenNexus;
    }

    public void setHadOpenNexus(int hadOpenNexus) {
        this.hadOpenNexus = hadOpenNexus;
    }

    public int getImmobilizeAndKillWithAlly() {
        return immobilizeAndKillWithAlly;
    }

    public void setImmobilizeAndKillWithAlly(int immobilizeAndKillWithAlly) {
        this.immobilizeAndKillWithAlly = immobilizeAndKillWithAlly;
    }

    public int getInitialBuffCount() {
        return initialBuffCount;
    }

    public void setInitialBuffCount(int initialBuffCount) {
        this.initialBuffCount = initialBuffCount;
    }

    public int getInitialCrabCount() {
        return initialCrabCount;
    }

    public void setInitialCrabCount(int initialCrabCount) {
        this.initialCrabCount = initialCrabCount;
    }

    public int getJungleCsBefore10Minutes() {
        return jungleCsBefore10Minutes;
    }

    public void setJungleCsBefore10Minutes(int jungleCsBefore10Minutes) {
        this.jungleCsBefore10Minutes = jungleCsBefore10Minutes;
    }

    public int getJunglerTakedownsNearDamagedEpicMonster() {
        return junglerTakedownsNearDamagedEpicMonster;
    }

    public void setJunglerTakedownsNearDamagedEpicMonster(int junglerTakedownsNearDamagedEpicMonster) {
        this.junglerTakedownsNearDamagedEpicMonster = junglerTakedownsNearDamagedEpicMonster;
    }

    public int getkTurretsDestroyedBeforePlatesFall() {
        return kTurretsDestroyedBeforePlatesFall;
    }

    public void setkTurretsDestroyedBeforePlatesFall(int kTurretsDestroyedBeforePlatesFall) {
        this.kTurretsDestroyedBeforePlatesFall = kTurretsDestroyedBeforePlatesFall;
    }

    public double getKda() {
        return kda;
    }

    public void setKda(double kda) {
        this.kda = kda;
    }

    public int getKillAfterHiddenWithAlly() {
        return killAfterHiddenWithAlly;
    }

    public void setKillAfterHiddenWithAlly(int killAfterHiddenWithAlly) {
        this.killAfterHiddenWithAlly = killAfterHiddenWithAlly;
    }

    public double getKillParticipation() {
        return killParticipation;
    }

    public void setKillParticipation(double killParticipation) {
        this.killParticipation = killParticipation;
    }

    public int getKilledChampTookFullTeamDamageSurvived() {
        return killedChampTookFullTeamDamageSurvived;
    }

    public void setKilledChampTookFullTeamDamageSurvived(int killedChampTookFullTeamDamageSurvived) {
        this.killedChampTookFullTeamDamageSurvived = killedChampTookFullTeamDamageSurvived;
    }

    public int getKillingSprees() {
        return killingSprees;
    }

    public void setKillingSprees(int killingSprees) {
        this.killingSprees = killingSprees;
    }

    public int getKillsNearEnemyTurret() {
        return killsNearEnemyTurret;
    }

    public void setKillsNearEnemyTurret(int killsNearEnemyTurret) {
        this.killsNearEnemyTurret = killsNearEnemyTurret;
    }

    public int getKillsOnOtherLanesEarlyJungleAsLaner() {
        return killsOnOtherLanesEarlyJungleAsLaner;
    }

    public void setKillsOnOtherLanesEarlyJungleAsLaner(int killsOnOtherLanesEarlyJungleAsLaner) {
        this.killsOnOtherLanesEarlyJungleAsLaner = killsOnOtherLanesEarlyJungleAsLaner;
    }

    public int getKillsOnRecentlyHealedByAramPack() {
        return killsOnRecentlyHealedByAramPack;
    }

    public void setKillsOnRecentlyHealedByAramPack(int killsOnRecentlyHealedByAramPack) {
        this.killsOnRecentlyHealedByAramPack = killsOnRecentlyHealedByAramPack;
    }

    public int getKillsUnderOwnTurret() {
        return killsUnderOwnTurret;
    }

    public void setKillsUnderOwnTurret(int killsUnderOwnTurret) {
        this.killsUnderOwnTurret = killsUnderOwnTurret;
    }

    public int getKillsWithHelpFromEpicMonster() {
        return killsWithHelpFromEpicMonster;
    }

    public void setKillsWithHelpFromEpicMonster(int killsWithHelpFromEpicMonster) {
        this.killsWithHelpFromEpicMonster = killsWithHelpFromEpicMonster;
    }

    public int getKnockEnemyIntoTeamAndKill() {
        return knockEnemyIntoTeamAndKill;
    }

    public void setKnockEnemyIntoTeamAndKill(int knockEnemyIntoTeamAndKill) {
        this.knockEnemyIntoTeamAndKill = knockEnemyIntoTeamAndKill;
    }

    public int getLandSkillShotsEarlyGame() {
        return landSkillShotsEarlyGame;
    }

    public void setLandSkillShotsEarlyGame(int landSkillShotsEarlyGame) {
        this.landSkillShotsEarlyGame = landSkillShotsEarlyGame;
    }

    public int getLaneMinionsFirst10Minutes() {
        return laneMinionsFirst10Minutes;
    }

    public void setLaneMinionsFirst10Minutes(int laneMinionsFirst10Minutes) {
        this.laneMinionsFirst10Minutes = laneMinionsFirst10Minutes;
    }

    public int getLaningPhaseGoldExpAdvantage() {
        return laningPhaseGoldExpAdvantage;
    }

    public void setLaningPhaseGoldExpAdvantage(int laningPhaseGoldExpAdvantage) {
        this.laningPhaseGoldExpAdvantage = laningPhaseGoldExpAdvantage;
    }

    public int getLegendaryCount() {
        return legendaryCount;
    }

    public void setLegendaryCount(int legendaryCount) {
        this.legendaryCount = legendaryCount;
    }

    public int getLostAnInhibitor() {
        return lostAnInhibitor;
    }

    public void setLostAnInhibitor(int lostAnInhibitor) {
        this.lostAnInhibitor = lostAnInhibitor;
    }

    public int getMaxCsAdvantageOnLaneOpponent() {
        return maxCsAdvantageOnLaneOpponent;
    }

    public void setMaxCsAdvantageOnLaneOpponent(int maxCsAdvantageOnLaneOpponent) {
        this.maxCsAdvantageOnLaneOpponent = maxCsAdvantageOnLaneOpponent;
    }

    public int getMaxKillDeficit() {
        return maxKillDeficit;
    }

    public void setMaxKillDeficit(int maxKillDeficit) {
        this.maxKillDeficit = maxKillDeficit;
    }

    public int getMaxLevelLeadLaneOpponent() {
        return maxLevelLeadLaneOpponent;
    }

    public void setMaxLevelLeadLaneOpponent(int maxLevelLeadLaneOpponent) {
        this.maxLevelLeadLaneOpponent = maxLevelLeadLaneOpponent;
    }

    public int getMejaisFullStackInTime() {
        return mejaisFullStackInTime;
    }

    public void setMejaisFullStackInTime(int mejaisFullStackInTime) {
        this.mejaisFullStackInTime = mejaisFullStackInTime;
    }

    public int getMoreEnemyJungleThanOpponent() {
        return moreEnemyJungleThanOpponent;
    }

    public void setMoreEnemyJungleThanOpponent(int moreEnemyJungleThanOpponent) {
        this.moreEnemyJungleThanOpponent = moreEnemyJungleThanOpponent;
    }

    public int getMultiKillOneSpell() {
        return multiKillOneSpell;
    }

    public void setMultiKillOneSpell(int multiKillOneSpell) {
        this.multiKillOneSpell = multiKillOneSpell;
    }

    public int getMultiTurretRiftHeraldCount() {
        return multiTurretRiftHeraldCount;
    }

    public void setMultiTurretRiftHeraldCount(int multiTurretRiftHeraldCount) {
        this.multiTurretRiftHeraldCount = multiTurretRiftHeraldCount;
    }

    public int getMultikills() {
        return multikills;
    }

    public void setMultikills(int multikills) {
        this.multikills = multikills;
    }

    public int getMultikillsAfterAggressiveFlash() {
        return multikillsAfterAggressiveFlash;
    }

    public void setMultikillsAfterAggressiveFlash(int multikillsAfterAggressiveFlash) {
        this.multikillsAfterAggressiveFlash = multikillsAfterAggressiveFlash;
    }

    public int getMythicItemUsed() {
        return mythicItemUsed;
    }

    public void setMythicItemUsed(int mythicItemUsed) {
        this.mythicItemUsed = mythicItemUsed;
    }

    public int getOuterTurretExecutesBefore10Minutes() {
        return outerTurretExecutesBefore10Minutes;
    }

    public void setOuterTurretExecutesBefore10Minutes(int outerTurretExecutesBefore10Minutes) {
        this.outerTurretExecutesBefore10Minutes = outerTurretExecutesBefore10Minutes;
    }

    public int getOutnumberedKills() {
        return outnumberedKills;
    }

    public void setOutnumberedKills(int outnumberedKills) {
        this.outnumberedKills = outnumberedKills;
    }

    public int getOutnumberedNexusKill() {
        return outnumberedNexusKill;
    }

    public void setOutnumberedNexusKill(int outnumberedNexusKill) {
        this.outnumberedNexusKill = outnumberedNexusKill;
    }

    public int getPerfectDragonSoulsTaken() {
        return perfectDragonSoulsTaken;
    }

    public void setPerfectDragonSoulsTaken(int perfectDragonSoulsTaken) {
        this.perfectDragonSoulsTaken = perfectDragonSoulsTaken;
    }

    public int getPerfectGame() {
        return perfectGame;
    }

    public void setPerfectGame(int perfectGame) {
        this.perfectGame = perfectGame;
    }

    public int getPickKillWithAlly() {
        return pickKillWithAlly;
    }

    public void setPickKillWithAlly(int pickKillWithAlly) {
        this.pickKillWithAlly = pickKillWithAlly;
    }

    public int getPoroExplosions() {
        return poroExplosions;
    }

    public void setPoroExplosions(int poroExplosions) {
        this.poroExplosions = poroExplosions;
    }

    public int getQuickCleanse() {
        return quickCleanse;
    }

    public void setQuickCleanse(int quickCleanse) {
        this.quickCleanse = quickCleanse;
    }

    public int getQuickFirstTurret() {
        return quickFirstTurret;
    }

    public void setQuickFirstTurret(int quickFirstTurret) {
        this.quickFirstTurret = quickFirstTurret;
    }

    public int getQuickSoloKills() {
        return quickSoloKills;
    }

    public void setQuickSoloKills(int quickSoloKills) {
        this.quickSoloKills = quickSoloKills;
    }

    public int getRiftHeraldTakedowns() {
        return riftHeraldTakedowns;
    }

    public void setRiftHeraldTakedowns(int riftHeraldTakedowns) {
        this.riftHeraldTakedowns = riftHeraldTakedowns;
    }

    public int getSaveAllyFromDeath() {
        return saveAllyFromDeath;
    }

    public void setSaveAllyFromDeath(int saveAllyFromDeath) {
        this.saveAllyFromDeath = saveAllyFromDeath;
    }

    public int getScuttleCrabKills() {
        return scuttleCrabKills;
    }

    public void setScuttleCrabKills(int scuttleCrabKills) {
        this.scuttleCrabKills = scuttleCrabKills;
    }

    public double getShortestTimeToAceFromFirstTakedown() {
        return shortestTimeToAceFromFirstTakedown;
    }

    public void setShortestTimeToAceFromFirstTakedown(double shortestTimeToAceFromFirstTakedown) {
        this.shortestTimeToAceFromFirstTakedown = shortestTimeToAceFromFirstTakedown;
    }

    public int getSkillshotsDodged() {
        return skillshotsDodged;
    }

    public void setSkillshotsDodged(int skillshotsDodged) {
        this.skillshotsDodged = skillshotsDodged;
    }

    public int getSkillshotsHit() {
        return skillshotsHit;
    }

    public void setSkillshotsHit(int skillshotsHit) {
        this.skillshotsHit = skillshotsHit;
    }

    public int getSnowballsHit() {
        return snowballsHit;
    }

    public void setSnowballsHit(int snowballsHit) {
        this.snowballsHit = snowballsHit;
    }

    public int getSoloBaronKills() {
        return soloBaronKills;
    }

    public void setSoloBaronKills(int soloBaronKills) {
        this.soloBaronKills = soloBaronKills;
    }

    public int getSoloKills() {
        return soloKills;
    }

    public void setSoloKills(int soloKills) {
        this.soloKills = soloKills;
    }

    public int getStealthWardsPlaced() {
        return stealthWardsPlaced;
    }

    public void setStealthWardsPlaced(int stealthWardsPlaced) {
        this.stealthWardsPlaced = stealthWardsPlaced;
    }

    public int getSurvivedSingleDigitHpCount() {
        return survivedSingleDigitHpCount;
    }

    public void setSurvivedSingleDigitHpCount(int survivedSingleDigitHpCount) {
        this.survivedSingleDigitHpCount = survivedSingleDigitHpCount;
    }

    public int getSurvivedThreeImmobilizesInFight() {
        return survivedThreeImmobilizesInFight;
    }

    public void setSurvivedThreeImmobilizesInFight(int survivedThreeImmobilizesInFight) {
        this.survivedThreeImmobilizesInFight = survivedThreeImmobilizesInFight;
    }

    public int getTakedownOnFirstTurret() {
        return takedownOnFirstTurret;
    }

    public void setTakedownOnFirstTurret(int takedownOnFirstTurret) {
        this.takedownOnFirstTurret = takedownOnFirstTurret;
    }

    public int getTakedowns() {
        return takedowns;
    }

    public void setTakedowns(int takedowns) {
        this.takedowns = takedowns;
    }

    public int getTakedownsAfterGainingLevelAdvantage() {
        return takedownsAfterGainingLevelAdvantage;
    }

    public void setTakedownsAfterGainingLevelAdvantage(int takedownsAfterGainingLevelAdvantage) {
        this.takedownsAfterGainingLevelAdvantage = takedownsAfterGainingLevelAdvantage;
    }

    public int getTakedownsBeforeJungleMinionSpawn() {
        return takedownsBeforeJungleMinionSpawn;
    }

    public void setTakedownsBeforeJungleMinionSpawn(int takedownsBeforeJungleMinionSpawn) {
        this.takedownsBeforeJungleMinionSpawn = takedownsBeforeJungleMinionSpawn;
    }

    public int getTakedownsFirstXMinutes() {
        return takedownsFirstXMinutes;
    }

    public void setTakedownsFirstXMinutes(int takedownsFirstXMinutes) {
        this.takedownsFirstXMinutes = takedownsFirstXMinutes;
    }

    public int getTakedownsInAlcove() {
        return takedownsInAlcove;
    }

    public void setTakedownsInAlcove(int takedownsInAlcove) {
        this.takedownsInAlcove = takedownsInAlcove;
    }

    public int getTakedownsInEnemyFountain() {
        return takedownsInEnemyFountain;
    }

    public void setTakedownsInEnemyFountain(int takedownsInEnemyFountain) {
        this.takedownsInEnemyFountain = takedownsInEnemyFountain;
    }

    public int getTeamBaronKills() {
        return teamBaronKills;
    }

    public void setTeamBaronKills(int teamBaronKills) {
        this.teamBaronKills = teamBaronKills;
    }

    public double getTeamDamagePercentage() {
        return teamDamagePercentage;
    }

    public void setTeamDamagePercentage(double teamDamagePercentage) {
        this.teamDamagePercentage = teamDamagePercentage;
    }

    public int getTeamElderDragonKills() {
        return teamElderDragonKills;
    }

    public void setTeamElderDragonKills(int teamElderDragonKills) {
        this.teamElderDragonKills = teamElderDragonKills;
    }

    public int getTeamRiftHeraldKills() {
        return teamRiftHeraldKills;
    }

    public void setTeamRiftHeraldKills(int teamRiftHeraldKills) {
        this.teamRiftHeraldKills = teamRiftHeraldKills;
    }

    public int getTookLargeDamageSurvived() {
        return tookLargeDamageSurvived;
    }

    public void setTookLargeDamageSurvived(int tookLargeDamageSurvived) {
        this.tookLargeDamageSurvived = tookLargeDamageSurvived;
    }

    public int getTurretPlatesTaken() {
        return turretPlatesTaken;
    }

    public void setTurretPlatesTaken(int turretPlatesTaken) {
        this.turretPlatesTaken = turretPlatesTaken;
    }

    public int getTurretTakedowns() {
        return turretTakedowns;
    }

    public void setTurretTakedowns(int turretTakedowns) {
        this.turretTakedowns = turretTakedowns;
    }

    public int getTurretsTakenWithRiftHerald() {
        return turretsTakenWithRiftHerald;
    }

    public void setTurretsTakenWithRiftHerald(int turretsTakenWithRiftHerald) {
        this.turretsTakenWithRiftHerald = turretsTakenWithRiftHerald;
    }

    public int getTwentyMinionsIn3SecondsCount() {
        return twentyMinionsIn3SecondsCount;
    }

    public void setTwentyMinionsIn3SecondsCount(int twentyMinionsIn3SecondsCount) {
        this.twentyMinionsIn3SecondsCount = twentyMinionsIn3SecondsCount;
    }

    public int getTwoWardsOneSweeperCount() {
        return twoWardsOneSweeperCount;
    }

    public void setTwoWardsOneSweeperCount(int twoWardsOneSweeperCount) {
        this.twoWardsOneSweeperCount = twoWardsOneSweeperCount;
    }

    public int getUnseenRecalls() {
        return unseenRecalls;
    }

    public void setUnseenRecalls(int unseenRecalls) {
        this.unseenRecalls = unseenRecalls;
    }

    public int getVisionScoreAdvantageLaneOpponent() {
        return visionScoreAdvantageLaneOpponent;
    }

    public void setVisionScoreAdvantageLaneOpponent(int visionScoreAdvantageLaneOpponent) {
        this.visionScoreAdvantageLaneOpponent = visionScoreAdvantageLaneOpponent;
    }

    public double getVisionScorePerMinute() {
        return visionScorePerMinute;
    }

    public void setVisionScorePerMinute(double visionScorePerMinute) {
        this.visionScorePerMinute = visionScorePerMinute;
    }

    public int getWardTakedowns() {
        return wardTakedowns;
    }

    public void setWardTakedowns(int wardTakedowns) {
        this.wardTakedowns = wardTakedowns;
    }

    public int getWardTakedownsBefore20M() {
        return wardTakedownsBefore20M;
    }

    public void setWardTakedownsBefore20M(int wardTakedownsBefore20M) {
        this.wardTakedownsBefore20M = wardTakedownsBefore20M;
    }

    public int getWardsGuarded() {
        return wardsGuarded;
    }

    public void setWardsGuarded(int wardsGuarded) {
        this.wardsGuarded = wardsGuarded;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
