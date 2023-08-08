package com.gagejackson.lof.Repositories.MatchEvent;

import com.gagejackson.lof.Models.MatchEvent.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventItemRepository extends JpaRepository<EventItem, Long> {
}
