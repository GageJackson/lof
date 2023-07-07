package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventItemRepository extends JpaRepository<EventItem, Long> {
}
