package com.gagejackson.lof.Repositories.MatchEvent;

import com.gagejackson.lof.Models.MatchEvent.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
