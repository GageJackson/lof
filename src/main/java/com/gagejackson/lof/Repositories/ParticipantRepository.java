package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Match;
import com.gagejackson.lof.Models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository  extends JpaRepository<Participant, Long> {
    Participant findByMatchAndParticipantNum(Match match, int participantId);
    Participant findByMatchAndPuuid(Match match, String puuid);
    List<Participant>findByMatch(Match match);
}
