package com.gagejackson.lof.Repositories.MatchEvent;

import com.gagejackson.lof.Models.MatchEvent.Event;
import com.gagejackson.lof.Models.MatchOverview.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByParticipant(Participant participant);
}
