package com.gagejackson.lof.Repositories.MatchOverview;

import com.gagejackson.lof.Models.MatchOverview.Participant;
import com.gagejackson.lof.Models.MatchOverview.ParticipantFrame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantFrameRepository extends JpaRepository<ParticipantFrame, Long> {
    List<ParticipantFrame> findParticipantFramesByParticipant(Participant participant);
}
