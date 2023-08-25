package com.gagejackson.lof.Repositories.MatchOverview;

import com.gagejackson.lof.Models.MatchOverview.Match;
import com.gagejackson.lof.Models.MatchOverview.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository  extends JpaRepository<Participant, Long> {
    Participant findByMatchAndParticipantNum(Match match, int participantId);
    Participant findByMatchAndPuuid(Match match, String puuid);
    List<Participant>findByMatch(Match match);
}
