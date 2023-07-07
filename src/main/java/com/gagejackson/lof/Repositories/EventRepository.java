package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
