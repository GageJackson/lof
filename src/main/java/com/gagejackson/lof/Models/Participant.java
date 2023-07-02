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
}
