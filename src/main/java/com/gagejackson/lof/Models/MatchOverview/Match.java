package com.gagejackson.lof.Models.MatchOverview;

import com.gagejackson.lof.Models.Friend.FriendMatch;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;

    @Column (name = "is_saved", nullable = false)
    private boolean isSaved;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private List<FriendMatch> friendMatch;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Match() {}

    public Match(long id, boolean isSaved, long gameCreation, long gameDuration, long gameEnd, long gameId, String gameMode, String gameName, long gameStart, String gameType, String gameVersion, int mapId, String platformId, int queueId, String tournamentCode, List<Team> team, List<Participant> participant, List<FriendMatch> friendMatch) {
        this.id = id;
        this.isSaved = isSaved;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameEnd = gameEnd;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStart = gameStart;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.platformId = platformId;
        this.queueId = queueId;
        this.tournamentCode = tournamentCode;
        this.team = team;
        this.participant = participant;
        this.friendMatch = friendMatch;
    }

    public Match(boolean isSaved, long gameCreation, long gameDuration, long gameEnd, long gameId, String gameMode, String gameName, long gameStart, String gameType, String gameVersion, int mapId, String platformId, int queueId, String tournamentCode, List<Team> team, List<Participant> participant, List<FriendMatch> friendMatch) {
        this.isSaved = isSaved;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameEnd = gameEnd;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStart = gameStart;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.platformId = platformId;
        this.queueId = queueId;
        this.tournamentCode = tournamentCode;
        this.team = team;
        this.participant = participant;
        this.friendMatch = friendMatch;
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

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(long gameEnd) {
        this.gameEnd = gameEnd;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public long getGameStart() {
        return gameStart;
    }

    public void setGameStart(long gameStart) {
        this.gameStart = gameStart;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getTournamentCode() {
        return tournamentCode;
    }

    public void setTournamentCode(String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public List<FriendMatch> getFriendMatch() {
        return friendMatch;
    }

    public void setFriendMatch(List<FriendMatch> friendMatch) {
        this.friendMatch = friendMatch;
    }
}
