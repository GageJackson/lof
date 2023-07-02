package com.gagejackson.lof.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "assists")
    private	int	assists;

    @Column (name = "baron_kills")
    private	int	baronKills;

    @Column (name = "bounty_level")
    private	int	bountyLevel;

    @Column (name = "champ_experience")
    private	int	champExperience;

    @Column (name = "champ_level")
    private	int	champLevel;

    @Column (name = "champion_id")
    private	int	championId;

    @Column (name = "champion_name")
    private	String	championName;

    @Column (name = "champion_transform")
    private	int	championTransform;

    @Column (name = "consumables_purchased")
    private	int	consumablesPurchased;

    @Column (name = "damage_dealt_to_buildings")
    private	int	damageDealtToBuildings;

    @Column (name = "damage_dealt_to_objectives")
    private	int	damageDealtToObjectives;

    @Column (name = "damage_dealt_to_turrets")
    private	int	damageDealtToTurrets;

    @Column(name = "damage_self_mitigated")
    private int damageSelfMitigated;

    @Column(name = "deaths")
    private int deaths;

    @Column(name = "detector_wards_placed")
    private int detectorWardsPlaced;

    @Column(name = "double_kills")
    private int doubleKills;

    @Column(name = "dragon_kills")
    private int dragonKills;

    @Column(name = "first_blood_assist")
    private boolean firstBloodAssist;

    @Column(name = "first_blood_kill")
    private boolean firstBloodKill;

    @Column(name = "first_tower_assist")
    private boolean firstTowerAssist;

    @Column(name = "first_tower_kill")
    private boolean firstTowerKill;

    @Column(name = "game_ended_in_early_surrender")
    private boolean gameEndedInEarlySurrender;

    @Column(name = "game_ended_in_surrender")
    private boolean gameEndedInSurrender;

    @Column(name = "gold_earned")
    private int goldEarned;

    @Column(name = "gold_spent")
    private int goldSpent;

    @Column(name = "individual_position")
    private String individualPosition;

    @Column(name = "inhibitor_kills")
    private int inhibitorKills;

    @Column(name = "inhibitor_takedowns")
    private int inhibitorTakedowns;

    @Column(name = "inhibitors_lost")
    private int inhibitorsLost;

    @Column(name = "item0")
    private int item0;

    @Column(name = "item1")
    private int item1;

    @Column(name = "item2")
    private int item2;

    @Column(name = "item3")
    private int item3;

    @Column(name = "item4")
    private int item4;

    @Column(name = "item5")
    private int item5;

    @Column(name = "item6")
    private int item6;

    @Column(name = "items_purchased")
    private int itemsPurchased;

    @Column(name = "killing_sprees")
    private int killingSprees;

    @Column(name = "kills")
    private int kills;

    @Column(name = "lane")
    private String lane;

    @Column(name = "largest_critical_strike")
    private int largestCriticalStrike;

    @Column(name = "largest_killing_spree")
    private int largestKillingSpree;

    @Column(name = "largest_multi_kill")
    private int largestMultiKill;

    @Column(name = "longest_time_spent_living")
    private int longestTimeSpentLiving;

    @Column(name = "magic_damage_dealt")
    private int magicDamageDealt;

    @Column(name = "magic_damage_dealt_to_champions")
    private int magicDamageDealtToChampions;

    @Column(name = "magic_damage_taken")
    private int magicDamageTaken;

    @Column(name = "neutral_minions_killed")
    private int neutralMinionsKilled;

    @Column(name = "nexus_kills")
    private int nexusKills;

    @Column(name = "nexus_takedowns")
    private int nexusTakedowns;

    @Column(name = "nexus_lost")
    private int nexusLost;

    @Column(name = "objectives_stolen")
    private int objectivesStolen;

    @Column(name = "objectives_stolen_assists")
    private int objectivesStolenAssists;

    @Column(name = "participant_id")
    private int participantId;

    @Column(name = "penta_kills")
    private int pentaKills;

    @Column(name = "physical_damage_dealt")
    private int physicalDamageDealt;

    @Column(name = "physical_damage_dealt_to_champions")
    private int physicalDamageDealtToChampions;

    @Column(name = "physical_damage_taken")
    private int physicalDamageTaken;

    @Column(name = "profile_icon")
    private int profileIcon;

    @Column(name = "puuid")
    private String puuid;

    @Column(name = "quadra_kills")
    private int quadraKills;

    @Column(name = "riot_id_name")
    private String riotIdName;

    @Column(name = "riot_id_tagline")
    private String riotIdTagline;

    @Column(name = "role")
    private String role;

    @Column(name = "sight_wards_bought_in_game")
    private int sightWardsBoughtInGame;

    @Column(name = "spell1_casts")
    private int spell1Casts;

    @Column(name = "spell2_casts")
    private int spell2Casts;

    @Column(name = "spell3_casts")
    private int spell3Casts;

    @Column(name = "spell4_casts")
    private int spell4Casts;

    @Column(name = "summoner1_casts")
    private int summoner1Casts;

    @Column(name = "summoner1_id")
    private int summoner1Id;

    @Column(name = "summoner2_casts")
    private int summoner2Casts;

    @Column(name = "summoner2_id")
    private int summoner2Id;

    @Column(name = "summoner_id")
    private String summonerId;

    @Column(name = "summoner_level")
    private int summonerLevel;

    @Column(name = "summoner_name")
    private String summonerName;

    @Column(name = "team_early_surrendered")
    private boolean teamEarlySurrendered;

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_position")
    private String teamPosition;

    @Column(name = "time_ccing_others")
    private int timeCCingOthers;

    @Column(name = "time_played")
    private int timePlayed;

    @Column(name = "total_damage_dealt")
    private int totalDamageDealt;

    @Column(name = "total_damage_dealt_to_champions")
    private int totalDamageDealtToChampions;

    @Column(name = "total_damage_shielded_on_teammates")
    private int totalDamageShieldedOnTeammates;

    @Column(name = "total_damage_taken")
    private int totalDamageTaken;

    @Column(name = "total_heal")
    private int totalHeal;

    @Column(name = "total_heals_on_teammates")
    private int totalHealsOnTeammates;

    @Column(name = "total_minions_killed")
    private int totalMinionsKilled;

    @Column(name = "total_time_cc_dealt")
    private int totalTimeCCDealt;

    @Column(name = "total_time_spent_dead")
    private int totalTimeSpentDead;

    @Column(name = "total_units_healed")
    private int totalUnitsHealed;

    @Column(name = "triple_kills")
    private int tripleKills;

    @Column(name = "true_damage_dealt")
    private int trueDamageDealt;

    @Column(name = "true_damage_dealt_to_champions")
    private int trueDamageDealtToChampions;

    @Column(name = "true_damage_taken")
    private int trueDamageTaken;

    @Column(name = "turret_kills")
    private int turretKills;

    @Column(name = "turret_takedowns")
    private int turretTakedowns;

    @Column(name = "turrets_lost")
    private int turretsLost;

    @Column(name = "unreal_kills")
    private int unrealKills;

    @Column(name = "vision_score")
    private int visionScore;

    @Column(name = "vision_wards_bought_in_game")
    private int visionWardsBoughtInGame;

    @Column(name = "wards_killed")
    private int wardsKilled;

    @Column(name = "wards_placed")
    private int wardsPlaced;

    @Column(name = "win")
    private boolean win;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "participant")
    private List<Perk> perk;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Participant(){}

    public Participant(long id, int assists, int baronKills, int bountyLevel, int champExperience, int champLevel, int championId, String championName, int championTransform, int consumablesPurchased, int damageDealtToBuildings, int damageDealtToObjectives, int damageDealtToTurrets, int damageSelfMitigated, int deaths, int detectorWardsPlaced, int doubleKills, int dragonKills, boolean firstBloodAssist, boolean firstBloodKill, boolean firstTowerAssist, boolean firstTowerKill, boolean gameEndedInEarlySurrender, boolean gameEndedInSurrender, int goldEarned, int goldSpent, String individualPosition, int inhibitorKills, int inhibitorTakedowns, int inhibitorsLost, int item0, int item1, int item2, int item3, int item4, int item5, int item6, int itemsPurchased, int killingSprees, int kills, String lane, int largestCriticalStrike, int largestKillingSpree, int largestMultiKill, int longestTimeSpentLiving, int magicDamageDealt, int magicDamageDealtToChampions, int magicDamageTaken, int neutralMinionsKilled, int nexusKills, int nexusTakedowns, int nexusLost, int objectivesStolen, int objectivesStolenAssists, int participantId, int pentaKills, int physicalDamageDealt, int physicalDamageDealtToChampions, int physicalDamageTaken, int profileIcon, String puuid, int quadraKills, String riotIdName, String riotIdTagline, String role, int sightWardsBoughtInGame, int spell1Casts, int spell2Casts, int spell3Casts, int spell4Casts, int summoner1Casts, int summoner1Id, int summoner2Casts, int summoner2Id, String summonerId, int summonerLevel, String summonerName, boolean teamEarlySurrendered, int teamId, String teamPosition, int timeCCingOthers, int timePlayed, int totalDamageDealt, int totalDamageDealtToChampions, int totalDamageShieldedOnTeammates, int totalDamageTaken, int totalHeal, int totalHealsOnTeammates, int totalMinionsKilled, int totalTimeCCDealt, int totalTimeSpentDead, int totalUnitsHealed, int tripleKills, int trueDamageDealt, int trueDamageDealtToChampions, int trueDamageTaken, int turretKills, int turretTakedowns, int turretsLost, int unrealKills, int visionScore, int visionWardsBoughtInGame, int wardsKilled, int wardsPlaced, boolean win, List<Perk> perk, Match match) {
        this.id = id;
        this.assists = assists;
        this.baronKills = baronKills;
        this.bountyLevel = bountyLevel;
        this.champExperience = champExperience;
        this.champLevel = champLevel;
        this.championId = championId;
        this.championName = championName;
        this.championTransform = championTransform;
        this.consumablesPurchased = consumablesPurchased;
        this.damageDealtToBuildings = damageDealtToBuildings;
        this.damageDealtToObjectives = damageDealtToObjectives;
        this.damageDealtToTurrets = damageDealtToTurrets;
        this.damageSelfMitigated = damageSelfMitigated;
        this.deaths = deaths;
        this.detectorWardsPlaced = detectorWardsPlaced;
        this.doubleKills = doubleKills;
        this.dragonKills = dragonKills;
        this.firstBloodAssist = firstBloodAssist;
        this.firstBloodKill = firstBloodKill;
        this.firstTowerAssist = firstTowerAssist;
        this.firstTowerKill = firstTowerKill;
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.individualPosition = individualPosition;
        this.inhibitorKills = inhibitorKills;
        this.inhibitorTakedowns = inhibitorTakedowns;
        this.inhibitorsLost = inhibitorsLost;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.itemsPurchased = itemsPurchased;
        this.killingSprees = killingSprees;
        this.kills = kills;
        this.lane = lane;
        this.largestCriticalStrike = largestCriticalStrike;
        this.largestKillingSpree = largestKillingSpree;
        this.largestMultiKill = largestMultiKill;
        this.longestTimeSpentLiving = longestTimeSpentLiving;
        this.magicDamageDealt = magicDamageDealt;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.magicDamageTaken = magicDamageTaken;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.nexusKills = nexusKills;
        this.nexusTakedowns = nexusTakedowns;
        this.nexusLost = nexusLost;
        this.objectivesStolen = objectivesStolen;
        this.objectivesStolenAssists = objectivesStolenAssists;
        this.participantId = participantId;
        this.pentaKills = pentaKills;
        this.physicalDamageDealt = physicalDamageDealt;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.physicalDamageTaken = physicalDamageTaken;
        this.profileIcon = profileIcon;
        this.puuid = puuid;
        this.quadraKills = quadraKills;
        this.riotIdName = riotIdName;
        this.riotIdTagline = riotIdTagline;
        this.role = role;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.spell1Casts = spell1Casts;
        this.spell2Casts = spell2Casts;
        this.spell3Casts = spell3Casts;
        this.spell4Casts = spell4Casts;
        this.summoner1Casts = summoner1Casts;
        this.summoner1Id = summoner1Id;
        this.summoner2Casts = summoner2Casts;
        this.summoner2Id = summoner2Id;
        this.summonerId = summonerId;
        this.summonerLevel = summonerLevel;
        this.summonerName = summonerName;
        this.teamEarlySurrendered = teamEarlySurrendered;
        this.teamId = teamId;
        this.teamPosition = teamPosition;
        this.timeCCingOthers = timeCCingOthers;
        this.timePlayed = timePlayed;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
        this.totalDamageTaken = totalDamageTaken;
        this.totalHeal = totalHeal;
        this.totalHealsOnTeammates = totalHealsOnTeammates;
        this.totalMinionsKilled = totalMinionsKilled;
        this.totalTimeCCDealt = totalTimeCCDealt;
        this.totalTimeSpentDead = totalTimeSpentDead;
        this.totalUnitsHealed = totalUnitsHealed;
        this.tripleKills = tripleKills;
        this.trueDamageDealt = trueDamageDealt;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.trueDamageTaken = trueDamageTaken;
        this.turretKills = turretKills;
        this.turretTakedowns = turretTakedowns;
        this.turretsLost = turretsLost;
        this.unrealKills = unrealKills;
        this.visionScore = visionScore;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.wardsKilled = wardsKilled;
        this.wardsPlaced = wardsPlaced;
        this.win = win;
        this.perk = perk;
        this.match = match;
    }
    public Participant(int assists, int baronKills, int bountyLevel, int champExperience, int champLevel, int championId, String championName, int championTransform, int consumablesPurchased, int damageDealtToBuildings, int damageDealtToObjectives, int damageDealtToTurrets, int damageSelfMitigated, int deaths, int detectorWardsPlaced, int doubleKills, int dragonKills, boolean firstBloodAssist, boolean firstBloodKill, boolean firstTowerAssist, boolean firstTowerKill, boolean gameEndedInEarlySurrender, boolean gameEndedInSurrender, int goldEarned, int goldSpent, String individualPosition, int inhibitorKills, int inhibitorTakedowns, int inhibitorsLost, int item0, int item1, int item2, int item3, int item4, int item5, int item6, int itemsPurchased, int killingSprees, int kills, String lane, int largestCriticalStrike, int largestKillingSpree, int largestMultiKill, int longestTimeSpentLiving, int magicDamageDealt, int magicDamageDealtToChampions, int magicDamageTaken, int neutralMinionsKilled, int nexusKills, int nexusTakedowns, int nexusLost, int objectivesStolen, int objectivesStolenAssists, int participantId, int pentaKills, int physicalDamageDealt, int physicalDamageDealtToChampions, int physicalDamageTaken, int profileIcon, String puuid, int quadraKills, String riotIdName, String riotIdTagline, String role, int sightWardsBoughtInGame, int spell1Casts, int spell2Casts, int spell3Casts, int spell4Casts, int summoner1Casts, int summoner1Id, int summoner2Casts, int summoner2Id, String summonerId, int summonerLevel, String summonerName, boolean teamEarlySurrendered, int teamId, String teamPosition, int timeCCingOthers, int timePlayed, int totalDamageDealt, int totalDamageDealtToChampions, int totalDamageShieldedOnTeammates, int totalDamageTaken, int totalHeal, int totalHealsOnTeammates, int totalMinionsKilled, int totalTimeCCDealt, int totalTimeSpentDead, int totalUnitsHealed, int tripleKills, int trueDamageDealt, int trueDamageDealtToChampions, int trueDamageTaken, int turretKills, int turretTakedowns, int turretsLost, int unrealKills, int visionScore, int visionWardsBoughtInGame, int wardsKilled, int wardsPlaced, boolean win, List<Perk> perk, Match match) {
        this.assists = assists;
        this.baronKills = baronKills;
        this.bountyLevel = bountyLevel;
        this.champExperience = champExperience;
        this.champLevel = champLevel;
        this.championId = championId;
        this.championName = championName;
        this.championTransform = championTransform;
        this.consumablesPurchased = consumablesPurchased;
        this.damageDealtToBuildings = damageDealtToBuildings;
        this.damageDealtToObjectives = damageDealtToObjectives;
        this.damageDealtToTurrets = damageDealtToTurrets;
        this.damageSelfMitigated = damageSelfMitigated;
        this.deaths = deaths;
        this.detectorWardsPlaced = detectorWardsPlaced;
        this.doubleKills = doubleKills;
        this.dragonKills = dragonKills;
        this.firstBloodAssist = firstBloodAssist;
        this.firstBloodKill = firstBloodKill;
        this.firstTowerAssist = firstTowerAssist;
        this.firstTowerKill = firstTowerKill;
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.individualPosition = individualPosition;
        this.inhibitorKills = inhibitorKills;
        this.inhibitorTakedowns = inhibitorTakedowns;
        this.inhibitorsLost = inhibitorsLost;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.itemsPurchased = itemsPurchased;
        this.killingSprees = killingSprees;
        this.kills = kills;
        this.lane = lane;
        this.largestCriticalStrike = largestCriticalStrike;
        this.largestKillingSpree = largestKillingSpree;
        this.largestMultiKill = largestMultiKill;
        this.longestTimeSpentLiving = longestTimeSpentLiving;
        this.magicDamageDealt = magicDamageDealt;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.magicDamageTaken = magicDamageTaken;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.nexusKills = nexusKills;
        this.nexusTakedowns = nexusTakedowns;
        this.nexusLost = nexusLost;
        this.objectivesStolen = objectivesStolen;
        this.objectivesStolenAssists = objectivesStolenAssists;
        this.participantId = participantId;
        this.pentaKills = pentaKills;
        this.physicalDamageDealt = physicalDamageDealt;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.physicalDamageTaken = physicalDamageTaken;
        this.profileIcon = profileIcon;
        this.puuid = puuid;
        this.quadraKills = quadraKills;
        this.riotIdName = riotIdName;
        this.riotIdTagline = riotIdTagline;
        this.role = role;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.spell1Casts = spell1Casts;
        this.spell2Casts = spell2Casts;
        this.spell3Casts = spell3Casts;
        this.spell4Casts = spell4Casts;
        this.summoner1Casts = summoner1Casts;
        this.summoner1Id = summoner1Id;
        this.summoner2Casts = summoner2Casts;
        this.summoner2Id = summoner2Id;
        this.summonerId = summonerId;
        this.summonerLevel = summonerLevel;
        this.summonerName = summonerName;
        this.teamEarlySurrendered = teamEarlySurrendered;
        this.teamId = teamId;
        this.teamPosition = teamPosition;
        this.timeCCingOthers = timeCCingOthers;
        this.timePlayed = timePlayed;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
        this.totalDamageTaken = totalDamageTaken;
        this.totalHeal = totalHeal;
        this.totalHealsOnTeammates = totalHealsOnTeammates;
        this.totalMinionsKilled = totalMinionsKilled;
        this.totalTimeCCDealt = totalTimeCCDealt;
        this.totalTimeSpentDead = totalTimeSpentDead;
        this.totalUnitsHealed = totalUnitsHealed;
        this.tripleKills = tripleKills;
        this.trueDamageDealt = trueDamageDealt;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.trueDamageTaken = trueDamageTaken;
        this.turretKills = turretKills;
        this.turretTakedowns = turretTakedowns;
        this.turretsLost = turretsLost;
        this.unrealKills = unrealKills;
        this.visionScore = visionScore;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.wardsKilled = wardsKilled;
        this.wardsPlaced = wardsPlaced;
        this.win = win;
        this.perk = perk;
        this.match = match;
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

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public int getBountyLevel() {
        return bountyLevel;
    }

    public void setBountyLevel(int bountyLevel) {
        this.bountyLevel = bountyLevel;
    }

    public int getChampExperience() {
        return champExperience;
    }

    public void setChampExperience(int champExperience) {
        this.champExperience = champExperience;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public int getChampionTransform() {
        return championTransform;
    }

    public void setChampionTransform(int championTransform) {
        this.championTransform = championTransform;
    }

    public int getConsumablesPurchased() {
        return consumablesPurchased;
    }

    public void setConsumablesPurchased(int consumablesPurchased) {
        this.consumablesPurchased = consumablesPurchased;
    }

    public int getDamageDealtToBuildings() {
        return damageDealtToBuildings;
    }

    public void setDamageDealtToBuildings(int damageDealtToBuildings) {
        this.damageDealtToBuildings = damageDealtToBuildings;
    }

    public int getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }

    public void setDamageDealtToObjectives(int damageDealtToObjectives) {
        this.damageDealtToObjectives = damageDealtToObjectives;
    }

    public int getDamageDealtToTurrets() {
        return damageDealtToTurrets;
    }

    public void setDamageDealtToTurrets(int damageDealtToTurrets) {
        this.damageDealtToTurrets = damageDealtToTurrets;
    }

    public int getDamageSelfMitigated() {
        return damageSelfMitigated;
    }

    public void setDamageSelfMitigated(int damageSelfMitigated) {
        this.damageSelfMitigated = damageSelfMitigated;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getDetectorWardsPlaced() {
        return detectorWardsPlaced;
    }

    public void setDetectorWardsPlaced(int detectorWardsPlaced) {
        this.detectorWardsPlaced = detectorWardsPlaced;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(int doubleKills) {
        this.doubleKills = doubleKills;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }

    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }

    public void setFirstBloodAssist(boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }

    public void setFirstBloodKill(boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }

    public void setFirstTowerAssist(boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }

    public void setFirstTowerKill(boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    public boolean isGameEndedInEarlySurrender() {
        return gameEndedInEarlySurrender;
    }

    public void setGameEndedInEarlySurrender(boolean gameEndedInEarlySurrender) {
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
    }

    public boolean isGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public void setGameEndedInSurrender(boolean gameEndedInSurrender) {
        this.gameEndedInSurrender = gameEndedInSurrender;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public void setIndividualPosition(String individualPosition) {
        this.individualPosition = individualPosition;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public int getInhibitorTakedowns() {
        return inhibitorTakedowns;
    }

    public void setInhibitorTakedowns(int inhibitorTakedowns) {
        this.inhibitorTakedowns = inhibitorTakedowns;
    }

    public int getInhibitorsLost() {
        return inhibitorsLost;
    }

    public void setInhibitorsLost(int inhibitorsLost) {
        this.inhibitorsLost = inhibitorsLost;
    }

    public int getItem0() {
        return item0;
    }

    public void setItem0(int item0) {
        this.item0 = item0;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getItem6() {
        return item6;
    }

    public void setItem6(int item6) {
        this.item6 = item6;
    }

    public int getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(int itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    public int getKillingSprees() {
        return killingSprees;
    }

    public void setKillingSprees(int killingSprees) {
        this.killingSprees = killingSprees;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    public void setLargestCriticalStrike(int largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    public int getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public void setLargestKillingSpree(int largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    public int getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(int largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public int getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public void setLongestTimeSpentLiving(int longestTimeSpentLiving) {
        this.longestTimeSpentLiving = longestTimeSpentLiving;
    }

    public int getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public void setMagicDamageDealt(int magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    public int getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public void setMagicDamageDealtToChampions(int magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    public int getMagicDamageTaken() {
        return magicDamageTaken;
    }

    public void setMagicDamageTaken(int magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    public void setNeutralMinionsKilled(int neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    public int getNexusKills() {
        return nexusKills;
    }

    public void setNexusKills(int nexusKills) {
        this.nexusKills = nexusKills;
    }

    public int getNexusTakedowns() {
        return nexusTakedowns;
    }

    public void setNexusTakedowns(int nexusTakedowns) {
        this.nexusTakedowns = nexusTakedowns;
    }

    public int getNexusLost() {
        return nexusLost;
    }

    public void setNexusLost(int nexusLost) {
        this.nexusLost = nexusLost;
    }

    public int getObjectivesStolen() {
        return objectivesStolen;
    }

    public void setObjectivesStolen(int objectivesStolen) {
        this.objectivesStolen = objectivesStolen;
    }

    public int getObjectivesStolenAssists() {
        return objectivesStolenAssists;
    }

    public void setObjectivesStolenAssists(int objectivesStolenAssists) {
        this.objectivesStolenAssists = objectivesStolenAssists;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getPentaKills() {
        return pentaKills;
    }

    public void setPentaKills(int pentaKills) {
        this.pentaKills = pentaKills;
    }

    public int getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public void setPhysicalDamageDealt(int physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    public int getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    public void setPhysicalDamageDealtToChampions(int physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    public int getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public void setPhysicalDamageTaken(int physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(int profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public int getQuadraKills() {
        return quadraKills;
    }

    public void setQuadraKills(int quadraKills) {
        this.quadraKills = quadraKills;
    }

    public String getRiotIdName() {
        return riotIdName;
    }

    public void setRiotIdName(String riotIdName) {
        this.riotIdName = riotIdName;
    }

    public String getRiotIdTagline() {
        return riotIdTagline;
    }

    public void setRiotIdTagline(String riotIdTagline) {
        this.riotIdTagline = riotIdTagline;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public void setSightWardsBoughtInGame(int sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    public int getSpell1Casts() {
        return spell1Casts;
    }

    public void setSpell1Casts(int spell1Casts) {
        this.spell1Casts = spell1Casts;
    }

    public int getSpell2Casts() {
        return spell2Casts;
    }

    public void setSpell2Casts(int spell2Casts) {
        this.spell2Casts = spell2Casts;
    }

    public int getSpell3Casts() {
        return spell3Casts;
    }

    public void setSpell3Casts(int spell3Casts) {
        this.spell3Casts = spell3Casts;
    }

    public int getSpell4Casts() {
        return spell4Casts;
    }

    public void setSpell4Casts(int spell4Casts) {
        this.spell4Casts = spell4Casts;
    }

    public int getSummoner1Casts() {
        return summoner1Casts;
    }

    public void setSummoner1Casts(int summoner1Casts) {
        this.summoner1Casts = summoner1Casts;
    }

    public int getSummoner1Id() {
        return summoner1Id;
    }

    public void setSummoner1Id(int summoner1Id) {
        this.summoner1Id = summoner1Id;
    }

    public int getSummoner2Casts() {
        return summoner2Casts;
    }

    public void setSummoner2Casts(int summoner2Casts) {
        this.summoner2Casts = summoner2Casts;
    }

    public int getSummoner2Id() {
        return summoner2Id;
    }

    public void setSummoner2Id(int summoner2Id) {
        this.summoner2Id = summoner2Id;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public boolean isTeamEarlySurrendered() {
        return teamEarlySurrendered;
    }

    public void setTeamEarlySurrendered(boolean teamEarlySurrendered) {
        this.teamEarlySurrendered = teamEarlySurrendered;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }

    public int getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public void setTimeCCingOthers(int timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(int totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    public int getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public void setTotalDamageDealtToChampions(int totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    public int getTotalDamageShieldedOnTeammates() {
        return totalDamageShieldedOnTeammates;
    }

    public void setTotalDamageShieldedOnTeammates(int totalDamageShieldedOnTeammates) {
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(int totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public int getTotalHeal() {
        return totalHeal;
    }

    public void setTotalHeal(int totalHeal) {
        this.totalHeal = totalHeal;
    }

    public int getTotalHealsOnTeammates() {
        return totalHealsOnTeammates;
    }

    public void setTotalHealsOnTeammates(int totalHealsOnTeammates) {
        this.totalHealsOnTeammates = totalHealsOnTeammates;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public int getTotalTimeCCDealt() {
        return totalTimeCCDealt;
    }

    public void setTotalTimeCCDealt(int totalTimeCCDealt) {
        this.totalTimeCCDealt = totalTimeCCDealt;
    }

    public int getTotalTimeSpentDead() {
        return totalTimeSpentDead;
    }

    public void setTotalTimeSpentDead(int totalTimeSpentDead) {
        this.totalTimeSpentDead = totalTimeSpentDead;
    }

    public int getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    public void setTotalUnitsHealed(int totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    public int getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(int tripleKills) {
        this.tripleKills = tripleKills;
    }

    public int getTrueDamageDealt() {
        return trueDamageDealt;
    }

    public void setTrueDamageDealt(int trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    public int getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public void setTrueDamageDealtToChampions(int trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    public int getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public void setTrueDamageTaken(int trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    public int getTurretKills() {
        return turretKills;
    }

    public void setTurretKills(int turretKills) {
        this.turretKills = turretKills;
    }

    public int getTurretTakedowns() {
        return turretTakedowns;
    }

    public void setTurretTakedowns(int turretTakedowns) {
        this.turretTakedowns = turretTakedowns;
    }

    public int getTurretsLost() {
        return turretsLost;
    }

    public void setTurretsLost(int turretsLost) {
        this.turretsLost = turretsLost;
    }

    public int getUnrealKills() {
        return unrealKills;
    }

    public void setUnrealKills(int unrealKills) {
        this.unrealKills = unrealKills;
    }

    public int getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(int visionScore) {
        this.visionScore = visionScore;
    }

    public int getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(int visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public int getWardsKilled() {
        return wardsKilled;
    }

    public void setWardsKilled(int wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(int wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public List<Perk> getPerk() {
        return perk;
    }

    public void setPerk(List<Perk> perk) {
        this.perk = perk;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
