package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository  extends JpaRepository<Participant, Long> {
}
