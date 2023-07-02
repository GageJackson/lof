package com.gagejackson.lof.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "game_creation")
    private long gameCreation;

    @Column (name = "game_duration")
    private long gameDuration;

    @Column (name = "game_end")
    private long gameEnd;

    @Column (name = "game_id", nullable = false, unique = true)
    private long gameId;

    @Column (name = "game_mode")
    private String gameMode;

    @Column (name = "game_name")
    private String gameName;

    @Column (name = "game_start")
    private long gameStart;

    @Column (name = "game_type")
    private String gameType;

    @Column (name = "game_version")
    private String gameVersion;

    @Column (name = "map_id")
    private int mapId;

    @Column (name = "platform_id")
    private String platformId;

    @Column (name = "queue_id")
    private int queueId;

    @Column (name = "tournament_code")
    private String tournamentCode;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "match")
    private List<Team> team;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "match")
    private List<Participant>participant;
}
