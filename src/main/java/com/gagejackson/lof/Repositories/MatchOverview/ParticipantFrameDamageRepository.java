package com.gagejackson.lof.Repositories.MatchOverview;

import com.gagejackson.lof.Models.MatchOverview.ParticipantFrame;
import com.gagejackson.lof.Models.MatchOverview.ParticipantFrameChamp;
import com.gagejackson.lof.Models.MatchOverview.ParticipantFrameDamage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantFrameDamageRepository extends JpaRepository<ParticipantFrameDamage, Long> {
    List<ParticipantFrameDamage> findParticipantFrameDamagesByParticipantFrame(ParticipantFrame participantFrame);

}
